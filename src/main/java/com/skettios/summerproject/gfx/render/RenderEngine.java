package com.skettios.summerproject.gfx.render;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.skettios.summerproject.gfx.render.IRenderable.RenderType;
import com.skettios.summerproject.util.Properties;
import com.skettios.summerproject.world.World;

public class RenderEngine
{
    private Box2DDebugRenderer worldDebugRenderer;
    private World world;

    private List<RenderLayer> layers;

    public RenderLayer background;
    public RenderLayer actor;
    public RenderLayer gui;

    public Viewport gameView;
    public Viewport guiView;

    public RenderEngine()
    {
        worldDebugRenderer = new Box2DDebugRenderer();

        layers = new ArrayList<RenderLayer>();

        gameView = new ScalingViewport(Scaling.fit, 640, 480);
        guiView = new ScalingViewport(Scaling.fit, 800, 600);

        gameView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        guiView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        background = addLayer(new RenderLayer(RenderType.BACKGROUND, gameView));
        actor = addLayer(new RenderLayer(RenderType.ENTITY, gameView));
        gui = addLayer(new RenderLayer(RenderType.GUI, guiView));
    }

    protected RenderLayer addLayer(RenderLayer layer)
    {
        layers.add(layer);
        return layer;
    }

    public void push(IRenderable renderable)
    {
        for (RenderLayer layer : layers)
        {
            if (layer.getRenderType() == renderable.getRenderType())
            {
                layer.push(renderable);
            }
        }
    }

    public void pop(IRenderable renderable)
    {
        for (RenderLayer layer : layers)
        {
            if (layer.getRenderType() == renderable.getRenderType())
            {
                layer.pop(renderable);
            }
        }
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

    public void render()
    {
        gameView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        guiView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        for (RenderLayer layer : layers)
        {
            layer.render();
        }

        if (worldDebugRenderer != null && Properties.DEBUG_MODE)
        {
            worldDebugRenderer.render(world.getWorld(), gameView.getCamera().combined);
        }
    }
}
