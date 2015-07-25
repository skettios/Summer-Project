package com.skettios.summerproject.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.skettios.summerproject.gfx.render.IRenderable;

public class GuiBackground implements IRenderable
{
    private String renderName;
    private Texture texture;

    public GuiBackground(String renderName, Texture texture)
    {
        this.renderName = renderName;
        this.texture = texture;
    }

    @Override
    public String getRenderName()
    {
        return renderName;
    }

    @Override
    public void setRenderName(String renderName)
    {
        this.renderName = renderName;
    }

    @Override
    public RenderType getRenderType()
    {
        return RenderType.GUI;
    }

    @Override
    public void render(SpriteBatch batch)
    {
        batch.draw(texture, 500, 0);
    }

    @Override
    public boolean destroy()
    {
        return false;
    }
}
