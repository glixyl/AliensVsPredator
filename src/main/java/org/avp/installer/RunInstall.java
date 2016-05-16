package org.avp.installer;

import java.io.File;
import java.util.ArrayList;

public class RunInstall extends Thread implements Runnable
{
    private Installer installer;
    private Interface gui;

    public RunInstall(Installer installer, Interface gui)
    {
        this.installer = installer;
        this.gui = gui;

        this.start();
    }

    @Override
    public void run()
    {
        while (installer.getRunning())
        {
            this.install();
            break;
        }
    }

    public void install()
    {
        gui.getBackgroundPane().setCompletionPercent(0);
        installer.setStatus("Searching for the latest release information...");

        File tempDirectory = new File(installer.getInstallPath(), ".arisux/temp");
        String forgeDownloadURL = "", forgeDownloadLocation = "", forgeVersion = "";
        ArrayList<String[]> modList = new ArrayList<String[]>();

        {
            modList.add(Installer.downloadString("https://dl.dropbox.com/u/84357394/Java/Remote/AIRI.ini").split(":"));
            gui.getBackgroundPane().setCompletionPercent((int) gui.getBackgroundPane().getProgressBar().getValue() + 5);
            modList.add(Installer.downloadString("https://dl.dropbox.com/u/84357394/Java/Remote/AliensVsPredator.ini").split(":"));
            gui.getBackgroundPane().setCompletionPercent((int) gui.getBackgroundPane().getProgressBar().getValue() + 10);
        }

        installer.setStatus("Validating release information...");

        for (String[] modInfo : modList)
        {
            if (!modInfo[3].equalsIgnoreCase(""))
            {
                installer.setStatus("Release information validated for " + modInfo[3] + "...");
                installer.setStatus("Downloading " + modInfo[3] + " " + modInfo[1] + " for Minecraft " + modInfo[0]);
                System.out.println("[Installer] Release Found: " + modInfo[3] + " " + modInfo[1] + " for Minecraft " + modInfo[0] + " and Minecraft Forge " + modInfo[2]);

                File modsDirectory = new File(installer.getInstallPath(), "mods");

                forgeVersion = modInfo[2];
                forgeDownloadURL = "http://files.minecraftforge.net/maven/net/minecraftforge/forge/" + modInfo[0] + "-" + modInfo[2] + "/forge-" + modInfo[0] + "-" + modInfo[2] + "-installer.jar";
                forgeDownloadLocation = tempDirectory.getAbsolutePath() + "\\forge-installer.jar";

                String modDownloadurl = "http://" + modInfo[4] + "[" + modInfo[0] + "-" + modInfo[2] + "][" + modInfo[1] + "]%20" + modInfo[3] + ".jar";
                String installLocation = modsDirectory.getAbsolutePath() + "\\" + modInfo[3] + ".jar";

                gui.getBackgroundPane().setCompletionPercent((int) gui.getBackgroundPane().getProgressBar().getValue() + 20);

                /**
                 * Download and save the mods to the mods
                 * directory
                 **/
                {
                    if (!modsDirectory.exists())
                    {
                        installer.setStatus("Directory '" + modsDirectory.getAbsolutePath() + "' is missing, creating it now.");
                        modsDirectory.mkdirs();

                        if (!modsDirectory.exists())
                        {
                            installer.setStatus("ERROR: Directory '" + modsDirectory.getAbsolutePath() + "' could not be created! Aborting installation!");

                            while (installer.getRunning())
                            {
                                ;
                            }
                        }
                    }

                    try
                    {
                        Installer.downloadFile(modDownloadurl, installLocation);
                        installer.setStatus("Download completed for " + modInfo[3]);
                    }
                    catch (Exception e1)
                    {
                        installer.setStatus("ERROR: " + e1);
                    }
                }
            }
        }

        /** Download and run the Minecraft Forge installer **/
        if (!tempDirectory.exists())
        {
            installer.setStatus("Directory '" + tempDirectory.getAbsolutePath() + "' is missing, creating it now.");
            tempDirectory.mkdirs();

            if (!tempDirectory.exists())
            {
                installer.setStatus("ERROR: Directory '" + tempDirectory.getAbsolutePath() + "' could not be created! Aborting installation!");

                while (installer.getRunning())
                {
                    ;
                }
            }
        }

        gui.getBackgroundPane().setCompletionPercent((int) gui.getBackgroundPane().getProgressBar().getValue() + 20);

        try
        {
            installer.setStatus("Downloading Minecraft Forge " + forgeVersion + "...");
            Installer.downloadFile(forgeDownloadURL, forgeDownloadLocation);
            installer.setStatus("Running the Minecraft Forge installer, waiting for process to terminate...");

            {
                ProcessBuilder pb = new ProcessBuilder("java", "-jar", forgeDownloadLocation);
                Process p = pb.start();
                p.waitFor();
            }
        }
        catch (Exception e1)
        {
            installer.setStatus("ERROR: " + e1);
        }

        gui.getBackgroundPane().setCompletionPercent((int) 100);
        installer.setStatus("Installation complete.");
    }
}
