package com.skettios.summerproject.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.skettios.summerproject.gfx.render.IRenderable;

public class Background implements IRenderable
{
    private Texture texture;

    public Background(Texture texture)
    {
        this.texture = texture;
    }

    public void setTexture(Texture texture)
    {
        this.texture = texture;
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
}
