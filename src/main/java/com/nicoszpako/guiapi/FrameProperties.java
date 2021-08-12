package com.nicoszpako.guiapi;

import javax.vecmath.Vector2f;

public class FrameProperties {

    private Vector2f frameOrigin = new Vector2f(0,0);

    public Vector2f getFrameOrigin() {
        return frameOrigin;
    }

    public void setFrameOrigin(Vector2f frameOrigin) {
        this.frameOrigin = frameOrigin;
    }
}
