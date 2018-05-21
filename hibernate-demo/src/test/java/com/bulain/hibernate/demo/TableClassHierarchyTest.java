package com.bulain.hibernate.demo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.HibernateTestCase;
import com.bulain.hibernate.entity.Bike;
import com.bulain.hibernate.entity.Car;
import com.bulain.hibernate.entity.Vehicle;

@SuppressWarnings("unchecked")
@SeedDataSet(file = "test-data/init_seed_dataset.xml")
public class TableClassHierarchyTest extends HibernateTestCase {

    @Test
    public void testTableClassHierarchy() {
        Vehicle vechicle = new Vehicle("vechicle");
        Car car = new Car("car", "carInfo");
        Bike bike = new Bike("bike", "bikeInfo");

        session.save(vechicle);
        session.save(car);
        session.save(bike);
        
        List<Vehicle> listVehicle = session.createCriteria(Vehicle.class).list();
        assertEquals(3, listVehicle.size());
        
        List<Car> listCar = session.createCriteria(Car.class).list();
        assertEquals(1, listCar.size());
        
        List<Bike> listBike = session.createCriteria(Bike.class).list();
        assertEquals(1, listBike.size());
    }

}
