
package dk.kea.class2019january.mathiasg.chargefinder.models.oldModels;

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

    public String getId()
    {
        return id;
    }

    public String getStatus()
    {
        return status;
    }
}

