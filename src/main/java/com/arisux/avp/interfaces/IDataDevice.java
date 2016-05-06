package com.arisux.avp.interfaces;

public interface IDataDevice
{
    /** Read data from another device with specified ID implementing this interface **/
    public void readFromOtherDevice(int ID);

    /** Write data to another device with specified ID implementing this interface **/
    public void writeToOtherDevice(int ID);
}
