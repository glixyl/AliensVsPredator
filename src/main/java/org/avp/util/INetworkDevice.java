package org.avp.util;

public interface INetworkDevice
{
    public void sendData();

    public void receiveData();

    public INetworkDevice getHostDevice();

    public String getChannel();
}
