package com.skettios.summerproject.world;

import com.skettios.summerproject.gfx.render.IRenderable;
import com.sun.media.jfxmediaimpl.MediaDisposer.Disposable;

public interface IWorldObject extends IRenderable, Disposable
{
    /**
     * Initialize the object into the world.
     * Give it a body and what not.
     *
     * @param world
     */
    void initialize(World world);

    /**
     * Update the object in the world.
     *
     * @param deltaTime
     */
    void update(float deltaTime);

    /**
     * Called to pop the object from the world.
     */
    @Override
    void dispose();

    /**
     * Removes the body from the world.
     *
     * @param world
     */
    void destroy(World world);
}
