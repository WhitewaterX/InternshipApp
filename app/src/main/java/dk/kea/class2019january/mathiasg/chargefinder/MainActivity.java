//TODO: pin color combos
//TODO: rest of station fragment layout

package dk.kea.class2019january.mathiasg.chargefinder;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dk.kea.class2019january.mathiasg.chargefinder.models.ChargePoint;
import dk.kea.class2019january.mathiasg.chargefinder.models.Connection;
import dk.kea.class2019january.mathiasg.chargefinder.viewmodels.ChargePointViewModel;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, AboutFragment.aboutOnFragmentInteractionListener, FilterFragment.filterOnFragmentInteractionListener, StationFragment.stationOnFragmentInteractionListener
{
    private static final String TAG = "MainActivity";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TYPE2 = "type2";
    public static final String CHADEMO = "chademo";
    public static final String TESLA = "tesla";
    public static final String CCS = "ccs";

    // Map
    private GoogleMap mMap;
    private boolean mLocationPermissionGranted = false;

    //  UI Elements
    private ImageButton infoButton;
    private Button filterButton;
    private FrameLayout fragmentContainer;

    //  data from api
    //private StationViewModel stationViewModel;
    //private ArrayList<Station> stationList;
    private ChargePointViewModel chargePointViewModel;
    private ArrayList<ChargePoint> chargePointList;

    //  marker data storage, also used for when opening station fragment
    //private Map<Marker, Station> markers = new HashMap<>();
    private Map<Marker, ChargePoint> markers = new HashMap<>();

    private boolean type2State;
    private boolean chademoState;
    private boolean teslaState;
    private boolean ccsState;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        loadFilter();

        /*
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
            }
        });

         */


        chargePointList = new ArrayList<>();

        //  Set up viewmodel
        chargePointViewModel = ViewModelProviders.of(this).get(ChargePointViewModel.class);

        //  retrieves data from repo
        chargePointViewModel.init();

        //  observe changes
        chargePointViewModel.getChargePoints().observe(this, new Observer<List<ChargePoint>>()
        {
            @Override
            public void onChanged(List<ChargePoint> chargePoints)
            {
                Log.d(TAG, "onChanged called");

                chargePointList.addAll(chargePoints);
            }
        });

    }

    //  places markers on map
    public void placeMarkers(List<ChargePoint> chargePointList)
    {

        for(ChargePoint chargePoint : chargePointList)
        {
            ArrayList<Bitmap> colorsToDraw = new ArrayList<>();
            
            LatLng pos = new LatLng(chargePoint.getAddressInfo().getLatitude(), chargePoint.getAddressInfo().getLongitude());
            
            Bitmap pin = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.pin);

            for(Connection connection : chargePoint.getConnections())
            {
                if(type2State && connection.getConnectionType().getTitle().contains("Mennekes (Type 2"))
                {

                }

                if(chademoState && connection.getConnectionType().getTitle().contains("CHAdeMO"))
                {

                }

                if(teslaState && connection.getConnectionType().getTitle().contains("Tesla"))
                {
                    
                }

                if(ccsState && connection.getConnectionType().getTitle().contains("CCS"))
                {

                }
            }

            /*
            LatLng pos = new LatLng(chargePoint.getAddressInfo().getLatitude(), chargePoint.getAddressInfo().getLongitude());
            Bitmap pin = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.pin);
            ArrayList<Bitmap> colorsToDraw = new ArrayList<>();

            Bitmap green = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.green_dot);
            Bitmap merged = mergeToPin(pin, green);

            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(pos)
                    .icon(BitmapDescriptorFactory.fromBitmap(merged)));
             */
        }
    }

    /*
    public void placeMarkers(List<Station> stationList)
    {
        if(type2State)
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
                        Bitmap pin = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.pin);
                        Bitmap green = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.green_dot);
                        Bitmap merged = mergeToPin(pin, green);

                        Marker marker = mMap.addMarker(new MarkerOptions()
                                .position(pos)
                                .icon(BitmapDescriptorFactory.fromBitmap(merged)));

                        // adds marker and station to the markers hashmap, which is used in opening station fragment to pass data
                        markers.put(marker, station);
                    }
                }
            }
        }

        else
        {
            mMap.clear();
        }
    }

     */

    //  gets filter settings from sharedpreferences
    public void loadFilter()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        type2State = sharedPreferences.getBoolean(TYPE2, false);
        chademoState = sharedPreferences.getBoolean(CHADEMO, false);
        teslaState = sharedPreferences.getBoolean(TESLA, false);
        ccsState = sharedPreferences.getBoolean(CCS, false);
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
        transaction.add(R.id.fragment_container, aboutFragment, "ABOUT_FRAGMENT").commit();
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
        transaction.add(R.id.fragment_container, filterFragment, "FILTER_FRAGMENT").commit();
    }

    @Override
    public void filterOnFragmentInteraction()
    {
        Log.d(TAG, "onBackPressed called");
        onBackPressed();

        mMap.clear();

        loadFilter();

        placeMarkers(chargePointList);

    }

    public void openStationFragment(ChargePoint chargePoint)
    {
        StationFragment stationFragment = StationFragment.newInstance(chargePoint);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_bottom, R.anim.enter_from_bottom, R.anim.exit_to_bottom);
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_container, stationFragment, "STATION_FRAGMENT").commit();
    }

    @Override
    public void stationOnFragmentInteraction()
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

        //  listener for custom click event
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker marker)
            {

                //  gets station
                ChargePoint chargePoint = markers.get(marker);
                openStationFragment(chargePoint);
                return false;
            }
        });

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

    }

    private Bitmap mergeToPin(Bitmap pin, Bitmap green, Bitmap blue, Bitmap red, Bitmap yellow){

        Bitmap result = Bitmap.createBitmap(pin.getWidth(), pin.getHeight(), pin.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(pin, 0f, 0f, null);

        if(type2State)
        {
            canvas.drawBitmap(green, 55, 30, null);
        }

        /*
            canvas.drawBitmap(blue, 85, 60, null);
            canvas.drawBitmap(red, 55, 90, null);
            canvas.drawBitmap(yellow, 25, 60, null);
         */

        return result;
    }
}
