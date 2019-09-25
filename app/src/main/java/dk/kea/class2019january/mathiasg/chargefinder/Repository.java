package dk.kea.class2019january.mathiasg.chargefinder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository
{

    //  Retrofit for Opladdinelbil, using Gson
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opladdinelbil.dk/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //  retrofit brings life to the methods in the Oplad interface
    OpladApi opladApi = retrofit.create(OpladApi.class);

    public void getDataFromOplad()
    {
        Call<ArrayList<Station>> call = opladApi.getStations();

        call.enqueue(new Callback<ArrayList<Station>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Station>> call, Response<ArrayList<Station>> response)
            {
                if(!response.isSuccessful())
                {
                    System.out.println(response.code());
                }

                ArrayList<Station> stations = response.body();
                System.out.println(stations);
            }

            @Override
            public void onFailure(Call<ArrayList<Station>> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
    }
}
