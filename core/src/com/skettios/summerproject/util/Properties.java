package com.skettios.summerproject.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;

public final class Properties
{
    private static java.util.Properties properties = new java.util.Properties();
    private static FileHandle propertiesFile = Gdx.files.absolute("Summer-Project.cfg");

    public static boolean DEBUG_MODE;
    public static int LOG_LEVEL;
    public static boolean LOG_TO_FILE;

    static
    {
        try
        {
            if (!propertiesFile.exists())
            {
                generateDefaults();
                properties.store(propertiesFile.writer(false), "Summer-Project Properties");
            }
        }
        catch (IOException e)
        {
            Logger.error(e);
        }
    }

    public static void load()
    {
        try
        {
            generateDefaults();
            properties.load(propertiesFile.reader());

            DEBUG_MODE = Boolean.parseBoolean(properties.getProperty("DebugMode"));
            LOG_LEVEL = Integer.parseInt(properties.getProperty("LogLevel"));
            LOG_TO_FILE = Boolean.parseBoolean(properties.getProperty("LogToFile"));

            properties.store(propertiesFile.writer(false), "Summer-Project Properties");
        }
        catch (IOException e)
        {
            Logger.error(e);
        }
    }

    private static void generateDefaults()
    {
        properties.setProperty("DebugMode", Boolean.toString(false));
        properties.setProperty("LogLevel", Integer.toString(2));
        properties.setProperty("LogToFile", Boolean.toString(true));
    }
}
