package dk.kea.class2019january.mathiasg.chargefinder;

import java.util.ArrayList;

import dk.kea.class2019january.mathiasg.chargefinder.models.ChargePoint;
import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenApi
{
    @GET("?output=json&countrycode=DK")
    Call<ArrayList<ChargePoint>> getChargePoints();
}
