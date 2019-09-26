package dk.kea.class2019january.mathiasg.chargefinder.models;

public class Connector
{
    private String id;
    private String status;

    public Connector(String id, String status)
    {
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "Connector{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
