package com.arisux.avp.interfaces;

public interface INetworkDevice
{
    public void sendData();

    public void receiveData();

    public INetworkDevice getHostDevice();

    public String getChannel();
}
