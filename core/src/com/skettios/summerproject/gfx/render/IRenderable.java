package com.skettios.summerproject.gfx.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IRenderable
{
    enum RenderType
    {
        BACKGROUND,
        MAP,
        MAP_OBJ,
        ENTITY,
        GUI
    }

    String getRenderName();

    void setRenderName(String renderName);

    RenderType getRenderType();

    void render(SpriteBatch batch);

    boolean destroy();
}
