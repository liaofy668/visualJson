package org.visualJson;

public class TreeIconFactory implements IconFactory {
    @Override
    public Icon createIcon(String name) {
        return new Icon(name);
    }
}
