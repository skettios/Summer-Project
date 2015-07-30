package com.skettios.summerproject.gfx.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.skettios.summerproject.gfx.render.IRenderable.RenderType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RenderLayer
{
    private RenderType type;
    private Viewport view;
    private SpriteBatch batch;

    private List<IRenderable> renderList;
    private Map<String, IRenderable> renderMap;

    public RenderLayer(RenderType type, Viewport view)
    {
        this.type = type;
        this.view = view;
        this.batch = new SpriteBatch();

        this.renderList = new ArrayList<IRenderable>();
        this.renderMap = new HashMap<String, IRenderable>();
    }

    public RenderType getRenderType()
    {
        return type;
    }

    public void push(IRenderable renderable)
    {
        renderList.add(renderable);
    }

    public void pop(IRenderable renderable)
    {
        renderList.remove(renderable);
    }

    public Map<String, IRenderable> getRenderMap()
    {
        return renderMap;
    }

    public void render()
    {
        view.apply();

        batch.setProjectionMatrix(view.getCamera().combined);

        batch.begin();
        {
            for (IRenderable renderable : renderList)
            {
                renderable.render(batch);
            }
        }
        batch.end();
    }
}
