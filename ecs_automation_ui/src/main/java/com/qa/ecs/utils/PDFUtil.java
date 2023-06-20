package com.qa.ecs.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


/**
 * This Class is used to provide methods related to PDF file Handling
 *
 * @author Romee Afroz
 * @version 1.0
 * @since 2022-12-15
 */
public class PDFUtil {

    String url = ("./src/test/resources/Documents/healthPolicy.pdf");

    /**
     * This method is used to read data from PDF file
     *
     * @param sheetName Excel's SheetName
     * @return This will return data in Object[][] format
     */
    public static String getPdfContent(String url) throws IOException {
        URL pdfURL = new URL(url);
        InputStream is = pdfURL.openStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        PDDocument doc = PDDocument.load(bis);
        PDFTextStripper strip = new PDFTextStripper();
        String stripText = strip.getText(doc);
        System.out.println(stripText);
        doc.close();
        return stripText;

    }

    public static String getPdfContentForPages(String filepath) throws IOException, InterruptedException {
        FileInputStream ip = new FileInputStream(filepath);
        PDDocument doc = PDDocument.load(ip);
        int pages = doc.getNumberOfPages();
        System.out.println("The total number of pages " + pages);
        //System.out.println(doc.isEncrypted());
        PDFTextStripper strip = new PDFTextStripper();
        strip.setStartPage(1);
        //strip.setEndPage(8);
        String stripText = strip.getText(doc);
        System.out.println(stripText + "pdf text");
        doc.close();
        return stripText;
    }
}
