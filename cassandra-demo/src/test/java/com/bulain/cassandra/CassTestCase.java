package com.bulain.cassandra;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

public abstract class CassTestCase {
    protected static Cluster cluster;
    protected static Session session;

    @BeforeClass
    public static void setUpClass() {
        String node = "127.0.0.1";
        cluster = Cluster.builder().addContactPoint(node).build();
        Metadata metadata = cluster.getMetadata();
        System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(), host.getAddress(),
                    host.getRack());
        }
        session = cluster.connect();
    }

    @AfterClass
    public static void tearDownClass() {
        session.close();
        cluster.close();
    }

}
