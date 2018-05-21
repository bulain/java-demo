package com.bulain.jolokia.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.jolokia.Version;
import org.jolokia.client.J4pClient;
import org.jolokia.client.exception.J4pException;
import org.jolokia.client.request.J4pExecRequest;
import org.jolokia.client.request.J4pListRequest;
import org.jolokia.client.request.J4pReadRequest;
import org.jolokia.client.request.J4pReadResponse;
import org.jolokia.client.request.J4pResponse;
import org.jolokia.client.request.J4pSearchRequest;
import org.jolokia.client.request.J4pVersionRequest;
import org.jolokia.client.request.J4pWriteRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:spring/applicationContext-resource.xml",
        "classpath:spring/jmx-client.xml"})
public class JolokiaIT {

    private static final String HTTP_URL = "http://localhost:8082/jolokia-demo/jolokia/";
    private static final String OBJECT_NAME = "jolokia:name=JmxBean";

    private J4pClient j4pClient;

    @Before
    public void setUp() {
        j4pClient = new J4pClient(HTTP_URL);
    }

    @Test
    public void testVersion() throws J4pException {
        J4pVersionRequest vreq = new J4pVersionRequest();
        J4pResponse<J4pVersionRequest> vresp = j4pClient.execute(vreq);
        Map<String, ?> values = vresp.getValue();
        assertEquals(Version.getProtocolVersion(), values.get("protocol"));
        assertEquals(Version.getAgentVersion(), values.get("agent"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testList() throws J4pException {
        J4pListRequest lreq = new J4pListRequest("jolokia/name=JmxBean");
        J4pResponse<J4pListRequest> lresp = j4pClient.execute(lreq);
        Map<String, ?> value = lresp.getValue();
        //desc
        assertEquals("Jmx Management Bean", value.get("desc"));
        //op
        Map<String, ?> ops = (Map<String, ?>) value.get("op");
        assertTrue(ops.containsKey("setTimes"));
        assertTrue(ops.containsKey("addTimes"));
        assertTrue(ops.containsKey("getTimes"));
        assertTrue(ops.containsKey("resetTimes"));
        //attr
        Map<String, ?> attrs = (Map<String, ?>) value.get("attr");
        assertTrue(attrs.containsKey("Times"));
    }

    @Test
    public void testSearch() throws Exception {
        //all
        J4pSearchRequest sreq = new J4pSearchRequest("*:*");
        J4pResponse<J4pSearchRequest> sresp = j4pClient.execute(sreq);
        List<String> values = sresp.getValue();
        assertTrue(values.contains(OBJECT_NAME));

        //jolokia:*
        sreq = new J4pSearchRequest("jolokia:*");
        sresp = j4pClient.execute(sreq);
        values = sresp.getValue();
        assertTrue(values.contains(OBJECT_NAME));

        //jolokia:name=*
        sreq = new J4pSearchRequest("jolokia:name=*");
        sresp = j4pClient.execute(sreq);
        values = sresp.getValue();
        assertTrue(values.contains(OBJECT_NAME));

        //jolokia:name=JmxBean
        sreq = new J4pSearchRequest("jolokia:name=JmxBean");
        sresp = j4pClient.execute(sreq);
        values = sresp.getValue();
        assertTrue(values.contains(OBJECT_NAME));
    }

    @Test
    public void testJmxBean() throws Exception {
        //operation
        J4pExecRequest ereq = new J4pExecRequest(OBJECT_NAME, "resetTimes");
        J4pResponse<J4pExecRequest> eresp = j4pClient.execute(ereq);
        eresp.getValue();

        //get
        J4pReadRequest rreq = new J4pReadRequest(OBJECT_NAME, "Times");
        J4pReadResponse rresp = j4pClient.execute(rreq);
        Object times = rresp.getValue();
        assertEquals(0L, times);

        //set
        J4pWriteRequest wreq = new J4pWriteRequest(OBJECT_NAME, "Times", 2);
        J4pResponse<J4pWriteRequest> wresp = j4pClient.execute(wreq);
        wresp.getValue();;

        //get
        rreq = new J4pReadRequest(OBJECT_NAME, "Times");
        rresp = j4pClient.execute(rreq);
        times = rresp.getValue();
        assertEquals(2L, times);

        //operation
        ereq = new J4pExecRequest(OBJECT_NAME, "addTimes", 3);
        eresp = j4pClient.execute(ereq);
        times = eresp.getValue();
        assertEquals(5L, times);
    }

}
