package dk.kea.class2019january.mathiasg.chargefinder.models;

import com.google.gson.annotations.SerializedName;

public class AddressInfo
{
    @SerializedName("Title")
    private String title;

    @SerializedName("AddressLine1")
    private String address;

    @SerializedName("Town")
    private String town;

    @SerializedName("StateOrProvince")
    private String stateOrProvince;

    @SerializedName("Postcode")
    private String postcode;

    @SerializedName("Latitude")
    private double latitude;

    @SerializedName("Longitude")
    private double longitude;

    public AddressInfo(String title, String address, String town, String stateOrProvince, String postcode, double latitude, double longitude)
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

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }
}
