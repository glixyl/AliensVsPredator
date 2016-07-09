package com.arisux.airi.lib;

public class BasicMarkupParser
{
    public static final String TAG_START_COLOR = "<color>";
    public static final String TAG_END_COLOR = TAG_START_COLOR.replace("<", "</");

    /** Returns the color value of the first color tag in the provided text. **/
    /** FORMAT: <color value="0xFFFFFF"></color> **/
    public static int parseColorTagValue(String text, int defaultValue)
    {
        String localStartTagColor = TAG_START_COLOR;
        String valueMarkerStart = "value='";
        String valueMarkerEnd = "'";
        int tagValue = defaultValue;
        int valueStartIndex;
        int valueEndIndex;
        localStartTagColor = localStartTagColor.replace(">", "");
        if (text.contains(localStartTagColor) && text.contains(TAG_END_COLOR))
        {
            localStartTagColor = text.substring(text.indexOf(localStartTagColor), text.indexOf(">", 0) + 1);
            valueStartIndex = localStartTagColor.indexOf(valueMarkerStart) + valueMarkerStart.length();
            valueEndIndex = localStartTagColor.indexOf(valueMarkerEnd, valueStartIndex);
            tagValue = Integer.parseInt(localStartTagColor.substring(valueStartIndex, valueEndIndex).replace("0x", ""), 16);
        }

        return tagValue;
    }

    /** Returns the contents of the first occurring start/end tags. **/
    /** FORMAT: <color value="0xFFFFFF"></color> **/
    public static String parseColorTagContents(String text, int defaultValue)
    {
        String localStartTagColor = TAG_START_COLOR;
        String tagContents = "";
        int lineTagStart;
        localStartTagColor = localStartTagColor.replace(">", "");
        if (text.contains(localStartTagColor) && text.contains(TAG_END_COLOR))
        {
            localStartTagColor = text.substring(lineTagStart = text.indexOf(localStartTagColor), text.indexOf(">", 0) + 1);
            tagContents = text.substring(lineTagStart + localStartTagColor.length(), text.indexOf(TAG_END_COLOR));
        }

        return tagContents;
    }

    /** Returns the provided text without the first start/end tag occurrences. **/
    /** FORMAT: <color value="0xFFFFFF"></color> **/
    public static String removeColorTag(String text)
    {
        String localStartTagColor = TAG_START_COLOR.replace(">", "");
        if (text.contains(localStartTagColor) && text.contains(TAG_END_COLOR))
        {
            localStartTagColor = text.substring(text.indexOf(localStartTagColor), text.indexOf(">", 0) + 1);
            text = text.replace(localStartTagColor, "");
            text = text.replace(TAG_END_COLOR, "");
        }

        return text;
    }
}
