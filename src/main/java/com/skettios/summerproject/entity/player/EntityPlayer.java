package com.skettios.summerproject.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.skettios.summerproject.entity.Entity;
import com.skettios.summerproject.util.Assets;
import com.skettios.summerproject.util.Constants;
import com.skettios.summerproject.util.factory.EntityFactory;
import com.skettios.summerproject.world.World;

public class EntityPlayer extends Entity
{
    public EntityPlayer(Texture texture)
    {
        super(texture);
    }

    @Override
    public void initialize(World world)
    {
        super.initialize(world);

        CircleShape hitCircle = new CircleShape();
        hitCircle.setRadius(sprite.getWidth() / (2 / sprite.getScaleX()) * 0.25f);
        hitCircle.setPosition(new Vector2(sprite.getWidth() / 2, sprite.getHeight() / 2));

        Filter hitFilter = new Filter();
        hitFilter.categoryBits = Constants.CATEGORY_PLAYER_HIT;
        hitFilter.maskBits = Constants.MASK_PLAYER_HIT;

        FixtureDef hitFixtureDef = new FixtureDef();
        hitFixtureDef.density = 0.005f;
        hitFixtureDef.shape = hitCircle;
        hitFixtureDef.friction = 0f;
        hitFixtureDef.filter.categoryBits = Constants.CATEGORY_PLAYER_HIT;
        hitFixtureDef.filter.maskBits = Constants.MASK_PLAYER_HIT;

        collisionBody.createFixture(hitFixtureDef);

        hitCircle.dispose();
    }

    int bulletCounter = 0;

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

        if (Gdx.input.isKeyPressed(Keys.Z))
        {
            if (bulletCounter == 5)
            {
                world.push(EntityFactory.createPlayerBullet(this, Assets.getTexture("player_bullet"), new Vector2(0, Integer.MAX_VALUE)));
                bulletCounter = 0;
            }

            bulletCounter++;
        }
        else if (!Gdx.input.isKeyPressed(Keys.Z))
        {
            bulletCounter = 5;
        }

        collisionBody.setLinearVelocity(velocity);
    }

    @Override
    public BodyType getBodyType()
    {
        return BodyType.DynamicBody;
    }

    @Override
    public short getCollisionCategory()
    {
        return Constants.CATEGORY_PLAYER;
    }

    @Override
    public short getCollisionMask()
    {
        return Constants.MASK_PLAYER;
    }
}
