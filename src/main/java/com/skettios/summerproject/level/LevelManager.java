package com.skettios.summerproject.level;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.skettios.summerproject.lua.LuaHandler;
import com.skettios.summerproject.util.Assets;
import com.skettios.summerproject.world.World;

import java.util.HashMap;
import java.util.Map;

public final class LevelManager
{
    private static Map<String, FileHandle> levelMap = new HashMap<String, FileHandle>();

    private static World world;
    private static String currentLevel;
    private static boolean isChangingLevel = false;

    public static void setLevel(String level)
    {
        isChangingLevel = true;

        world.reset();

        currentLevel = level;
        Timer.schedule(new Task()
        {
            @Override
            public void run()
            {
                start();
                cancel();
            }
        }, 0.0001f);

        isChangingLevel = false;
    }

    public static void setWorld(World world)
    {
        LevelManager.world = world;
    }

    public static void load()
    {
        for (String alias : Assets.levels.keySet())
        {
            levelMap.put(alias, Assets.getLevel(alias));
        }
    }

    private static void start()
    {
        if (currentLevel != null && !currentLevel.isEmpty())
        {
            LuaHandler.evaluateScript(levelMap.get(currentLevel));
            LuaHandler.callFunction("start", world);
        }
    }

    public static void update(float deltaTime)
    {
        if (currentLevel != null && !isChangingLevel)
        {
            LuaHandler.callFunction("loop", world, deltaTime);
        }
    }
}
