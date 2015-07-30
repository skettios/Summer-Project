package com.skettios.summerproject.lua;

import javax.script.Compilable;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.badlogic.gdx.files.FileHandle;
import com.naef.jnlua.script.LuaScriptEngineFactory;
import com.skettios.summerproject.util.Logger;

public final class LuaHandler
{
    private static LuaScriptEngineFactory factory = new LuaScriptEngineFactory();
    private static ScriptEngine engine = factory.getScriptEngine();

    static
    {
        engine.setBindings(new LuaEngineBindings(), ScriptContext.ENGINE_SCOPE);
    }

    public static Object evaluateScript(FileHandle handle)
    {
        try
        {
            return ((Compilable) engine).compile(handle.reader()).eval();
        }
        catch (ScriptException e)
        {
            Logger.error(e);
        }

        return null;
    }

    public static Object callFunction(String funcName, Object... args)
    {
        try
        {
            return ((Invocable) engine).invokeFunction(funcName, args);
        }
        catch (Exception e)
        {
            Logger.error(e);
        }

        return null;
    }
}
