package dk.kea.class2019january.mathiasg.chargefinder.adapters;

import java.util.ArrayList;
import java.util.List;

import dk.kea.class2019january.mathiasg.chargefinder.models.Station;

public class StationListAdapter
{
    private List<Station> mStations;


    public void setStation(List<Station> station)
    {
        mStations = station;
    }

    public int getItemCount()
    {
        if(mStations != null)
        {
            return mStations.size();
        }

        else return 0;
    }

}
