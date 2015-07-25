package com.skettios.summerproject.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.skettios.summerproject.SummerProject;
import com.skettios.summerproject.gfx.render.IRenderable;

public class Background implements IRenderable
{
    private String renderName;
    private Texture texture;

    public Background(String renderName, Texture texture)
    {
        this.renderName = renderName;
        this.texture = texture;
    }

    public void setTexture(Texture texture)
    {
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
        return RenderType.BACKGROUND;
    }

    @Override
    public void render(SpriteBatch batch)
    {
        batch.draw(texture, 0, 0, 640, 480);
    }

    @Override
    public boolean destroy()
    {
        SummerProject.getInstance().renderEngine.pop(this);
        return true;
    }
}
