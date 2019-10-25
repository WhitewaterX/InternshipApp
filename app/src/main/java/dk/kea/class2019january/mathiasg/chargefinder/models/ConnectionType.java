package dk.kea.class2019january.mathiasg.chargefinder.models;

public class ConnectionType
{

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
