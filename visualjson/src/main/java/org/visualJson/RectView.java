package org.visualJson;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RectView implements View {
    private List<View> child = new ArrayList<>();
    private Icon branchIcon;
    private Icon leafIcon;
    public void setBranchIcon(Icon icon){
        this.branchIcon =icon;
    }
    public void setLeafIcon(Icon icon){
        this.leafIcon = icon;
    }
    public void add(View component){
        child.add(component);
    }

    public static String firstKey = "";
    public static String lastKey = "";
    public static Integer maxLen=0;

    public void computer_len(LinkedTreeMap<String, Object> tree,Integer len){
        if (Objects.equals(firstKey, ""))firstKey = (String) tree.keySet().toArray()[0];
        for (String key : tree.keySet()){
            lastKey = key;
            Object value = tree.get(key);
            if (value instanceof LinkedTreeMap) {
//                String nextPrefix = prefix + (key.equals(tree.keySet().toArray()[tree.size() - 1]) ? "    " : "│   ");
                this.computer_len((LinkedTreeMap<String,Object>) value, len+4);
            }
            else {
                if (value!=null)
                    maxLen = Math.max(maxLen,len+1+key.length()+4+value.toString().length());
            }
        }
    }

    public void load(LinkedTreeMap<String, Object> tree, String prefix) {
        if (maxLen==0){
            computer_len(tree,0);
            maxLen+=10;
        }
        for (String key : tree.keySet()) {
            String connector = "├─ ";String end = "─┤";
            if (Objects.equals(key, firstKey)){
                connector = "┌─ ";end = "─┐";
            }
            if (Objects.equals(key, lastKey)){
                if(!prefix.isEmpty()) connector = "┴─ ";else connector = "└─ ";
                end = "─┘";
            }
            Object value = tree.get(key);
            if (value instanceof LinkedTreeMap) {
                Integer padLen = maxLen-(prefix+connector+"1"+key).length();
                String formatString = key+("─".repeat(padLen));
                ViewComponent viewComponent = new ViewComponent(prefix+connector,branchIcon,formatString+end);
                add(viewComponent);
                String nextPrefix = prefix + "│   ";
                this.load((LinkedTreeMap<String,Object>) value, nextPrefix);
            }
            else {
                if (Objects.equals(key, lastKey))prefix = "└"+"─".repeat(prefix.length()-1);
                if (value != null) {
                    Integer padLen = maxLen-(prefix+connector+"1"+key + ":" + value).length();
                    String formatString = key + ":" + value+("─".repeat(padLen));
                    ViewComponent viewComponent = new ViewComponent(prefix+connector,leafIcon,formatString+end);
                    this.add(viewComponent);
                } else {
                    Integer padLen = maxLen-(prefix+connector+"1"+key).length();
                    String formatString = key+("─".repeat(padLen));
                    ViewComponent viewComponent = new ViewComponent(prefix+connector,leafIcon,formatString+end);
                    this.add(viewComponent);
                }

            }
        }
    }
    @Override
    public void show() {
        for (View view:child){
            view.show();
        }
    }
}
