package com.bulain.printer;

import java.io.FileInputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;

import org.junit.Test;

public class PrintServiceTest {
    @Test
    public void testPrintServiceLookup() {
        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
        System.out.println(printService);
        System.out.println();

        DocFlavor[] docFlavors = printService.getSupportedDocFlavors();
        for (DocFlavor df : docFlavors) {
            System.out.println(df);
        }
        System.out.println();

        PrintServiceAttributeSet attributes = printService.getAttributes();
        Attribute[] array = attributes.toArray();
        for (Attribute a : array) {
            System.out.println(a.getName() + " : " + a);
        }
        System.out.println();

        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService ps : printServices) {
            System.out.println(ps);
        }
        System.out.println();

        AttributeSet attr = new HashPrintServiceAttributeSet();
        PrinterName printerName = new PrinterName("Foxit Reader PDF Printer", null);
        attr.add(printerName);
        printServices = PrintServiceLookup.lookupPrintServices(null, attr);
        for (PrintService ps : printServices) {
            System.out.println(ps);
        }
        System.out.println();
    }

    @Test
    public void testDocPrintJob() throws Exception {
        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob printJob = printService.createPrintJob();

        DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(MediaSizeName.ISO_A6);
        FileInputStream fis = new FileInputStream("src/test/resources/png/ilogie_arch.png");
        Doc doc = new SimpleDoc(fis, flavor, null);
        printJob.print(doc, aset);
    }

    
}
