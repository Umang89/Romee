package com.qa.ecs.utils;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ImageUtil {

    public static String getImageText(String filePath) throws TesseractException {
        Tesseract tes = new Tesseract();
        tes.setDatapath("./src/test/resources/tessdata");
        tes.setLanguage("eng");
        String result = tes.doOCR(new File(filePath));
        System.out.println(result);
        return result;
    }
}
