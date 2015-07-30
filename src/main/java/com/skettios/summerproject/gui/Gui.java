package com.skettios.summerproject.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.VisUI;
import com.skettios.summerproject.SummerProject;
import com.skettios.summerproject.gfx.render.IRenderable;
import com.skettios.summerproject.state.StateManager;

public abstract class Gui implements IRenderable
{
    protected String renderName;
    protected Stage stage;
    protected Table table;

    public Gui()
    {
        renderName = "gui";
        stage = new Stage(SummerProject.getInstance().renderEngine.guiView);
        table = new Table(VisUI.getSkin());
        Gdx.input.setInputProcessor(stage);
        table.setFillParent(true);
        initialize();
        stage.addActor(table);
    }

    protected abstract void initialize();

    @Override
    public RenderType getRenderType()
    {
        return RenderType.GUI;
    }

    public abstract void update(StateManager stateManager, float deltaTime);

    @Override
    public void render(SpriteBatch batch)
    {
        stage.draw();
    }
}
