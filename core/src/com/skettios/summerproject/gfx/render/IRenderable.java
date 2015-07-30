package com.skettios.summerproject.gfx.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IRenderable
{
    enum RenderType
    {
        BACKGROUND,
        ENTITY,
        GUI
    }

    RenderType getRenderType();

    void render(SpriteBatch batch);
}
