package org.avp.installer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Installer extends Thread
{
    private static boolean running;
    private static String status;
    private static Interface gui;
    public static File installPath = getDefaultMinecraftDir();

    public static void main(String args[])
    {
        System.out.println("[Installer] Initialized - Copyright (C) 2014-2016 Arisux");
        System.out.println("[Installer] Install Dir: " + getDefaultMinecraftDir().getAbsolutePath());

        {
            running = true;
            status = "Ready";
            Installer installer = new Installer();
            installer.start();
            gui = new Interface(installer);
        }
    }

    @Override
    public void run()
    {
        super.run();

        while (running)
        {
            this.onTick();
        }
    }

    public void onTick()
    {
        if (getGui() != null)
            getGui().tickGui();
    }

    public static File getDefaultMinecraftDir()
    {
        if ((System.getProperty("os.name").toLowerCase()).contains("win"))
        {
            return new File(System.getProperty("user.home"), "appdata/roaming/.minecraft");
        }
        else if ((System.getProperty("os.name").toLowerCase()).contains("linux"))
        {
            return new File(System.getProperty("user.home"), ".minecraft");
        }
        else
        {
            return new File(System.getProperty("user.home"), "Library/Application Support/.minecraft");
        }
    }

    public static void downloadFile(String fileUrl, String destinationFile) throws IOException
    {
        System.out.println("Downloading: " + destinationFile);
        URL url = new URL(fileUrl);
        InputStream is = url.openStream();
        FileOutputStream os = new FileOutputStream(destinationFile);
        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1)
        {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }

    public static String downloadString(String var1)
    {
        HttpURLConnection httpurlconnection = null;

        try
        {
            httpurlconnection = (HttpURLConnection) ((HttpURLConnection) (new URL(var1)).openConnection());
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(false);
            httpurlconnection.connect();

            if (httpurlconnection.getResponseCode() / 100 != 2)
            {
                return null;
            }
            else
            {
                BufferedReader exception = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = exception.readLine()) != null)
                {
                    response.append(inputLine);
                }

                exception.close();
                String var5 = response.toString();
                return var5;
            }
        }
        catch (Exception var9)
        {
            var9.printStackTrace();
            return null;
        }
        finally
        {
            if (httpurlconnection != null)
            {
                httpurlconnection.disconnect();
            }
        }
    }

    public File getInstallPath()
    {
        return installPath;
    }

    public void setInstallPath(File path)
    {
        installPath = path;
    }

    public static Interface getGui()
    {
        return gui;
    }

    public String getStatus()
    {
        return status;
    }

    public boolean getRunning()
    {
        return running;
    }

    public void setStatus(String stat)
    {
        status = stat;
        System.out.println("[Installer] " + status);
        getGui().repaint();
    }
}
