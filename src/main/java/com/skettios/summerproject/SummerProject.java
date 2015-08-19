package com.skettios.summerproject;

import java.io.File;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.kotcrab.vis.ui.VisUI;
import com.naef.jnlua.NativeSupport;
import com.naef.jnlua.NativeSupport.Loader;
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

    public static void main(String[] args)
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 600;
        config.addIcon("icon.png", FileType.Internal);

        NativeLoader.load();

        new LwjglApplication(new SummerProject(), config);
    }
    
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

        Box2D.init();
        
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
    
    protected static class NativeLoader
    {
        public static void load()
        {
            SharedLibraryLoader loader = new SharedLibraryLoader();
            File nativesDir = null;
            try
            {
                if (SharedLibraryLoader.isWindows)
                {
                    if (SharedLibraryLoader.is64Bit)
                    {
                        nativesDir = loader.extractFile("windows/64-bit/lua52.dll", null).getParentFile();
                        loader.extractFile("windows/64-bit/jnlua52.dll", nativesDir.getName());
                        loader.extractFile("windows/64-bit/javavm.dll", nativesDir.getName());
                    }
                    else
                    {
                        // TODO(skettios): GET 32-BIT LUA WINDOWS BINARIES
                    }
                }

                if (SharedLibraryLoader.isLinux)
                {
                    if (SharedLibraryLoader.is64Bit)
                    {
                        // TODO(skettios): GET 64-BIT LUA LINUX BINARIES
                    }
                    else
                    {
                        // TODO(skettios): GET 32-BIT LUA LINUX BINARIES
                    }
                }

                if (SharedLibraryLoader.isMac)
                {
                    if (SharedLibraryLoader.is64Bit)
                    {
                        // TODO(skettios): GET 64-BIT LUA MAC BINARIES
                    }
                    else
                    {
                        // TODO(skettios): GET 32-BIT LUA MAC BINARIES
                    }
                }
            }
            catch (Throwable ex)
            {
                throw new GdxRuntimeException("Unable to extract JNLua natives.", ex);
            }

            final File finalNativesDir = nativesDir;
            NativeSupport.getInstance().setLoader(new Loader()
            {
                @Override
                public void load()
                {
                    System.load(finalNativesDir.getAbsolutePath() + "/lua52.dll");
                    System.load(finalNativesDir.getAbsolutePath() + "/jnlua52.dll");
                    System.load(finalNativesDir.getAbsolutePath() + "/javavm.dll");
                }
            });
        }
    }
}
