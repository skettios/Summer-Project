package com.skettios.summerproject.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.skettios.summerproject.util.Assets;

public class EntityPlayer extends Entity
{
    public EntityPlayer()
    {
        super("player", Assets.getTexture("player"));
    }

    @Override
    public void update(float deltaTime)
    {
        Vector2 velocity = Vector2.Zero;

        if (Gdx.input.isKeyPressed(Keys.LEFT))
        {
            velocity.x = -150;
        }
        else if (Gdx.input.isKeyPressed(Keys.RIGHT))
        {
            velocity.x = 150;
        }
        else
        {
            velocity.x = 0;
        }

        if (Gdx.input.isKeyPressed(Keys.UP))
        {
            velocity.y = 150;
        }
        else if (Gdx.input.isKeyPressed(Keys.DOWN))
        {
            velocity.y = -150;
        }
        else
        {
            velocity.y = 0;
        }

        collisionBody.setLinearVelocity(velocity);
    }
}
