package com.arisux.airi.lib;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.arisux.airi.AIRI;

public class NetworkUtil
{
    /**
     * Retrieves the contents of a page with the specified URL.
     * NOTE: Networking must be enabled for this method to function.
     * 
     * @param url - The URL to retrieve page contents from.
     * @return The contents of the remote page.
     */
    public static String getURLContents(String url)
    {
        return getURLContents(url, false);
    }

    /**
     * Retrieves the contents of a page with the specified URL.
     * NOTE: Networking must be enabled for this method to function.
     * 
     * @param url - The URL to retrieve page contents from.
     * @param insertNewLines - If set true, the method automatically inserts line breaks.
     * @return The contents of the remote page.
     */
    public static String getURLContents(String url, boolean insertNewLines)
    {
        if (AIRI.settings().isNetworkingEnabled())
        {
            HttpURLConnection connection = null;

            try
            {
                connection = ((HttpURLConnection) (new URL(url)).openConnection());
                connection.setDoInput(true);
                connection.setDoOutput(false);
                connection.connect();

                if (connection.getResponseCode() / 100 != 2)
                {
                    return null;
                }
                else
                {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder builder = new StringBuilder();
                    String inputLine;

                    while ((inputLine = reader.readLine()) != null)
                    {
                        if (insertNewLines)
                            builder.append(inputLine + "\n");
                        else
                            builder.append(inputLine);
                    }

                    reader.close();
                    return builder.toString();
                }
            }
            catch (Exception e)
            {
                AIRI.logger.warning(e.toString() + ": " + url);

                if (connection != null)
                {
                    connection.disconnect();
                }
                return null;
            }
            finally
            {
                if (connection != null)
                {
                    connection.disconnect();
                }
            }
        }

        return null;
    }

    /**
     * Downloads a file from the specified URL and saves it to the specified location.
     * NOTE: Networking must be enabled for this method to function.
     * 
     * @param fileUrl - The URL to download a file from.
     * @param saveLocation - The location where the downloaded file will be saved.
     * @throws IOException
     */
    public static void downloadFile(String fileUrl, String saveLocation) throws IOException
    {
        if (AIRI.settings().isNetworkingEnabled())
        {
            AIRI.logger.info("Downloading file from '" + fileUrl + "' and saving it to '" + saveLocation + "'");
            InputStream is = (new URL(fileUrl)).openStream();
            FileOutputStream os = new FileOutputStream(saveLocation);
            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1)
            {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        }
        else
        {
            AIRI.logger.info("Networking disabled, skipping: " + fileUrl);
        }
    }
}
