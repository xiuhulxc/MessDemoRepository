package com.example.demo1.util;

import java.io.*;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/9/27 18:18
 * @version: 1.0
 */
public class yolo {


    public static void convertYoloToVoc(String yoloPath, String vocPath, String imageFolder) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(yoloPath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(vocPath));

            writer.write("<annotation>\n");
            writer.write("\t<folder>" + imageFolder + "</folder>\n");
            writer.write("\t<filename>" + yoloPath.substring(yoloPath.lastIndexOf("/") + 1, yoloPath.lastIndexOf(".")) + "</filename>\n");
            writer.write("\t<size>\n");
            writer.write("\t\t<width>0</width>\n"); // Replace with actual width
            writer.write("\t\t<height>0</height>\n"); // Replace with actual height
            writer.write("\t\t<depth>3</depth>\n"); // Replace with actual depth (e.g., 3 for RGB)
            writer.write("\t</size>\n");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(" ");
                String label = parts[0];
                double xCenter = Double.parseDouble(parts[1]);
                System.out.println(xCenter);
                double yCenter = Double.parseDouble(parts[2]);
                double width = Double.parseDouble(parts[3]);
                double height = Double.parseDouble(parts[4]);

                int xmin = (int) ((xCenter - width / 2) * width);
                int ymin = (int) ((yCenter - height / 2) * height);
                int xmax = (int) ((xCenter + width / 2) * width);
                int ymax = (int) ((yCenter + height / 2) * height);

                writer.write("\t<object>\n");
                writer.write("\t\t<name>" + label + "</name>\n");
                writer.write("\t\t<bndbox>\n");
                writer.write("\t\t\t<xmin>" + xmin + "</xmin>\n");
                writer.write("\t\t\t<ymin>" + ymin + "</ymin>\n");
                writer.write("\t\t\t<xmax>" + xmax + "</xmax>\n");
                writer.write("\t\t\t<ymax>" + ymax + "</ymax>\n");
                writer.write("\t\t</bndbox>\n");
                writer.write("\t</object>\n");
            }

            writer.write("</annotation>");

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String yoloFile = "D:/tiegao-new/ff/0f44cf3351c1f63b6f968da750f30f43.txt";
        String vocFile = "D:/tiegao-new/ff/0f44cf3351c1f63b6f968da750f30f43.xml";
        String imageFolder = "D:/tiegao-new/ff";

        convertYoloToVoc(yoloFile, vocFile, imageFolder);

    }



}
