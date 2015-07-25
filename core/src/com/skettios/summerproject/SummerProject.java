package com.skettios.summerproject;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.naef.jnlua.script.LuaScriptEngineFactory;
import com.skettios.summerproject.entity.Entity;
import com.skettios.summerproject.entity.EntityPlayer;
import com.skettios.summerproject.gfx.Background;
import com.skettios.summerproject.gfx.GuiBackground;
import com.skettios.summerproject.gfx.render.RenderEngine;
import com.skettios.summerproject.state.StateManager;
import com.skettios.summerproject.util.Assets;
import com.skettios.summerproject.util.Logger;
import com.skettios.summerproject.util.Properties;
import com.skettios.summerproject.world.World;

import javax.script.Compilable;
import javax.script.Invocable;
import javax.script.ScriptEngine;

public class SummerProject extends ApplicationAdapter
{
    private static SummerProject instance;
    private StateManager stateManager;
    public RenderEngine renderEngine;

    private World world;
    private Background background;
    private GuiBackground guiBackground;
    private Entity test;

    private LuaScriptEngineFactory factory;
    private ScriptEngine engine;

    @Override
    public void create()
    {
        configureGame();
        Assets.load();
        stateManager = new StateManager();
        renderEngine = new RenderEngine();
        instance = this;

        world = new World();
        background = new Background("bg", Assets.getTexture("level_0"));
        guiBackground = new GuiBackground("gui_bg", Assets.getTexture("sidebar"));
        test = new EntityPlayer();
        world.push(test);

        renderEngine.push(background);
        renderEngine.push(guiBackground);

        Music music = Assets.getMusic("yet_another_drizzly_rain");
        music.play();

        try
        {
            factory = new LuaScriptEngineFactory();
            engine = factory.getScriptEngine();
            ((Compilable) engine).compile(Gdx.files.absolute("scripts/start.lua").reader()).eval();
            ((Invocable) engine).invokeFunction("start");
        }
        catch (Exception e)
        {
            Logger.error(e);
        }
    }

    int counter = 0;

    @Override
    public void render()
    {
        // Update
        handleGlobalKeyCombinations();
        stateManager.update(Gdx.graphics.getDeltaTime());
        world.update(Gdx.graphics.getDeltaTime());

        // Render
        renderEngine.render();

        counter++;
    }

    public static SummerProject getInstance()
    {
        return instance;
    }

    private void configureGame()
    {
        Properties.load();

        Logger.setLogLevel(Properties.LOG_LEVEL);
        Logger.setLogToFile(Properties.LOG_TO_FILE);
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
