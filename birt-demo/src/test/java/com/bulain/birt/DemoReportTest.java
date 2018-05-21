package com.bulain.birt;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;

import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IPDFRenderOption;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;

public class DemoReportTest extends BirtReport {
    private static String OUTPUT_FORMAT_POSTSCRIPT = "postscript";

    @Test
    public void testReport() throws Exception {
        ClassPathResource resource = new ClassPathResource("birt/demoReport.rptdesign");
        InputStream is = resource.getInputStream();
        IReportRunnable design = engine.openReportDesign(is);

        IRunAndRenderTask task = engine.createRunAndRenderTask(design);

        IPDFRenderOption options = new PDFRenderOption();
        options.setOutputFormat(IRenderOption.OUTPUT_FORMAT_PDF);
        options.setOutputFileName("target/demoReport.pdf");

        task.setRenderOption(options);
        task.run();

    }

    @Test
    public void testAutoPrint() throws Exception {

        {
            ClassPathResource resource = new ClassPathResource("birt/demoReport.rptdesign");
            InputStream is = resource.getInputStream();
            IReportRunnable design = engine.openReportDesign(is);

            IRunAndRenderTask task = engine.createRunAndRenderTask(design);

            IPDFRenderOption options = new PDFRenderOption();
            options.setOutputFormat(IRenderOption.OUTPUT_FORMAT_PDF);
            options.setOutputFileName("target/demoReport1.pdf");

            task.setRenderOption(options);
            task.run();
        }

        {
            PdfReader js1 = new PdfReader("target/demoReport1.pdf");
            PdfCopyFields cpy = new PdfCopyFields(new FileOutputStream("target/demoReport2.pdf"));
            cpy.addDocument(js1);
            cpy.addJavaScript("this.print({bUI:true, bSilent:false, bShrinkToFit:true});");
            cpy.close();
        }

    }

    @Test
    public void testPostscript() throws Exception {
        ClassPathResource resource = new ClassPathResource("birt/demoReport.rptdesign");
        InputStream is = resource.getInputStream();
        IReportRunnable design = engine.openReportDesign(is);

        IRunAndRenderTask task = engine.createRunAndRenderTask(design);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IRenderOption options = new HTMLRenderOption();
        options.setOutputFormat(OUTPUT_FORMAT_POSTSCRIPT);
        options.setOutputFileName("target/demoReport.ps");
        task.setRenderOption(options);
        task.run();
        task.close();

        //setup your Printer 
        //        InputStream inputStream = new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray());
        //        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        //        synchronized (service) {
        //            DocPrintJob job = service.createPrintJob();
        //            Doc doc = new SimpleDoc(inputStream, DocFlavor.INPUT_STREAM.POSTSCRIPT, null);
        //            PrintRequestAttributeSet pra = new HashPrintRequestAttributeSet();
        //            pra.add(MediaSizeName.ISO_A4);
        //            job.print(doc, pra);
        //            
        //            PrinterJob printJob = PrinterJob.getPrinterJob();
        //            Book book = new Book();
        //            PageFormat pageFormat = printJob.defaultPage();
        //            Printable painter = null;
        //            book.append(painter , pageFormat);
        //            printJob.setPageable(book);
        //            printJob.print();
        //            
        //        }
    }

    @Test
    public void testPrint() throws Exception {
        FileInputStream psStream = new FileInputStream("target/demoReport.ps");

        DocFlavor psInFormat = DocFlavor.INPUT_STREAM.POSTSCRIPT;
        Doc myDoc = new SimpleDoc(psStream, psInFormat, null);
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(new Copies(2));
        aset.add(MediaSizeName.ISO_A4);
        //aset.add(Sides.DUPLEX);
        PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, null);
        System.out.println(Arrays.asList(services));
        if (services.length > 0) {
            DocPrintJob job = services[0].createPrintJob();
            job.print(myDoc, aset);
        }
    }

}
