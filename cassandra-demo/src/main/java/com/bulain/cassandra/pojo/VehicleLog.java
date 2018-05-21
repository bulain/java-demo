package com.bulain.cassandra.pojo;

import java.util.Date;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "gps", name = "vehicle_log")
public class VehicleLog {
    @PartitionKey
    private String vehicle;
    private Date time;
    private String lnglat;

    public String getVehicle() {
        return vehicle;
    }
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public String getLnglat() {
        return lnglat;
    }
    public void setLnglat(String lnglat) {
        this.lnglat = lnglat;
    }

}
