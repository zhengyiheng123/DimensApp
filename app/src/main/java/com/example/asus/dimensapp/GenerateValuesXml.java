package com.example.asus.dimensapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2015/11/1.
 * 默认情况下字体不用适配
 * 步骤：
 * 1、将ios的尺寸转换为安卓480*320的尺寸（hdpi）
 * 2、根据该尺寸适配其它尺寸的屏幕
 * 屏幕比例：        2:3:4:6:8
 */
public class GenerateValuesXml {

    private static String cellTemplet = "<dimen name=\"{0}\">{1}</dimen>\n";
    private static final float baseRatio = 1f;
    private static float baseH = 1136;
    private static float baseW = 640;
    private static float ratio;
    private static float wRatio;
    private static float hRatio;
    private static String rootFile = "./res";
    private static float n = 3.0f;
    private static int baseHeight = 5;
    private static String[] files = {"values-mdpi", "values-hdpi", "values-xhdpi", "values-xxhdpi"};
    private static String[] suppport_Dimens = {"480*320", "800*480", "1280*720", "1920*1080"};
    private static File baseFile = null;

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args.length == 2) {
                baseH = Float.valueOf(args[0]);
                baseW = Float.valueOf(args[1]);
            } else {
                System.out.println("输入格式：java -jar autoDimens.jar height width");
            }
        }
        generateXmlFile();
    }

    public static void generateXmlFile() {
        File dir = new File(rootFile);
        if (!dir.exists()) {
            dir.mkdir();
        }

        for (int i = 0; i < files.length; i++) {
            baseFile = new File(rootFile, files[i]);
            if (!baseFile.exists())
                baseFile.mkdir();

            handleRatio(files[i]);

            generateXmlFile("lay_x.xml", 1, 2000, "x", "dp");
            generateXmlFile("lay_y.xml", 1, 2000, "y", "dp");
            generateXmlFile("lay_f.xml", 11, 20, "f", "sp");
        }
    }

    public static void generateXmlFile(String fileName, int start, int end, String... type) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        buffer.append("<resources>");

        if (start <= end) {
            for (int i = start; i <= end; i++) {
                buffer.append(cellTemplet.replace("{0}", type[0] + i + "").replace("{1}", change((i * handleType(type[0])) / n) + type[1]));
            }
        } else {
            System.out.println("please end >= start");
        }

        buffer.append("</resources>");

        if (null != baseFile) {
            File file = new File(baseFile.getAbsolutePath(), fileName);
            try {
                PrintWriter pw = new PrintWriter(new FileOutputStream(file));
                pw.print(buffer.toString());
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static float handleType(String type) {
        if (type.equals("x")) {
            return wRatio;
        } else if (type.equals("y")) {
            return hRatio;
        } else if (type.equals("f")) {
            n = 1;
            return 1;
        }
        return 0;
    }

    public static void handleRatio(String str) {
        System.out.println(str);
        if (str.equals("values-hdpi")) {
            wRatio = 480f / baseW;
            hRatio = 800f / baseH;
            ratio = baseRatio * (3f / 4f);
            n = 1.5f;
        } else if (str.equals("values-xhdpi")) {
            wRatio = 720f / baseW;
            hRatio = 1280f / baseH;
            ratio = baseRatio * (1f);
            n = 2f;
        } else if (str.equals("values-mdpi")) {
            wRatio = 320f / baseW;
            hRatio = 480f / baseH;
            ratio = baseRatio * (2f / 4f);
            n = 1f;
        } else if (str.equals("values-xxhdpi")) {
            wRatio = 1080f / baseW;
            hRatio = 1920f / baseH;
            ratio = baseRatio * (6f / 4f);
            n = 3f;
        }
    }

    //该方法是为了保留三位小数
    public static float change(float num) {
        int temp = (int) (num * 1000);
        return temp / 1000f;
    }
}
