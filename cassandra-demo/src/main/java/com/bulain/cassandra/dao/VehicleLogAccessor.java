package com.bulain.cassandra.dao;

import java.util.Date;

import com.bulain.cassandra.pojo.VehicleLog;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

@Accessor
public interface VehicleLogAccessor {
    
    @Query("INSERT INTO gps.vehicle_log (vehicle, time, lnglat) values (:vehicle, :time, :lnglat)")
    ResultSet insert(@Param("vehicle") String vehicleNo, @Param("time") Date time, @Param("lnglat") String lnglat);

    @Query("SELECT * FROM gps.vehicle_log where vehicle = :vehicle and time >= :timeFrom and time <= :timeTo")
    Result<VehicleLog> find(@Param("vehicle") String vehicleNo, @Param("timeFrom") Date timeFrom,
            @Param("timeTo") Date timeTo);

}
