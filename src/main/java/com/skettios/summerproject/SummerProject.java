package com.skettios.summerproject;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.kotcrab.vis.ui.VisUI;
import com.skettios.summerproject.gfx.render.RenderEngine;
import com.skettios.summerproject.level.LevelManager;
import com.skettios.summerproject.state.StateManager;
import com.skettios.summerproject.state.StateManager.State;
import com.skettios.summerproject.util.Assets;
import com.skettios.summerproject.util.Constants;
import com.skettios.summerproject.util.Logger;
import com.skettios.summerproject.util.Properties;

public class SummerProject extends ApplicationAdapter
{
    private static SummerProject instance;
    private StateManager stateManager;
    public RenderEngine renderEngine;

    @Override
    public void create()
    {
        configureGame();
        Assets.load();
        stateManager = new StateManager();
        renderEngine = new RenderEngine();
        instance = this;

        LevelManager.load();

        stateManager.push(State.MAIN_MENU);
        
        Logger.log(Constants.VERSION);
    }

    @Override
    public void render()
    {
        // Update
        handleGlobalKeyCombinations();
        stateManager.update(0.25f);

        // Render
        renderEngine.render();
    }

    public static SummerProject getInstance()
    {
        return instance;
    }

    private void configureGame()
    {
        Properties.load();

        VisUI.load();
        Gdx.input.setCursorCatched(true);

        Logger.setLogLevel(Properties.LOG_LEVEL);
        
        Logger.log("TEST");
    }

    private void handleGlobalKeyCombinations()
    {
        if ((Gdx.input.isKeyPressed(Keys.ALT_LEFT) || Gdx.input.isKeyPressed(Keys.ALT_RIGHT)) && Gdx.input.isKeyPressed(Keys.ENTER))
        {
            if (!Gdx.graphics.isFullscreen())
            {
                Gdx.graphics.setDisplayMode(800, 600, true);
            }
            else
            {
                Gdx.graphics.setDisplayMode(800, 600, false);
            }
        }
    }
}
