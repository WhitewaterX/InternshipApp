package dk.kea.class2019january.mathiasg.chargefinder.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import dk.kea.class2019january.mathiasg.chargefinder.OpladApi;
import dk.kea.class2019january.mathiasg.chargefinder.RepoCallback;
import dk.kea.class2019january.mathiasg.chargefinder.models.Station;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StationRepository
{
    /*
    Singleton pattern, only one instance of the repo, to avoid many different open connections.
     */
    private final String TAG = "StationRepository";
    static StationRepository instance;

    public static StationRepository getInstance()
    {
        if(instance == null)
        {
            instance = new StationRepository();
        }
        return instance;
    }

    //  Retrofit for Opladdinelbil, using Gson
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opladdinelbil.dk/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //  Retrofit brings life to the methods in the Oplad interface
    OpladApi opladApi = retrofit.create(OpladApi.class);

    public void getDataFromOplad(final RepoCallback<List<Station>> callback)
    {
        Call<ArrayList<Station>> call = opladApi.getStations();

        call.enqueue(new Callback<ArrayList<Station>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Station>> call, Response<ArrayList<Station>> response)
            {
                Log.d(TAG, "onResponse called");

                if(!response.isSuccessful())
                {
                    System.out.println(response.code());
                }

                callback.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<ArrayList<Station>> call, Throwable t)
            {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }

}
