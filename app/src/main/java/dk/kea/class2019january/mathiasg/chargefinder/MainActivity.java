//TODO: toggle markers
//TODO: pin info fragment on selection and open in maps app
//TODO: custom map pins
//TODO: add different API
//TODO: possibly add Room, or Firebase
//TODO: hide toolbar

package dk.kea.class2019january.mathiasg.chargefinder;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import dk.kea.class2019january.mathiasg.chargefinder.models.Connector;
import dk.kea.class2019january.mathiasg.chargefinder.models.Station;
import dk.kea.class2019january.mathiasg.chargefinder.viewmodels.StationViewModel;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, AboutFragment.aboutOnFragmentInteractionListener, FilterFragment.filterOnFragmentInteractionListener
{
    private static final String TAG = "MainActivity";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TYPE2 = "type2";

    // Map
    private GoogleMap mMap;
    private boolean mLocationPermissionGranted = false;

    //  UI Elements
    private ImageButton infoButton;
    private Button filterButton;
    private FrameLayout fragmentContainer;

    private StationViewModel stationViewModel;
    private ArrayList<Station> stationList;

    private boolean type2State;

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

        loadFilter();
    }

    //  places markers on map
    public void placeMarkers(List<Station> stationList)
    {
        for(Station station : stationList)
        {
            //  Boolean used to secure that only 1 marker is placed per station.
            boolean connectorsAvailable = false;

            //  check station based on area (currently static cph, can later get gps location)
            if (station.getCityName().contains("København"))
            {
                //  check if there are any available connectors and flags the boolean to true if
                for(Connector connector : station.getConnectors())
                {
                    if (connector.getStatus().equals("Available"))
                    {
                        connectorsAvailable = true;
                    }
                }
                //  places marker if a connector is available
                if(connectorsAvailable)
                {
                    LatLng pos = new LatLng(station.getLat(), station.getLng());
                    mMap.addMarker(new MarkerOptions().position(pos).title(station.getStreetAddress() + ", " + station.getCityName()));
                }
            }
        }
    }

    //  gets filter settings from sharedpreferences
    public void loadFilter()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        type2State = sharedPreferences.getBoolean(TYPE2, false);
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

    public void openAboutFragment()
    {
        AboutFragment aboutFragment = AboutFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_bottom, R.anim.enter_from_bottom, R.anim.exit_to_bottom);
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_container, aboutFragment, "BLANK_FRAGMENT").commit();

        System.out.println(type2State);
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
        //  reloads the filter settings from sharedpreferences, set in filterfragment, when returning to mainactivity.
        loadFilter();
        Log.d(TAG, "onbackpressed");
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

        //  Central Copenhagen coords
        LatLng cph = new LatLng(55.6761, 12.5683);

        //  Make a camera position
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(cph)
                .zoom(14)
                .bearing(0)
                .tilt(0)
                .build();

        //  Animate to camera position
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //Code to move google logo to top right
        //mMap.setPadding(20, 0, 0, 1800);

    }

}
