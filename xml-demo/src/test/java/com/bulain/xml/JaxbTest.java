package com.bulain.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.bulain.xml.pojo.Student;
import com.bulain.xml.pojo.Students;

public class JaxbTest {

    @Test
    public void testUnmarshall() throws IOException, JAXBException {
        // get test file
        File file = getTestFile();

        // unmarshal
        Students students = JAXB.unmarshal(file, Students.class);

        // assert
        assertNotNull(students);
        assertEquals(2, students.getStudent().size());
    }

    @Test
    public void testMarshaller() {
        // prepare
        Students students = new Students();
        Student student = new Student();
        student.setName("name");
        student.setAddress("address");
        students.getStudent().add(student);

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        // marshal
        JAXB.marshal(students, os);

        // assert
        System.out.println(os.toString());
    }

    private File getTestFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/bulain/xml/student.xml");
        return resource.getFile();
    }
}
