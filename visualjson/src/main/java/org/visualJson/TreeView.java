package org.visualJson;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

public class TreeView implements View {

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

    public void load(LinkedTreeMap<String, Object> tree,String prefix) {
        for (String key : tree.keySet()) {
            String connector = "└─ ";
            if (!key.equals(tree.keySet().toArray()[tree.size() - 1])) {
                connector = "├─ ";
            }
            Object value = tree.get(key);
            if (value instanceof LinkedTreeMap) {
                ViewComponent viewComponent = new ViewComponent(prefix+connector,branchIcon,key);
                add(viewComponent);
//                System.out.println(prefix + connector + key);
                String nextPrefix = prefix + (key.equals(tree.keySet().toArray()[tree.size() - 1]) ? "    " : "│   ");
                this.load((LinkedTreeMap<String,Object>) value, nextPrefix);
//                printTree((LinkedTreeMap<String,Object>) value, nextPrefix);
            }
            else {
                if (value != null) {
                    ViewComponent viewComponent = new ViewComponent(prefix+connector,leafIcon,key + ":" + value);
                    this.add(viewComponent);
//                    System.out.println(prefix + connector + key + ":" + value);
                } else {
                    ViewComponent viewComponent = new ViewComponent(prefix+connector,leafIcon,key);
                    this.add(viewComponent);
//                    System.out.println(prefix + connector + key);
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
