package com.nicoszpako.guiapi.style;

public class Style {

    private int color = 0xffFFFFFF;
    private int hoverColor = 0xffFFFFFF;

    private int backgroundColor = 0x0;
    private int hoverBackgroundColor = 0x0;

    private int borderColor = 0xff000000;
    private int hoverBorderColor = 0xff000000;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getHoverColor() {
        return hoverColor;
    }

    public void setHoverColor(int hoverColor) {
        this.hoverColor = hoverColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(int hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public int getHoverBorderColor() {
        return hoverBorderColor;
    }

    public void setHoverBorderColor(int hoverBorderColor) {
        this.hoverBorderColor = hoverBorderColor;
    }
}
