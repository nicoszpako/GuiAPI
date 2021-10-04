package com.nicoszpako.guiapi.listeners;

import com.nicoszpako.guiapi.EnumMouseInteraction;

@FunctionalInterface
public interface IMouseListener {

    void mouseEvent(EnumMouseInteraction mouseInteraction);

}
