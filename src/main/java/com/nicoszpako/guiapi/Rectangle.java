package com.nicoszpako.guiapi;

import javax.vecmath.Vector2f;

public class Rectangle {

    private float left, top, right, bottom;

    public Rectangle() {
    }

    public Rectangle(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public boolean isPointInside(double x, double y) {
        return x >= getLeft() && x <= getRight() && y >= getTop() && y <= getBottom();
    }

    public boolean isPointInsideExpandedRectangle(Rectangle expanding, double x, double y) {
        return x >= getLeft() - expanding.getLeft() && x <= getRight() + expanding.getRight() && y >= getTop() - expanding.getTop() && y <= getBottom() + expanding.getBottom();
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getWidth(){
        return getRight() - getLeft();
    }

    public void setWidth(float width){
        setRight(getLeft() + width);
    }

    public float getHeight(){
        return getBottom() - getTop();
    }

    public void setHeight(float height){
        setBottom(getTop() + height);
    }

    public void move(float x, float y){
        setRight(x + getWidth());
        setBottom(y + getHeight());
        setLeft(x);
        setTop(y);
    }

    /**
     * Returns a new rectangle expanded by the given amount in all directions.
     * @param amount
     * @return a new rectangle expanded by the given amount in all directions.
     */
    public Rectangle expand(float amount){
        return new Rectangle(getLeft()-amount,getTop()-amount,getRight()+amount,getBottom()+amount);
    }

    public Vector2f getOrigin() {
        return new Vector2f(getLeft(),getTop());
    }

    public void move(Vector2f origin) {
        move(origin.x, origin.y);
    }

    public void add(Vector2f vector) {
        setLeft(getLeft() + vector.getX());
        setTop(getTop() + vector.getY());
        setRight(getRight() + vector.getX());
        setBottom(getBottom() + vector.getY());
    }

    public void add(float x, float y) {
        setLeft(getLeft() + x);
        setTop(getTop() + y);
        setRight(getRight() + x);
        setBottom(getBottom() + y);
    }

    @Override
    public String toString() {
        return "("+left+","+top+") -> ("+right+","+bottom+")";
    }

}
