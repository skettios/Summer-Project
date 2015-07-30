package com.skettios.summerproject.lua;

import java.util.Random;

import javax.script.SimpleBindings;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.skettios.summerproject.level.LevelManager;
import com.skettios.summerproject.util.Assets;
import com.skettios.summerproject.util.Logger;
import com.skettios.summerproject.util.factory.EntityFactory;
import com.skettios.summerproject.util.factory.GraphicFactory;

public class LuaEngineBindings extends SimpleBindings
{
    public LuaEngineBindings()
    {
        super();
        // Java Classes
        put("Math", Math.class);
        put("Random", Random.class);
        put("System", System.class);
        
        // Gdx Classes
        put("Color", Color.class);
        put("Sprite", Sprite.class);
        put("Texture", Texture.class);
        
        // Summer Project Classes
        put("Assets", Assets.class);
        put("EntityFactory", EntityFactory.class);
        put("GraphicFactory", GraphicFactory.class);
        put("LevelManager", LevelManager.class);
        put("Logger", Logger.class);
    }
}
