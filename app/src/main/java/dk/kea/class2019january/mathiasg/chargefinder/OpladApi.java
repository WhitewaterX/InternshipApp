package dk.kea.class2019january.mathiasg.chargefinder;

import java.util.ArrayList;

import dk.kea.class2019january.mathiasg.chargefinder.models.oldModels.Station;
import retrofit2.Call;
import retrofit2.http.GET;

public interface OpladApi
{
    @GET("data.json")
    Call<ArrayList<Station>> getStations();
}
