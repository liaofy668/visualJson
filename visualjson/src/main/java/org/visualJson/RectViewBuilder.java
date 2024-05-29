package org.visualJson;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.FileReader;
import java.io.Reader;

public class RectViewBuilder implements ViewBuilder {
    private RectView view;
    private static RectIconFactory iconFactory  = new RectIconFactory();
    @Override
    public void reset() {
        this.view = new RectView();
    }
    @Override
    public void setBranchIcon(String iconShape) {
        Icon icon = iconFactory.createIcon(iconShape);
        this.view.setBranchIcon(icon);
    }
    @Override
    public void setLeafIcon(String iconShape) {
        Icon icon = iconFactory.createIcon(iconShape);
        this.view.setLeafIcon(icon);
    }
    @Override
    public void loadJson(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            LinkedTreeMap<String,Object> tree = gson.fromJson(reader, LinkedTreeMap.class);
            this.view.load(tree,"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public RectView getResult() {
        RectView ret = this.view;
        this.reset();
        return ret;
    }
}
