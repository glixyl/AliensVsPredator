package com.arisux.airi.lib;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.commons.lang3.SystemUtils;
import org.lwjgl.opengl.GL11;

public class SystemUtil
{
    public static int bytesUnit = 1024;
    public static Enumeration<NetworkInterface> networkAdapters;

    public static Enumeration<NetworkInterface> networkAdapters()
    {
        try
        {
            return networkAdapters == null ? networkAdapters = NetworkInterface.getNetworkInterfaces() : networkAdapters;
        }
        catch (SocketException e)
        {
            ;
        }

        return null;
    }

    public static String gpu()
    {
        return GlStateManager.getString(GL11.GL_RENDERER);
    }

    public static String gpuVendor()
    {
        return GlStateManager.getString(GL11.GL_VENDOR);
    }

    public static String cpu()
    {
        return System.getenv("processor.identifier");
    }

    public static int cpuCores()
    {
        return Runtime.getRuntime().availableProcessors();
    }

    public static String javaVersion()
    {
        return SystemUtils.JAVA_VERSION;
    }

    public static String osName()
    {
        return System.getProperty("os.name");
    }

    public static String osVersion()
    {
        return System.getProperty("os.version");
    }

    public static String osArchitecture()
    {
        return System.getProperty("os.arch");
    }

    public static long vmMemoryTotalBytes()
    {
        return Runtime.getRuntime().totalMemory();
    }

    public static long vmMemoryMaxBytes()
    {
        return Runtime.getRuntime().maxMemory();
    }

    public static long vmMemoryFreeBytes()
    {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * Convert Bytes to TeraBytes
     * 
     * @param b - Bytes to be converted
     * @return the amount of TeraBytes
     */
    public static double toTBFromB(long b)
    {
        return b / bytesUnit / bytesUnit / bytesUnit / bytesUnit;
    }

    /**
     * Convert Bytes to GigaBytes
     * 
     * @param b - Bytes to be converted
     * @return the amount of GigaBytes
     */
    public static double toGBFromB(long b)
    {
        return b / bytesUnit / bytesUnit / bytesUnit;
    }

    /**
     * Convert Bytes to MegaBytes
     * 
     * @param b - Bytes to be converted
     * @return the amount of MegaBytes
     */
    public static double toMBFromB(long b)
    {
        return b / bytesUnit / bytesUnit;
    }

    /**
     * Convert Bytes to KiloBytes
     * 
     * @param b - Bytes to be converted
     * @return the amount of KiloBytes
     */
    public static double toKBFromB(long b)
    {
        return b / bytesUnit;
    }

    /**
     * Convert MegaBytes to TeraBytes
     * 
     * @param b - MegaBytes to be converted
     * @return the amount of TeraBytes
     */
    public static double toTBFromMB(long mb)
    {
        return mb / bytesUnit / bytesUnit;
    }

    /**
     * Convert MegaBytes to GigaBytes
     * 
     * @param b - MegaBytes to be converted
     * @return the amount of GigaBytes
     */
    public static double toGBFromMB(long mb)
    {
        return mb / bytesUnit;
    }

    /**
     * Convert MegaBytes to KiloBytes
     * 
     * @param b - MegaBytes to be converted
     * @return the amount of KiloBytes
     */
    public static double toKBFromMB(long mb)
    {
        return mb * bytesUnit;
    }

    /**
     * Convert MegaBytes to Bytes
     * 
     * @param b - MegaBytes to be converted
     * @return the amount of Bytes
     */
    public static double toBFromMB(long mb)
    {
        return mb * bytesUnit * bytesUnit;
    }

    /**
     * Convert GigaBytes to TeraBytes
     * 
     * @param b - GigaBytes to be converted
     * @return the amount of TeraBytes
     */
    public static double toTBFromGB(long gb)
    {
        return gb / bytesUnit;
    }

    /**
     * Convert GigaBytes to MegaBytes
     * 
     * @param b - GigaBytes to be converted
     * @return the amount of MegaBytes
     */
    public static double toMBFromGB(long gb)
    {
        return gb * bytesUnit;
    }

    /**
     * Convert GigaBytes to KiloBytes
     * 
     * @param b - GigaBytes to be converted
     * @return the amount of KiloBytes
     */
    public static double toKBFromGB(long gb)
    {
        return gb * bytesUnit * bytesUnit;
    }

    /**
     * Convert GigaBytes to Bytes
     * 
     * @param b - GigaBytes to be converted
     * @return the amount of Bytes
     */
    public static double toBFromGB(long gb)
    {
        return gb * bytesUnit * bytesUnit * bytesUnit;
    }
}
