package com.skettios.summerproject.entity.enemy.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.skettios.summerproject.entity.EntityBullet;
import com.skettios.summerproject.util.Constants;

public class EntityBulletEnemy extends EntityBullet
{
    public EntityBulletEnemy(Texture texture)
    {
        super(texture);
    }

    @Override
    public BodyType getBodyType()
    {
        return BodyType.DynamicBody;
    }

    @Override
    public short getCollisionCategory()
    {
        return Constants.CATEGORY_ENEMY_BULLET;
    }

    @Override
    public short getCollisionMask()
    {
        return Constants.MASK_ENEMY_BULLET;
    }

    @Override
    public void update(float deltaTime)
    {

    }
}
