package org.visualJson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public  class ViewDirector  {
    public static Properties properties= new Properties();

    ViewDirector(){
        try {
            properties.load(new InputStreamReader(new FileInputStream("./config.conf"), StandardCharsets.UTF_8));
            System.out.println("load config successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void constructTreeView(ViewBuilder builder, String filePath,String iconFamilyOption){
//        System.out.println(properties.getProperty("key1"));
        String branchIcon = properties.getProperty(iconFamilyOption+".branchIcon");
        String leafIcon = properties.getProperty(iconFamilyOption+".leafIcon");
        builder.reset();
        builder.setBranchIcon(branchIcon);
        builder.setLeafIcon(leafIcon);
        builder.loadJson(filePath);
    }
    public void constructRectView(RectViewBuilder builder, String filePath,String iconFamilyOption) {
        String branchIcon = properties.getProperty(iconFamilyOption+".branchIcon");
        String leafIcon = properties.getProperty(iconFamilyOption+".leafIcon");
        builder.reset();
        builder.setBranchIcon(branchIcon);
        builder.setLeafIcon(leafIcon);
        builder.loadJson(filePath);
    }
}
