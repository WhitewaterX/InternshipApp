package dk.kea.class2019january.mathiasg.chargefinder.models;

public class AddressInfo
{
    private String title;
    private String address;
    private String town;
    private String stateOrProvince;
    private String postcode;
    private int latitude;
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
}
