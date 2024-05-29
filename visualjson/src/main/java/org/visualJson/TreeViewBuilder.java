package org.visualJson;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.FileReader;
import java.io.Reader;

public class TreeViewBuilder implements ViewBuilder {
    private TreeView treeView;
    private static TreeIconFactory iconFactory  = new TreeIconFactory();
    @Override
    public void reset() {
        this.treeView = new TreeView();
    }

    @Override
    public void setBranchIcon(String iconShape) {
        Icon icon = iconFactory.createIcon(iconShape);
        this.treeView.setBranchIcon(icon);
    }

    @Override
    public void setLeafIcon(String iconShape) {
        Icon icon = iconFactory.createIcon(iconShape);
        this.treeView.setLeafIcon(icon);
    }

    @Override
    public void loadJson(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            LinkedTreeMap<String,Object> tree = gson.fromJson(reader, LinkedTreeMap.class);
            this.treeView.load(tree,"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TreeView getResult() {
        TreeView ret = this.treeView;
        this.reset();
        return ret;
    }
}
