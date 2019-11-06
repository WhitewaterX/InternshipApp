package dk.kea.class2019january.mathiasg.chargefinder.repositories;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import dk.kea.class2019january.mathiasg.chargefinder.OpenApi;
import dk.kea.class2019january.mathiasg.chargefinder.RepoCallback;
import dk.kea.class2019january.mathiasg.chargefinder.models.ChargePoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChargePointRepository
{
    /*
    Singleton pattern, only one instance of the repo, to avoid many different open connections.
     */
    private final String TAG = "ChargePointRepository";
    static ChargePointRepository instance;

    public static ChargePointRepository getInstance()
    {
        if(instance == null)
        {
            instance = new ChargePointRepository();
        }
        return instance;
    }

    //  Retrofit for Openchargemap, using Gson
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.openchargemap.io/v3/poi/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //  Retrofit brings life to the methods in the open interface
    OpenApi openApi = retrofit.create(OpenApi.class);

    public void getDataFromOpen(final RepoCallback<List<ChargePoint>> callback)
    {
        Call<ArrayList<ChargePoint>> call = openApi.getChargePoints();

        call.enqueue(new Callback<ArrayList<ChargePoint>>()
        {
            @Override
            public void onResponse(Call<ArrayList<ChargePoint>> call, Response<ArrayList<ChargePoint>> response)
            {
                Log.d(TAG, "onResponse called");

                if(!response.isSuccessful())
                {
                    System.out.println(response.code());
                }

                callback.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<ArrayList<ChargePoint>> call, Throwable t)
            {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }

}
