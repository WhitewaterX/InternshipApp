package dk.kea.class2019january.mathiasg.chargefinder.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dk.kea.class2019january.mathiasg.chargefinder.RepoCallback;
import dk.kea.class2019january.mathiasg.chargefinder.models.Station;
import dk.kea.class2019january.mathiasg.chargefinder.repositories.StationRepository;

public class StationViewModel extends ViewModel
{
    private final String TAG = "StationViewModel";
    private MutableLiveData<List<Station>> stations = new MutableLiveData<>();
    private StationRepository repo;

    public void init()
    {
        repo = StationRepository.getInstance();

        repo.getDataFromOplad(new RepoCallback<List<Station>>()
        {
            @Override
            public void onSuccess(List<Station> result)
            {
                stations.setValue(result);
            }

            @Override
            public void onFailure()
            {

            }
        });

    }

    public LiveData<List<Station>> getStations()
    {
        Log.d(TAG, "getStations");
        return stations;
    }

}
