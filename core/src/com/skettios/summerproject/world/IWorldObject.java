package com.skettios.summerproject.world;

import com.skettios.summerproject.gfx.render.IRenderable;

public interface IWorldObject extends IRenderable
{
    void initialize(World world);

    void update(float deltaTime);
}
