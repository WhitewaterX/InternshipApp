package dk.kea.class2019january.mathiasg.chargefinder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import dk.kea.class2019january.mathiasg.chargefinder.models.ChargePoint;


public class StationFragment extends Fragment
{

    private ChargePoint mChargePoint;
    private stationOnFragmentInteractionListener mListener;
    private ImageView closeStation;
    private TextView stationName;
    private TextView operatorName;
    private Button navButton;

    private ViewGroup linearLayout;

    public StationFragment()
    {
        // Required empty public constructor
    }

    public static StationFragment newInstance(ChargePoint chargePoint)
    {
        StationFragment fragment = new StationFragment();
        fragment.mChargePoint = chargePoint;
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

        stationName = view.findViewById(R.id.stationName);
        stationName.setText(mChargePoint.getAddressInfo().getAddress());

        operatorName = view.findViewById(R.id.operator);
        //  concatenates "operated by" with the operator name
        String operatorText = getString(R.string.operatedBy) + " " + mChargePoint.getOperatorInfo().getTitle();
        operatorName.setText(operatorText);

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
                double latitude = mChargePoint.getAddressInfo().getLatitude();
                double longitude = mChargePoint.getAddressInfo().getLongitude();
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude + "," + longitude + "&mode=d");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        return view;
    }

    private void addLayout(ImageView plug, ImageView dot, TextView plugType)
    {
        View infoLayout = LayoutInflater.from(getActivity().getLayoutInflater().inflate(R.layout.info_layout, linearLayout, false));
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
