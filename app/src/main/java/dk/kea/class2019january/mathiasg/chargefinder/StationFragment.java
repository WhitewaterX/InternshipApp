package dk.kea.class2019january.mathiasg.chargefinder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import dk.kea.class2019january.mathiasg.chargefinder.models.ChargePoint;
import dk.kea.class2019january.mathiasg.chargefinder.models.Connection;


public class StationFragment extends Fragment
{

    //  chargepoint object with info
    private ChargePoint chargePoint;

    private stationOnFragmentInteractionListener mListener;
    private ImageView closeStation;
    private TextView stationName;
    private TextView operatorName;
    private Button navButton;

    //  info labels
    private ConstraintLayout type2Info;
    private ConstraintLayout ccsInfo;
    private ConstraintLayout chademoInfo;
    private ConstraintLayout teslaInfo;

    public StationFragment()
    {
        // Required empty public constructor
    }

    public static StationFragment newInstance(ChargePoint chargePoint)
    {
        StationFragment fragment = new StationFragment();
        fragment.chargePoint = chargePoint;
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

    //  VIEW SETUPS

        //  close button
        closeStation = view.findViewById(R.id.closeStation);

        //  sets station name label
        stationName = view.findViewById(R.id.stationName);
        stationName.setText(chargePoint.getAddressInfo().getAddress());

        //  if operator field from API is null, will set text to unknown, else sets the text to
        //  the one it downloaded
        String operatorText;
        if(chargePoint.getOperatorInfo() == null)
        {
            operatorText = getString(R.string.operatedBy) + " Unknown";
        }
        else
        {
            operatorText = getString(R.string.operatedBy) + " " + chargePoint.getOperatorInfo().getTitle();
        }
        operatorName = view.findViewById(R.id.operator);
        operatorName.setText(operatorText);

        //  Constraintlayouts for info labels
        type2Info = view.findViewById(R.id.type2_info);
        ccsInfo = view.findViewById(R.id.ccs_info);
        chademoInfo = view.findViewById(R.id.chademo_info);
        teslaInfo = view.findViewById(R.id.tesla_info);

        //  Will hide or show the info labels, which are the constraintlayouts, based on what type
        //  of connection the chargepoint has
        for(Connection connection : chargePoint.getConnections())
        {
            if(connection.getConnectionType().getTitle().contains("Type 2 ("))
            {
                type2Info.setVisibility(View.VISIBLE);
            }

            if(connection.getConnectionType().getTitle().contains("CHAdeMO"))
            {
                chademoInfo.setVisibility(View.VISIBLE);
            }

            if(connection.getConnectionType().getTitle().contains("Tesla"))
            {
                teslaInfo.setVisibility(View.VISIBLE);
            }

            if(connection.getConnectionType().getTitle().contains("CCS"))
            {
                ccsInfo.setVisibility(View.VISIBLE);
            }
        }

        //  Button for navigation
        navButton = view.findViewById(R.id.navButton);

        closeStation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendBack();
            }
        });

        navButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //  Opens google maps navigation with the marker's coordinates
                double latitude = chargePoint.getAddressInfo().getLatitude();
                double longitude = chargePoint.getAddressInfo().getLongitude();
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude + "," + longitude + "&mode=d");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
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
