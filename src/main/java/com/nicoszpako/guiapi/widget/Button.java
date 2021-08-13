package com.nicoszpako.guiapi.widget;

import com.nicoszpako.guiapi.EnumMouseInteraction;
import com.nicoszpako.guiapi.listeners.IMouseListener;
import com.nicoszpako.guiapi.widget.container.Container;

public class Button extends Container implements IMouseListener {

    public Button(String text){
        setFixed(false);
        setText(text);
    }

    public Button(){
    }

    public void click(){

    }

    @Override
    public void mouseEvent(EnumMouseInteraction mouseInteraction) {
        if(mouseInteraction == EnumMouseInteraction.LEFT_CLICK){
            click();
        }
    }

    public void setText(String text){
        getWidgets().clear();
        Label label = new Label(text);
        add(label);
    }

}
