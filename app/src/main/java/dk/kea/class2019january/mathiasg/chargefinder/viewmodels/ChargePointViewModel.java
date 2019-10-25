package dk.kea.class2019january.mathiasg.chargefinder.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dk.kea.class2019january.mathiasg.chargefinder.RepoCallback;
import dk.kea.class2019january.mathiasg.chargefinder.models.ChargePoint;
import dk.kea.class2019january.mathiasg.chargefinder.repositories.ChargePointRepository;

public class ChargePointViewModel extends ViewModel
{
    private final String TAG = "ChargePointViewModel";
    private MutableLiveData<List<ChargePoint>> chargePoints = new MutableLiveData<>();
    private ChargePointRepository repo;

    public void init()
    {
        repo = ChargePointRepository.getInstance();

        repo.getDataFromOpen(new RepoCallback<List<ChargePoint>>()
        {
            @Override
            public void onSuccess(List<ChargePoint> result)
            {
                chargePoints.setValue(result);
            }

            @Override
            public void onFailure()
            {

            }
        });

    }

    public LiveData<List<ChargePoint>> getChargePoints()
    {
        Log.d(TAG, "getChargePoints");
        return chargePoints;
    }

}
