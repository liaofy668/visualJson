package org.visualJson;

public class RectIconFactory implements IconFactory {
    @Override
    public Icon createIcon(String name) {
        return new Icon(name);
    }
}
