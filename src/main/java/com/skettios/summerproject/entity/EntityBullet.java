package com.skettios.summerproject.entity;

import com.badlogic.gdx.graphics.Texture;
import com.skettios.summerproject.world.World;

public abstract class EntityBullet extends Entity
{
    public EntityBullet(Texture texture)
    {
        super(texture);
    }
    
    @Override
    public void initialize(World world)
    {
        super.initialize(world);
        collisionBody.setBullet(true);
    }
}
