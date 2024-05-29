package org.visualJson;

public class Icon{
    private String shape;

    public Icon(String shape) {
        this.shape = shape;
    }

    public void draw(){
        System.out.print(shape);
    }
}
