package org.visualJson;

public class RectViewFactory implements ViewFactory {
    private static ViewDirector viewDirector = new ViewDirector();
    @Override
    public View createView(String filePath,String iconFamilyOption) {
        RectViewBuilder builder = new RectViewBuilder();
        viewDirector.constructRectView(builder,filePath,iconFamilyOption);
        return builder.getResult();
    }
}
