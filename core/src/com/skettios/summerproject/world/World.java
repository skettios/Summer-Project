package com.skettios.summerproject.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.skettios.summerproject.SummerProject;
import com.skettios.summerproject.util.Constants;
import com.skettios.summerproject.util.Properties;

import java.util.ArrayList;
import java.util.List;

public class World
{
    private com.badlogic.gdx.physics.box2d.World world;

    private List<IWorldObject> worldObjects;

    public World()
    {
        this.world = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, 0), true);
        this.worldObjects = new ArrayList<IWorldObject>();

        setupBounds();

        if (Properties.DEBUG_MODE)
        {
            SummerProject.getInstance().renderEngine.setWorld(this);
        }
    }

    private void setupBounds()
    {
        // Body Definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.fixedRotation = true;

        // Width Collision Box
        PolygonShape widthBox = new PolygonShape();
        widthBox.setAsBox(640, 5);

        // Height Collision Box
        PolygonShape heightBox = new PolygonShape();
        heightBox.setAsBox(5, 480);

        // Filter
        Filter filter = new Filter();
        filter.categoryBits = Constants.CATEGORY_PLATFORM;
        filter.maskBits = Constants.MASK_PLATFORM;

        // Bottom Bound
        Body bottomBound = world.createBody(bodyDef);
        bottomBound.setTransform(0, -5, 0);
        Fixture bottomFixture = bottomBound.createFixture(widthBox, 1f);
        bottomFixture.setFilterData(filter);

        // Top Bound
        Body topBound = world.createBody(bodyDef);
        topBound.setTransform(0, 485, 0);
        Fixture topFixture = topBound.createFixture(widthBox, 1f);
        topFixture.setFilterData(filter);

        // Left Bound
        Body leftBound = world.createBody(bodyDef);
        leftBound.setTransform(-5, 0, 0);
        Fixture leftFixture = leftBound.createFixture(heightBox, 1f);
        leftFixture.setFilterData(filter);

        // Right Bound
        Body rightBound = world.createBody(bodyDef);
        rightBound.setTransform(405, 0, 0);
        Fixture rightFixture = rightBound.createFixture(heightBox, 1f);
        rightFixture.setFilterData(filter);

        // Dispose
        widthBox.dispose();
        heightBox.dispose();
    }

    public void push(IWorldObject obj)
    {
        obj.initialize(this);
        SummerProject.getInstance().renderEngine.push(obj);
        worldObjects.add(obj);
    }

    public IWorldObject pop(IWorldObject obj)
    {
        SummerProject.getInstance().renderEngine.pop(obj);
        return worldObjects.remove(worldObjects.indexOf(obj));
    }

    public com.badlogic.gdx.physics.box2d.World getWorld()
    {
        return world;
    }

    public void update(float deltaTime)
    {
        for (int i = 0; i < worldObjects.size(); i++)
        {
            worldObjects.get(i).update(deltaTime);
        }

        world.step(1f / 20f, 6, 2);
    }
}
