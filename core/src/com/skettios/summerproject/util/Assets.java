package com.skettios.summerproject.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Assets
{
    private static AssetManager assetManager = new AssetManager();

    private static Map<String, String> textureMap;
    private static Map<String, String> soundMap;
    private static Map<String, String> musicMap;

    private static FileHandle textureManifest = Gdx.files.classpath("com/skettios/summerproject/util/manifest/textures.json");
    private static FileHandle soundManifest = Gdx.files.classpath("com/skettios/summerproject/util/manifest/sounds.json");
    private static FileHandle musicManifest = Gdx.files.classpath("com/skettios/summerproject/util/manifest/music.json");

    public static void load()
    {
        textureMap = getMapFromJson(textureManifest);
        soundMap = getMapFromJson(soundManifest);
        musicMap = getMapFromJson(musicManifest);

        for (String alias : textureMap.keySet())
        {
            assetManager.load("content/textures/" + textureMap.get(alias), Texture.class);
            assetManager.finishLoadingAsset("content/textures/" + textureMap.get(alias));
            Logger.debug("Loaded Texture: " + alias);
        }
        for (String alias : soundMap.keySet())
        {
            assetManager.load("content/sounds/" + soundMap.get(alias), Sound.class);
            assetManager.finishLoadingAsset("content/sounds/" + soundMap.get(alias));
            Logger.debug("Loaded Sound: " + alias);
        }
        for (String alias : musicMap.keySet())
        {
            assetManager.load("content/music/" + musicMap.get(alias), Music.class);
            assetManager.finishLoadingAsset("content/music/" + musicMap.get(alias));
            Logger.debug("Loaded Music: " + alias);
        }
    }

    public static Texture getTexture(String alias)
    {
        return assetManager.get("content/textures/" + textureMap.get(alias), Texture.class);
    }

    public static Sound getSound(String alias)
    {
        return assetManager.get("content/sounds/" + soundMap.get(alias), Sound.class);
    }

    public static Music getMusic(String alias)
    {
        return assetManager.get("content/music/" + musicMap.get(alias), Music.class);
    }

    private static Map<String, String> getMapFromJson(FileHandle file)
    {
        Map<String, String> ret = new HashMap<String, String>();

        if (file != null)
        {
            Json json = new Json();
            ArrayList<JsonValue> elements = json.fromJson(ArrayList.class, file.reader());
            if (elements != null)
            {
                for (JsonValue value : elements)
                {
                    if (value != null)
                    {
                        JsonValue.JsonIterator iterator = value.iterator();
                        while (iterator.hasNext())
                        {
                            JsonValue currentValue = iterator.next();
                            ret.put(currentValue.name(), currentValue.asString());
                        }
                    }
                }
            }
        }

        return ret;
    }
}
