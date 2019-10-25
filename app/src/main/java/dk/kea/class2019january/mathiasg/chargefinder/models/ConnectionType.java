package dk.kea.class2019january.mathiasg.chargefinder.models;

import com.google.gson.annotations.Expose;

public class ConnectionType
{
    @Expose
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
