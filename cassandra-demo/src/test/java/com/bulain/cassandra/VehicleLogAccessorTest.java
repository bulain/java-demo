package com.bulain.cassandra;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bulain.cassandra.dao.VehicleLogAccessor;
import com.bulain.cassandra.pojo.VehicleLog;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

public class VehicleLogAccessorTest extends CassTestCase {
    private VehicleLogAccessor accessor;

    @Before
    public void setUp() {
        MappingManager manager = new MappingManager(session);
        accessor = manager.createAccessor(VehicleLogAccessor.class);
    }

    @Test
    public void testGps() {
        long base = 1428384140693L;
        String vehicleNo = "沪A12345";
        for (int i = 0; i < 10; i++) {
            Date time = new Date(base + i * 100);
            String lnglat = String.format("[%d:%d]", i, i);
            accessor.insert(vehicleNo, time, lnglat);
        }

        Date timeFrom = new Date(base + 2 * 100);
        Date timeTo = new Date(base + 5 * 100);
        Result<VehicleLog> result = accessor.find(vehicleNo, timeFrom, timeTo);
        List<VehicleLog> all = result.all();
        assertEquals(4, all.size());
        
    }

}
