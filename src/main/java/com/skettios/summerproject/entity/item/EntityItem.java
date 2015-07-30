package com.skettios.summerproject.entity.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.skettios.summerproject.entity.Entity;
import com.skettios.summerproject.util.Constants;

public class EntityItem extends Entity
{
    public EntityItem(Texture texture)
    {
        super(texture);
    }

    @Override
    public BodyType getBodyType()
    {
        return BodyType.KinematicBody;
    }

    @Override
    public short getCollisionCategory()
    {
        return Constants.CATEGORY_ITEM;
    }

    @Override
    public short getCollisionMask()
    {
        return Constants.MASK_ITEM;
    }

    @Override
    public void update(float deltaTime)
    {

    }
}
