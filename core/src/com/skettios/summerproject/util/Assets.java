package com.skettios.summerproject.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.AbsoluteFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.skettios.summerproject.util.handler.ArchiveFileHandle;
import com.skettios.summerproject.util.handler.ArchiveFileHandleResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipFile;

public final class Assets
{
    public static boolean isPacked = Gdx.files.absolute("base.spp").exists();

    private static FileHandle baseArchive = isPacked ? Gdx.files.absolute("base.spp") : Gdx.files.absolute("base");
    private static FileHandle textureArchive = isPacked ? Gdx.files.absolute("textures.spp") : Gdx.files.absolute("textures");
    private static FileHandle audioArchive = isPacked ? Gdx.files.absolute("audio.spp") : Gdx.files.absolute("audio");

    private static AssetManager textureManager;
    private static AssetManager audioManager;

    private static Map<String, String> textures;
    private static Map<String, String> music;
    private static Map<String, String> sounds;
    public static Map<String, String> levels;

    public static void load()
    {
        try
        {
            textureManager = isPacked ? new AssetManager(new ArchiveFileHandleResolver(new ZipFile(textureArchive.file()))) : new AssetManager(new AbsoluteFileHandleResolver());
            audioManager = isPacked ? new AssetManager(new ArchiveFileHandleResolver(new ZipFile(audioArchive.file()))) : new AssetManager(new AbsoluteFileHandleResolver());

            textures = isPacked ? getMapFromJson(new ArchiveFileHandle(new ZipFile(baseArchive.file()), "textures.json")) : getMapFromJson(baseArchive.child("textures.json"));
            music = isPacked ? getMapFromJson(new ArchiveFileHandle(new ZipFile(baseArchive.file()), "music.json")) : getMapFromJson(baseArchive.child("music.json"));
            sounds = isPacked ? getMapFromJson(new ArchiveFileHandle(new ZipFile(baseArchive.file()), "sounds.json")) : getMapFromJson(baseArchive.child("sounds.json"));
            levels = isPacked ? getMapFromJson(new ArchiveFileHandle(new ZipFile(baseArchive.file()), "levels.json")) : getMapFromJson(baseArchive.child("levels.json"));

            for (String alias : textures.keySet())
            {
                if (isPacked)
                {
                    textureManager.load(textures.get(alias), Texture.class);
                    textureManager.finishLoadingAsset(textures.get(alias));
                }
                else
                {
                    textureManager.load("textures/" + textures.get(alias), Texture.class);
                    textureManager.finishLoadingAsset("textures/" + textures.get(alias));
                }
                Logger.debug("Loaded Texture: " + alias);
            }
            for (String alias : music.keySet())
            {
                if (isPacked)
                {
                    audioManager.load(music.get(alias), Music.class);
                    audioManager.finishLoadingAsset(music.get(alias));
                }
                else
                {
                    audioManager.load("audio/" + music.get(alias), Music.class);
                    audioManager.finishLoadingAsset("audio/" + music.get(alias));
                }
                Logger.debug("Loaded Music: " + alias);
            }
            for (String alias : sounds.keySet())
            {
                if (isPacked)
                {
                    audioManager.load(sounds.get(alias), Sound.class);
                    audioManager.finishLoadingAsset(sounds.get(alias));
                }
                else
                {
                    audioManager.load("audio/" + sounds.get(alias), Sound.class);
                    audioManager.finishLoadingAsset("audio/" + sounds.get(alias));
                }
                Logger.debug("Loaded Sound: " + alias);
            }
        }
        catch (Exception e)
        {
            Logger.error(e);
        }
    }

    public static Texture getTexture(String alias)
    {
        if (!isPacked)
        {
            return textureManager.get("textures/" + textures.get(alias), Texture.class);
        }

        return textureManager.get(textures.get(alias), Texture.class);
    }

    public static Music getMusic(String alias)
    {
        if (!isPacked)
        {
            return audioManager.get("audio/" + music.get(alias), Music.class);
        }

        return audioManager.get(music.get(alias), Music.class);
    }

    public static Sound getSound(String alias)
    {
        if (!isPacked)
        {
            return audioManager.get("audio/" + sounds.get(alias), Sound.class);
        }

        return audioManager.get(sounds.get(alias), Sound.class);
    }

    public static FileHandle getLevel(String alias)
    {
        ZipFile file = null;

        try
        {
            file = new ZipFile(baseArchive.file());
        }
        catch (Exception e)
        {
            Logger.error(e);
        }

        if (!isPacked)
        {
            return Gdx.files.absolute("base/levels/" + levels.get(alias));
        }

        return new ArchiveFileHandle(file, "levels/" + levels.get(alias));
    }

    public static void stopAllMusic()
    {
        for (String alias : music.keySet())
        {
            getMusic(alias).stop();
        }
    }

    public static void stopAllSounds()
    {
        for (String alias : sounds.keySet())
        {
            getSound(alias).stop();
        }
    }

    @SuppressWarnings("unchecked")
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
