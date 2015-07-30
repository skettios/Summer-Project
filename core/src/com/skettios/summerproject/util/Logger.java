package com.skettios.summerproject.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public final class Logger
{
    private static FileHandle logFile = Gdx.files.absolute("logs/Summer-Project.log");
    private static DateFormat format = new SimpleDateFormat("MM/dd/yyy-HH:mm:ss");
    
    public static File getLogFile()
    {
        return logFile.file();
    }

    public static void setLogLevel(int logLevel)
    {
        Gdx.app.setLogLevel(logLevel);
    }

    public static void log(Object message, Object... args)
    {
        Date date = new Date();
        Gdx.app.log(String.format("[%s][Summer-Project][INFO]", format.format(date)), String.format(message.toString(), args));
        if (Properties.LOG_TO_FILE && Gdx.app.getLogLevel() >= 1)
        {
            logFile.writeString(String.format("[%s][Summer-Project][INFO]: %s", format.format(date), String.format(message.toString(), args)), false);
        }
    }

    public static void error(Object message, Object... args)
    {
        Date date = new Date();
        Gdx.app.error(String.format("[%s][Summer-Project][ERROR]", format.format(date)), String.format(message.toString(), args));
        if (Properties.LOG_TO_FILE && Gdx.app.getLogLevel() >= 2)
        {
            logFile.writeString(String.format("[%s][Summer-Project][ERROR]: %s", format.format(date), String.format(message.toString(), args)), false);
        }
    }

    public static void debug(Object message, Object... args)
    {
        Date date = new Date();
        Gdx.app.debug(String.format("[%s][Summer-Project][DEBUG]", format.format(date)), String.format(message.toString(), args));
        if (Properties.LOG_TO_FILE && Gdx.app.getLogLevel() >= 3)
        {
            logFile.writeString(String.format("[%s][Summer-Project][DEBUG]: %s", format.format(date), String.format(message.toString(), args)), false);
        }
    }
}
