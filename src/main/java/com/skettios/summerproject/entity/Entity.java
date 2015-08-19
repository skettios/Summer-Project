package com.skettios.summerproject.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.skettios.summerproject.world.IWorldObject;
import com.skettios.summerproject.world.World;

public abstract class Entity implements IWorldObject
{
    public Sprite sprite;
    protected Vector2 initialPos = Vector2.Zero;
    protected World world;
    protected float collisionBoxScale = 1f;
    protected Body collisionBody;
    protected Fixture collisionFixture;

    public Entity(Texture texture)
    {
        sprite = new Sprite(texture);
    }

    @Override
    public void initialize(World world)
    {
        this.world = world;
        PolygonShape collisionBox = new PolygonShape();
        collisionBox.setAsBox(sprite.getWidth() / (2 / sprite.getScaleX()) * collisionBoxScale, sprite.getHeight() / (2 / sprite.getScaleY()) * collisionBoxScale, new Vector2(sprite.getOriginX(), sprite.getOriginY()), sprite.getRotation());

        BodyDef collisionBodyDef = new BodyDef();
        collisionBodyDef.type = getBodyType();
        collisionBodyDef.fixedRotation = true;

        collisionBody = world.getWorld().createBody(collisionBodyDef);
        collisionBody.setTransform(initialPos.x, initialPos.y, 0f);
        collisionBody.setUserData(getClass());
        collisionBody.setSleepingAllowed(true);

        FixtureDef collisionFixtureDef = new FixtureDef();
        collisionFixtureDef.density = 0f;
        collisionFixtureDef.shape = collisionBox;
        collisionFixtureDef.friction = 0f;
        collisionFixtureDef.filter.categoryBits = getCollisionCategory();
        collisionFixtureDef.filter.maskBits = getCollisionMask();

        collisionFixture = collisionBody.createFixture(collisionFixtureDef);
        collisionFixture.setUserData(this);

        collisionBox.dispose();
    }

    public abstract BodyType getBodyType();

    public abstract short getCollisionCategory();

    public abstract short getCollisionMask();

    @Override
    public RenderType getRenderType()
    {
        return RenderType.ENTITY;
    }

    public Texture getTexture()
    {
        return sprite.getTexture();
    }

    public Entity setInitialPosition(float x, float y)
    {
        initialPos = new Vector2(x, y);
        return this;
    }

    public Entity setInitialPosition(Vector2 pos)
    {
        initialPos = pos;
        return this;
    }

    public Entity setCollisionBoxScale(float scale)
    {
        collisionBoxScale = scale;
        return this;
    }

    public Entity setColor(Color color)
    {
        sprite.setColor(color);
        return this;
    }

    public Entity setPosition(float x, float y)
    {
        sprite.setPosition(x, y);
        return this;
    }

    @Override
    public void render(SpriteBatch batch)
    {
        sprite.setPosition(collisionBody.getTransform().getPosition().x, collisionBody.getTransform().getPosition().y);
        sprite.draw(batch);
    }

    @Override
    public void dispose()
    {
        world.pop(this);
    }

    @Override
    public void destroy(World world)
    {
        world.getWorld().destroyBody(collisionBody);
    }
}
