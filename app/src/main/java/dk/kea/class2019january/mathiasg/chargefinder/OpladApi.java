package dk.kea.class2019january.mathiasg.chargefinder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpladApi
{
    @GET("data.json")
    Call<List<Station>> getStations();
}