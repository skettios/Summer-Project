package com.skettios.summerproject.gfx.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.skettios.summerproject.gfx.render.IRenderable.RenderType;

import java.util.HashMap;
import java.util.Map;

public class RenderLayer
{
    private RenderType type;
    private Viewport view;
    private SpriteBatch batch;

    private Map<String, IRenderable> renderMap;

    public RenderLayer(RenderType type, Viewport view)
    {
        this.type = type;
        this.view = view;
        this.batch = new SpriteBatch();

        this.renderMap = new HashMap<String, IRenderable>();
    }

    public RenderType getRenderType()
    {
        return type;
    }

    public void push(String alias, IRenderable renderable)
    {
        renderMap.put(alias, renderable);
    }

    public IRenderable pop(String alias)
    {
        return renderMap.remove(alias);
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
            for (String alias : renderMap.keySet())
            {
                renderMap.get(alias).render(batch);
            }
        }
        batch.end();
    }
}
