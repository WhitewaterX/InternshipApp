package dk.kea.class2019january.mathiasg.chargefinder;

import java.util.List;

public class Station
{
    private String siteId;
    private String siteName;
    private String streetAddress;
    private int zipCode;
    private String cityName;
    private double lat;
    private double lng;
    private String status;
    private List<Connector> connectors;

    public Station(String siteId, String siteName, String streetAddress, int zipCode, String cityName, double lat, double lng, String status, List<Connector> connectors)
    {
        this.siteId = siteId;
        this.siteName = siteName;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.cityName = cityName;
        this.lat = lat;
        this.lng = lng;
        this.status = status;
        this.connectors = connectors;
    }

    @Override
    public String toString()
    {
        return "Station{" +
                "siteId='" + siteId + '\'' +
                ", siteName='" + siteName + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", zipCode=" + zipCode +
                ", cityName='" + cityName + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", status='" + status + '\'' +
                ", connectors=" + connectors +
                '}';
    }
}
