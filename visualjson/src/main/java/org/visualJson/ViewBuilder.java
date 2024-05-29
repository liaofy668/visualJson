package org.visualJson;

public interface ViewBuilder {
    public void reset();
    public void setBranchIcon(String iconShape);
    public void setLeafIcon(String iconShape);

    public void loadJson(String filePath);

}
