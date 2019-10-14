package dk.kea.class2019january.mathiasg.chargefinder;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dk.kea.class2019january.mathiasg.chargefinder.models.Station;


public class StationFragment extends Fragment
{

    private Station mStation;
    private stationOnFragmentInteractionListener mListener;
    private ImageView closeStation;

    public StationFragment()
    {
        // Required empty public constructor
    }

    public static StationFragment newInstance(Station station)
    {
        StationFragment fragment = new StationFragment();
        fragment.mStation = station;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.station_layout, container, false);
        closeStation = view.findViewById(R.id.closeStation);

        closeStation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendBack();
            }
        });

        return view;
    }

    //  Closes the about tab
    private void sendBack()
    {
        if (mListener != null)
        {
            mListener.stationOnFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof stationOnFragmentInteractionListener)
        {
            mListener = (stationOnFragmentInteractionListener) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }


    public interface stationOnFragmentInteractionListener
    {
        void stationOnFragmentInteraction();
    }
}
