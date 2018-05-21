package com.bulain.hibernate.demo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.HibernateTestCase;
import com.bulain.hibernate.entity.Computer;
import com.bulain.hibernate.entity.Desktop;
import com.bulain.hibernate.entity.Lappad;

@SuppressWarnings("unchecked")
@SeedDataSet(file = "test-data/init_seed_dataset.xml")
public class TableSubclassDiscriminatorTest extends HibernateTestCase {

    @Test
    public void testTableSubclassDiscriminator() {
        Computer Computer = new Computer("Computer");
        Lappad Lappad = new Lappad("Lappad", "LappadInfo");
        Desktop Desktop = new Desktop("Desktop", "DesktopInfo");

        session.save(Computer);
        session.save(Lappad);
        session.save(Desktop);
        
        List<Computer> listComputer = session.createCriteria(Computer.class).list();
        assertEquals(3, listComputer.size());
        
        List<Lappad> listLappad = session.createCriteria(Lappad.class).list();
        assertEquals(1, listLappad.size());
        
        List<Desktop> listDesktop = session.createCriteria(Desktop.class).list();
        assertEquals(1, listDesktop.size());
    }

}
