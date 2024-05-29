package org.visualJson;

public class ViewComponent implements View {
    private String prefix;
    private Icon icon;
    private String attrname;

    ViewComponent(String prefix, Icon icon, String attrname){
        this.prefix = prefix;
        this.icon = icon;
        this.attrname = attrname;
    }


    @Override
    public void show() {
        System.out.print(prefix);
        icon.draw();
        System.out.println(attrname);
    }
}
