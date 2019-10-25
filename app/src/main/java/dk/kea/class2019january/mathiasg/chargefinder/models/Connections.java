package dk.kea.class2019january.mathiasg.chargefinder.models;

import com.google.gson.annotations.Expose;

public class Connections
{

    private int ID;
    private ConnectionType connectionType;
    public String toString()
    {
        return "Connections{" +
                "ID=" + ID +
                ", connectionType=" + connectionType +
                '}';
    }

    public Connections(int ID, ConnectionType connectionType)
    {
        this.ID = ID;
        this.connectionType = connectionType;
    }

    public int getID()
    {
        return ID;
    }

    public ConnectionType getConnectionType()
    {
        return connectionType;
    }

}
