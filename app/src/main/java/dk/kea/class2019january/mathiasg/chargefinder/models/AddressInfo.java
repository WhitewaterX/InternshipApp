package dk.kea.class2019january.mathiasg.chargefinder.models;

import com.google.gson.annotations.Expose;

public class AddressInfo
{
    @Expose
    private String title;

    @Expose
    private String address;

    @Expose
    private String town;

    @Expose
    private String stateOrProvince;

    @Expose
    private String postcode;

    @Expose
    private int latitude;

    @Expose
    private int longitude;

    public AddressInfo(String title, String address, String town, String stateOrProvince, String postcode, int latitude, int longitude)
    {
        this.title = title;
        this.address = address;
        this.town = town;
        this.stateOrProvince = stateOrProvince;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return "AddressInfo{" +
                "title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", town='" + town + '\'' +
                ", stateOrProvince='" + stateOrProvince + '\'' +
                ", postcode='" + postcode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public String getTitle()
    {
        return title;
    }

    public String getAddress()
    {
        return address;
    }

    public String getTown()
    {
        return town;
    }

    public String getStateOrProvince()
    {
        return stateOrProvince;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public int getLatitude()
    {
        return latitude;
    }

    public int getLongitude()
    {
        return longitude;
    }
}
