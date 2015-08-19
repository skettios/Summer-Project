package com.skettios.summerproject.world;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.skettios.summerproject.SummerProject;
import com.skettios.summerproject.gfx.Background;
import com.skettios.summerproject.util.Assets;
import com.skettios.summerproject.util.Constants;
import com.skettios.summerproject.util.Properties;

public class World
{
    private com.badlogic.gdx.physics.box2d.World world;

    private List<IWorldObject> worldObjects;
    private List<IWorldObject> worldObjectDestructionQueue;

    private boolean removeBounds = false;
    private Body bottomBound, topBound, leftBound, rightBound;

    private Background background;

    public World()
    {
        this.world = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, 0), true);
        this.worldObjects = new ArrayList<IWorldObject>();
        this.worldObjectDestructionQueue = new ArrayList<IWorldObject>();
        this.world.setContactListener(new WorldContactListener());

        if (Properties.DEBUG_MODE)
        {
            SummerProject.getInstance().renderEngine.setWorld(this);
        }
    }

    public void setBackground(Background background)
    {
        if (this.background != null)
        {
            SummerProject.getInstance().renderEngine.pop(this.background);
            if (background == null)
            {
                SummerProject.getInstance().renderEngine.pop(this.background);
                return;
            }
        }
        this.background = background;
        if (this.background != null)
        {
            SummerProject.getInstance().renderEngine.push(this.background);
        }
    }

    public void initialize()
    {
        setupBounds();
    }

    public void destroy()
    {
        if (worldObjects.size() > 0)
        {
            for (int i = 0; i < worldObjects.size(); i++)
            {
                pop(worldObjects.get(i));
            }
        }

        if (background != null)
        {
            setBackground(null);
        }

        Assets.stopAllMusic();

        removeBounds = true;
    }

    public void reset()
    {
        if (worldObjects.size() > 0)
        {
            for (int i = 0; i < worldObjects.size(); i++)
            {
                pop(worldObjects.get(i));
            }
        }

        if (background != null)
        {
            setBackground(null);
        }

        Assets.stopAllMusic();
    }

    private void setupBounds()
    {
        removeBounds = false;

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
        filter.categoryBits = Constants.CATEGORY_BOUNDARY;
        filter.maskBits = Constants.MASK_BOUNDARY;

        // Bottom Bound
        bottomBound = world.createBody(bodyDef);
        bottomBound.setTransform(0, -5, 0);
        Fixture bottomFixture = bottomBound.createFixture(widthBox, 1f);
        bottomFixture.setFilterData(filter);

        // Top Bound
        topBound = world.createBody(bodyDef);
        topBound.setTransform(0, 485, 0);
        Fixture topFixture = topBound.createFixture(widthBox, 1f);
        topFixture.setFilterData(filter);

        // Left Bound
        leftBound = world.createBody(bodyDef);
        leftBound.setTransform(-5, 0, 0);
        Fixture leftFixture = leftBound.createFixture(heightBox, 1f);
        leftFixture.setFilterData(filter);

        // Right Bound
        rightBound = world.createBody(bodyDef);
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

    public void pop(IWorldObject obj)
    {
        if (contains(obj))
        {
            worldObjectDestructionQueue.add(obj);
            SummerProject.getInstance().renderEngine.pop(obj);
        }
    }

    public boolean contains(IWorldObject obj)
    {
        return worldObjects.contains(obj);
    }

    public com.badlogic.gdx.physics.box2d.World getWorld()
    {
        return world;
    }

    private void destroyQueue()
    {
        if (worldObjectDestructionQueue.size() > 0)
        {
            for (int i = 0; i < worldObjectDestructionQueue.size(); i++)
            {
                SummerProject.getInstance().renderEngine.pop(worldObjectDestructionQueue.get(i));
                worldObjects.remove(worldObjectDestructionQueue.get(i));
                worldObjectDestructionQueue.get(i).destroy(this);
            }
        }

        worldObjectDestructionQueue.clear();
    }

    public void update(float deltaTime)
    {    
        if (removeBounds)
        {
            world.destroyBody(bottomBound);
            world.destroyBody(topBound);
            world.destroyBody(leftBound);
            world.destroyBody(rightBound);
        }

        destroyQueue();

        for (int i = 0; i < worldObjects.size(); i++)
        {
            worldObjects.get(i).update(deltaTime);
        }
        
        world.step(1f, 32, 12);
    }
}
