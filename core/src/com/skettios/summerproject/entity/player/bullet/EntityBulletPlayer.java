package com.skettios.summerproject.entity.player.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.skettios.summerproject.entity.EntityBullet;
import com.skettios.summerproject.entity.player.EntityPlayer;
import com.skettios.summerproject.util.Constants;

public class EntityBulletPlayer extends EntityBullet
{
    private Vector2 velocity;

    public EntityBulletPlayer(EntityPlayer player, Texture texture, Vector2 velocity)
    {
        super(texture);
        this.velocity = velocity;
        initialPos = new Vector2((player.sprite.getX() + player.sprite.getWidth() / 2) - sprite.getWidth() / 2, (player.sprite.getY() + player.sprite.getHeight() / 2) - sprite.getHeight() / 2);
    }

    @Override
    public BodyType getBodyType()
    {
        return BodyType.DynamicBody;
    }

    @Override
    public short getCollisionCategory()
    {
        return Constants.CATEGORY_PLAYER_BULLET;
    }

    @Override
    public short getCollisionMask()
    {
        return Constants.MASK_PLAYER_BULLET;
    }

    @Override
    public void update(float deltaTime)
    {
        collisionBody.setLinearVelocity(velocity);
    }
}
