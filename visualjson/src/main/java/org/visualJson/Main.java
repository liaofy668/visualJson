package org.visualJson;

import com.google.gson.internal.LinkedTreeMap;
import org.apache.commons.cli.*;

import java.util.Objects;

public abstract class Main {
    public static void main(String[] args) {
        Options options = new Options();

        Option input = new Option("f", "json file", true, "input JSON file");
//        input.setRequired(true);
        options.addOption(input);

        Option style = new Option("s", "style", true, "style option");
        options.addOption(style);

        Option iconFamily = new Option("i", "icon family", true, "icon family option");
        options.addOption(iconFamily);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            String jsonFile = cmd.hasOption('f') ? cmd.getOptionValue('f') : "./data.json";
            String styleOption = cmd.hasOption('s') ? cmd.getOptionValue('s') : "1";
            String iconFamilyOption = cmd.hasOption('i') ? cmd.getOptionValue('i') : "iconFamily1";
            if (Objects.equals(styleOption, "1")){
                TreeViewFactory viewFactory = new TreeViewFactory();
                View view =  viewFactory.createView(jsonFile,iconFamilyOption);
                view.show();
            }
            else{
                RectViewFactory viewFactory = new RectViewFactory();
                View view =  viewFactory.createView(jsonFile,iconFamilyOption);
                view.show();
            }

//            System.out.println("JSON file: " + jsonFile);
//            System.out.println("Style option: " + styleOption);
//            System.out.println("Icon family option: " + iconFamilyOption);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("program", options);
            System.exit(1);
        }
    }
}