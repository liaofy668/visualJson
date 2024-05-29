package org.visualJson;

public class Client {
    public static void run(String[] args) {
        TreeViewFactory viewFactory = new TreeViewFactory();
        View view =  viewFactory.createView("src/main/java/org/example/data.json","iconFamily1");
        view.show();
    }
}
