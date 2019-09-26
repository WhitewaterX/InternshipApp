package dk.kea.class2019january.mathiasg.chargefinder.models;

import java.util.ArrayList;

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
    private ArrayList<Connector> connectors;

    public Station(String siteId, String siteName, String streetAddress, int zipCode, String cityName, double lat, double lng, String status, ArrayList<Connector> connectors)
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
