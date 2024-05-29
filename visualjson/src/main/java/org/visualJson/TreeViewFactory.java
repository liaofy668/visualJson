package org.visualJson;

public class TreeViewFactory implements ViewFactory {
    private static ViewDirector viewDirector = new ViewDirector();
    @Override
    public View createView(String filePath,String iconFamilyOption) {
        TreeViewBuilder builder = new TreeViewBuilder();
        viewDirector.constructTreeView(builder,filePath,iconFamilyOption);
        return builder.getResult();
    }
}
