package dk.kea.class2019january.mathiasg.chargefinder.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dk.kea.class2019january.mathiasg.chargefinder.models.Station;
import dk.kea.class2019january.mathiasg.chargefinder.repositories.StationRepository;

public class StationViewModel extends ViewModel
{
    private MutableLiveData<List<Station>> stations;
    private StationRepository repo;

    public void init()
    {
        if(stations != null)
        {
            return;
        }
        repo = StationRepository.getInstance();
        stations = repo.repoGetStations();
    }

    public LiveData<List<Station>> getStations()
    {
        return stations;
    }

}
