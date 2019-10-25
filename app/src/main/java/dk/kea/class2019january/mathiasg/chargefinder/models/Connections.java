package dk.kea.class2019january.mathiasg.chargefinder.models;

import com.google.gson.annotations.SerializedName;

public class Connections
{
    private int ID;

    @SerializedName("ConnectionType")
    private ConnectionType connectionType;

    public Connections(int ID, ConnectionType connectionType)
    {
        this.ID = ID;
        this.connectionType = connectionType;
    }

    public String toString()
    {
        return "Connections{" +
                "ID=" + ID +
                ", connectionType=" + connectionType +
                '}';
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
