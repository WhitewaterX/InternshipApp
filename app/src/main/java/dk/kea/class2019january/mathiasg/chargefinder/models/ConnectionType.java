package dk.kea.class2019january.mathiasg.chargefinder.models;

import com.google.gson.annotations.SerializedName;

public class ConnectionType
{
    @SerializedName("Title")
    private String title;

    public ConnectionType(String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "ConnectionType{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle()
    {
        return title;
    }
}
