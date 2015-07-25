package com.skettios.summerproject.gfx.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.skettios.summerproject.gfx.render.IRenderable.RenderType;
import com.skettios.summerproject.util.Properties;
import com.skettios.summerproject.world.World;

import java.util.ArrayList;
import java.util.List;

public class RenderEngine
{
    private Box2DDebugRenderer worldDebugRenderer;
    private World world;

    private List<RenderLayer> layers;

    private Viewport gameView;
    private Viewport guiView;

    protected RenderLayer background;
    protected RenderLayer map;
    protected RenderLayer map_obj;
    protected RenderLayer actor;
    protected RenderLayer gui;

    public RenderEngine()
    {
        worldDebugRenderer = new Box2DDebugRenderer();

        layers = new ArrayList<RenderLayer>();

        gameView = new ScalingViewport(Scaling.fit, 640, 480);
        guiView = new ScalingViewport(Scaling.fit, 800, 600);

        gameView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        guiView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        background = addLayer(new RenderLayer(RenderType.BACKGROUND, gameView));
        map = addLayer(new RenderLayer(RenderType.MAP, gameView));
        map_obj = addLayer(new RenderLayer(RenderType.MAP_OBJ, gameView));
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
        String renderName = renderable.getRenderName();
        boolean containsIndex = false;

        for (RenderLayer layer : layers)
        {
            String layerRenderName = renderName;
            if (!renderName.contains(layer.getRenderType().name()))
            {
                layerRenderName = layer.getRenderType() + "_" + renderName;
            }
            String indexString = layerRenderName.substring(layerRenderName.lastIndexOf('_') + 1);
            for (int i = 0; i < indexString.length(); i++)
            {
                containsIndex = Character.isDigit(indexString.charAt(i));
            }
            int layerRenderIndex = 0;
            if (layer.getRenderType() == renderable.getRenderType())
            {
                if (!containsIndex)
                {
                    for (IRenderable layerRenderable : layer.getRenderMap().values())
                    {
                        if (layerRenderable.getRenderName().contains(layerRenderName))
                        {
                            layerRenderIndex++;
                        }
                    }

                    layerRenderName += "_" + layerRenderIndex;
                }

                renderable.setRenderName(layerRenderName);
                layer.push(layerRenderName, renderable);
            }
        }
    }

    public IRenderable pop(IRenderable renderable)
    {
        String renderName = renderable.getRenderName();

        for (RenderLayer layer : layers)
        {
            if (layer.getRenderType() == renderable.getRenderType())
            {
                return layer.pop(renderName);
            }
        }

        return null;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

    public void render()
    {
        gameView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        guiView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT | Gdx.gl.GL_DEPTH_BUFFER_BIT);

        for (RenderLayer layer : layers)
        {
            layer.render();
        }

        if (Properties.DEBUG_MODE)
        {
            worldDebugRenderer.render(world.getWorld(), gameView.getCamera().combined);
        }
    }
}
