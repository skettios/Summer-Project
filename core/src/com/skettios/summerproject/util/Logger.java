package com.skettios.summerproject.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Logger
{
    private static DateFormat format = new SimpleDateFormat("MM/dd/yyy-HH:mm:ss");
    private static FileHandle logFile = Gdx.files.absolute("logs/Summer-Project.log");

    static
    {
        if (!logFile.parent().exists())
        {
            logFile.parent().mkdirs();
        }
    }

    public static void setLogLevel(int logLevel)
    {
        Gdx.app.setLogLevel(logLevel);
    }

    public static void setLogToFile(boolean value)
    {
        if (value)
        {
            try
            {
                System.setErr(new PrintStream(logFile.file()));
                System.setOut(new PrintStream(logFile.file()));
            }
            catch (IOException e)
            {
                Logger.error(e.getMessage());
            }
        }
    }

    public static void log(Object message, Object... args)
    {
        Date date = new Date();
        Gdx.app.log(String.format("[%s][Summer-Project][INFO]", format.format(date)), String.format(message.toString(), args));
    }

    public static void error(Object message, Object... args)
    {
        Date date = new Date();
        Gdx.app.error(String.format("[%s][Summer-Project][ERROR]", format.format(date)), String.format(message.toString(), args));
    }

    public static void debug(Object message, Object... args)
    {
        Date date = new Date();
        Gdx.app.debug(String.format("[%s][Summer-Project][DEBUG]", format.format(date)), String.format(message.toString(), args));
    }
}
