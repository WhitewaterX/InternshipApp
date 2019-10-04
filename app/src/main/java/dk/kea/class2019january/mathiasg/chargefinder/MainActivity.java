package dk.kea.class2019january.mathiasg.chargefinder;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import dk.kea.class2019january.mathiasg.chargefinder.adapters.StationListAdapter;
import dk.kea.class2019january.mathiasg.chargefinder.models.Station;
import dk.kea.class2019january.mathiasg.chargefinder.viewmodels.StationViewModel;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, AboutFragment.aboutOnFragmentInteractionListener, FilterFragment.filterOnFragmentInteractionListener
{
    private static final String TAG = "MainActivity";

    // Map
    private GoogleMap mMap;
    private boolean mLocationPermissionGranted = false;

    //  UI Elements
    private ImageButton infoButton;
    private Button filterButton;
    private FrameLayout fragmentContainer;

    private StationViewModel stationViewModel;

    private ArrayList<Station> stationList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

        stationList = new ArrayList<>();


        //  Set up viewmodel
        stationViewModel = ViewModelProviders.of(this).get(StationViewModel.class);

        //  retrieves data from repo
        stationViewModel.init();

        //  observe changes
        stationViewModel.getStations().observe(this, new Observer<List<Station>>()
        {
            @Override
            public void onChanged(List<Station> stations)
            {
                Log.d(TAG, "onChanged called");

                stationList.addAll(stations);

                placeMarkers(stationList);

            }
        });

    }

    public void placeMarkers(List<Station> stationList)
    {
        for(Station station : stationList)
        {
            LatLng pos = new LatLng(station.getLat(), station.getLng());
            mMap.addMarker(new MarkerOptions().position(pos).title("Marker in " + station.getCityName()));
        }

    }

    public void setupViews()
    {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //  Views
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        infoButton = (ImageButton) findViewById(R.id.infoButton);
        filterButton = (Button) findViewById(R.id.filterButton);

        //  Button listeners
        infoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openAboutFragment();
            }
        });
        filterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openFilterFragment();
            }
        });
    }

    // private void init, for adapter

    public void openAboutFragment()
    {
        AboutFragment aboutFragment = AboutFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_bottom, R.anim.enter_from_bottom, R.anim.exit_to_bottom);
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_container, aboutFragment, "BLANK_FRAGMENT").commit();

        System.out.println(stationViewModel.getStations());

    }

    @Override
    public void aboutOnFragmentInteraction()
    {
        onBackPressed();
    }

    public void openFilterFragment()
    {
        FilterFragment filterFragment = FilterFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_bottom, R.anim.enter_from_bottom, R.anim.exit_to_bottom);
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_container, filterFragment, "BLANK_FRAGMENT").commit();
    }

    @Override
    public void filterOnFragmentInteraction()
    {
        onBackPressed();
    }






    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        /*
        // Add a marker
        LatLng cph = new LatLng(55.6761, 12.5683);
        mMap.addMarker(new MarkerOptions().position(cph).title("Marker in Copenhagen"));
         */

        //Code to move google logo to top right
        //mMap.setPadding(20, 0, 0, 1800);


    }
}
