package com.skettios.summerproject.util.factory;

import com.badlogic.gdx.graphics.Texture;
import com.skettios.summerproject.gfx.Background;

public final class GraphicFactory
{
    public static Background createBackground(Texture texture)
    {
        return new Background(texture);
    }
}
