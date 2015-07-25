package com.skettios.summerproject.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.skettios.summerproject.util.Constants;
import com.skettios.summerproject.world.IWorldObject;
import com.skettios.summerproject.world.World;

public abstract class Entity implements IWorldObject
{
    protected Sprite sprite;
    protected String renderName;
    protected World world;
    protected Body collisionBody;
    protected Fixture collisionFixture;

    public Entity(String renderName, Texture texture)
    {
        sprite = new Sprite(texture);
        this.renderName = renderName;
    }

    @Override
    public void initialize(World world)
    {
        this.world = world;
        PolygonShape collisionBox = new PolygonShape();
        collisionBox.setAsBox(sprite.getWidth() / (2 / sprite.getScaleX()), sprite.getHeight() / (2 / sprite.getScaleY()), new Vector2(sprite.getOriginX(), sprite.getOriginY()), sprite.getRotation());

        BodyDef collisionBodyDef = new BodyDef();
        collisionBodyDef.type = BodyType.DynamicBody;
        collisionBodyDef.fixedRotation = true;

        collisionBody = world.getWorld().createBody(collisionBodyDef);
        collisionBody.setTransform(sprite.getX(), sprite.getY(), 0f);

        FixtureDef collisionFixtureDef = new FixtureDef();
        collisionFixtureDef.density = 0.005f;
        collisionFixtureDef.shape = collisionBox;
        collisionFixtureDef.friction = 0f;

        Filter filter = new Filter();
        filter.categoryBits = Constants.CATEGORY_PLAYER;
        filter.maskBits = Constants.MASK_PLAYER;

        collisionFixture = collisionBody.createFixture(collisionFixtureDef);
        collisionFixture.setFilterData(filter);

        collisionBox.dispose();
    }

    @Override
    public String getRenderName()
    {
        return renderName;
    }

    @Override
    public void setRenderName(String renderName)
    {
        this.renderName = renderName;
    }

    @Override
    public RenderType getRenderType()
    {
        return RenderType.ENTITY;
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
    public boolean destroy()
    {
        if (world == null)
        {
            return false;
        }

        world.pop(this);
        return true;
    }
}
