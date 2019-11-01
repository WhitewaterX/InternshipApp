//TODO: rest of station fragment layout
//TODO: maybe zoom map to device location

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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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

    //  Map
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

    //  places markers on map, triggered when pressing close button on filter fragment
    public void placeMarkers(List<ChargePoint> chargePointList)
    {
        for(ChargePoint chargePoint : chargePointList)
        {
            //  coordinates of the charge station, used for marker
            LatLng pos = new LatLng(chargePoint.getAddressInfo().getLatitude(), chargePoint.getAddressInfo().getLongitude());

            //  flags for which colors to draw
            boolean greenColor = false;
            boolean blueColor = false;
            boolean redColor = false;
            boolean yellowColor = false;

            for(Connection connection : chargePoint.getConnections())
            {
                //  if the user has filtered for it, and one of the connections to a charge point
                //  has the desired type, set flag to true

                if(type2State && connection.getConnectionType().getTitle().contains("Type 2 ("))
                {
                    greenColor = true;
                }

                if(chademoState && connection.getConnectionType().getTitle().contains("CHAdeMO"))
                {
                    blueColor = true;
                }

                if(teslaState && connection.getConnectionType().getTitle().contains("Tesla"))
                {
                    redColor = true;
                }

                if(ccsState && connection.getConnectionType().getTitle().contains("CCS"))
                {
                    yellowColor = true;
                }
            }

            //  if any flag was set to true, draw markers.
            if(greenColor || blueColor || redColor || yellowColor)
            {
                //  merges pin and color bitmaps for a marker, passes booleans so mergeToPin knows
                //  which to draw
                Bitmap merged = mergeToPin(greenColor, blueColor, redColor, yellowColor);

                //  places markers, based on pin
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(pos)
                        .icon(BitmapDescriptorFactory.fromBitmap(merged)));
                markers.put(marker, chargePoint);
            }
        }
    }

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

        markers.clear();
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

    private Bitmap mergeToPin(boolean greenColor, boolean blueColor, boolean redColor, boolean yellowColor)
    {
        Bitmap pin = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.pin);
        Bitmap green = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.green_dot);
        Bitmap blue = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.blue_dot);
        Bitmap red = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.red_dot);
        Bitmap yellow = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.yellow_dot);

        Bitmap result = Bitmap.createBitmap(pin.getWidth(), pin.getHeight(), pin.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(pin, 0f, 0f, null);

        if(greenColor)
        {
            canvas.drawBitmap(green, 55, 30, null);
        }

        if(blueColor)
        {
            canvas.drawBitmap(blue, 85, 60, null);
        }

        if(redColor)
        {
            canvas.drawBitmap(red, 55, 90, null);
        }

        if(yellowColor)
        {
            canvas.drawBitmap(yellow, 25, 60, null);
        }

        return result;
    }
}
