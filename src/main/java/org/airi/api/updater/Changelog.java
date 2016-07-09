package com.arisux.airi.api.updater;

import java.util.ArrayList;

public class Changelog
{
    private ArrayList<SubChangelog> subLogs = new ArrayList<SubChangelog>();
    private String preParsedChangelog;
    public static final String changelogDefaultContents = "Downloading changelog...";

    public static final String START_TAG = "<changelog>";
    public static final String END_TAG = START_TAG.replace("<", "</");

    public static final String START_TAG_VERSION = "<version>";
    public static final String END_TAG_VERSION = START_TAG_VERSION.replace("<", "</");

    public static final String START_TAG_DATE = "<date>";
    public static final String END_TAG_DATE = START_TAG_DATE.replace("<", "</");

    public static final String START_TAG_CONTENTS = "<contents>";
    public static final String END_TAG_CONTENTS = START_TAG_CONTENTS.replace("<", "</");

    public static class SubChangelog
    {
        private String preParsedLog, version, date, contents;

        public SubChangelog(String preParsedLog, String version, String date, String contents)
        {
            this.preParsedLog = preParsedLog;
            this.version = version;
            this.date = date;
            this.contents = contents;
        }

        public String getPreParsedLog()
        {
            return preParsedLog;
        }

        public String getVersion()
        {
            return version;
        }

        public String getDate()
        {
            return date;
        }

        public String getContents()
        {
            return contents;
        }
    }

    public Changelog(String preParsedChangelog)
    {
        this.preParsedChangelog = preParsedChangelog;
        this.parse();
    }

    public void parse()
    {
        try
        {
            String[] preParsedLogs = preParsedChangelog.split(START_TAG);

            for (String preParsedLog : preParsedLogs)
            {
                if (preParsedLog.contains(END_TAG))
                {
                    String logPreParsed = preParsedLog.replace(END_TAG, "");
                    String logVersion = logPreParsed.substring(logPreParsed.indexOf(START_TAG_VERSION) + START_TAG_VERSION.length(), logPreParsed.indexOf(END_TAG_VERSION));
                    String logContents = logPreParsed.substring(logPreParsed.indexOf(START_TAG_CONTENTS) + START_TAG_CONTENTS.length(), logPreParsed.indexOf(END_TAG_CONTENTS));
                    String logDate = "";

                    if (preParsedLog.contains(START_TAG_DATE))
                    {
                        logDate = logPreParsed.substring(logPreParsed.indexOf(START_TAG_DATE) + START_TAG_DATE.length(), logPreParsed.indexOf(END_TAG_DATE));
                    }

                    this.subLogs.add(new SubChangelog(logPreParsed, logVersion, logDate, logContents));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<SubChangelog> getSubLogs()
    {
        return subLogs;
    }

    public String getPreParsedChangelog()
    {
        return preParsedChangelog;
    }

    public SubChangelog getLatestLog()
    {
        return this.subLogs.get(0);
    }

    public SubChangelog getChangelog(int id)
    {
        return this.subLogs.get(id);
    }

    public SubChangelog getChangelogByVersion(String version)
    {
        for (SubChangelog subLog : this.subLogs)
        {
            if (subLog.getVersion().equalsIgnoreCase(version))
            {
                return subLog;
            }
        }

        return null;
    }
}
