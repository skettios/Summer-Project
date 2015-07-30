package com.skettios.summerproject.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.skettios.summerproject.entity.Entity;
import com.skettios.summerproject.pattern.Pattern;
import com.skettios.summerproject.util.Constants;

public class EntityEnemy extends Entity
{
    private Pattern pattern;
    
    public EntityEnemy(Texture texture)
    {
        super(texture);
    }

    public void setPattern(Pattern pattern)
    {
        this.pattern = pattern;
    }
    
    @Override
    public BodyType getBodyType()
    {
        return BodyType.KinematicBody;
    }

    @Override
    public short getCollisionCategory()
    {
        return Constants.CATEGORY_ENEMY;
    }

    @Override
    public short getCollisionMask()
    {
        return Constants.MASK_ENEMY;
    }

    @Override
    public void update(float deltaTime)
    {
        if (pattern != null)
        {
            pattern.update(deltaTime);
        }
    }
}
