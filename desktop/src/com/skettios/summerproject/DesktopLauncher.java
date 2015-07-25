package com.skettios.summerproject;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.naef.jnlua.NativeSupport;
import com.naef.jnlua.NativeSupport.Loader;

import java.io.File;

public class DesktopLauncher
{
    public static void main(String[] arg)
    {
        NativeLoader.load();

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 600;
        config.addIcon("content/icon.png", FileType.Internal);
        new LwjglApplication(new SummerProject(), config);
    }

    protected static class NativeLoader
    {
        public static void load()
        {
            SharedLibraryLoader loader = new SharedLibraryLoader();
            File nativesDir = null;
            try
            {
                if (loader.isWindows)
                {
                    if (loader.is64Bit)
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

                if (loader.isLinux)
                {
                    if (loader.is64Bit)
                    {
                        // TODO(skettios): GET 64-BIT LUA LINUX BINARIES
                    }
                    else
                    {
                        // TODO(skettios): GET 32-BIT LUA LINUX BINARIES
                    }
                }

                if (loader.isMac)
                {
                    if (loader.is64Bit)
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
