package dk.kea.class2019january.mathiasg.chargefinder.models;

import com.google.gson.annotations.SerializedName;

public class Connection
{
    private int ID;

    @SerializedName("ConnectionType")
    private ConnectionType connectionType;

    public Connection(int ID, ConnectionType connectionType)
    {
        this.ID = ID;
        this.connectionType = connectionType;
    }

    public String toString()
    {
        return "Connection{" +
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
