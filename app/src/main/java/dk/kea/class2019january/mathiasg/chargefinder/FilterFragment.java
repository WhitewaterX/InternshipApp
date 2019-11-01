package dk.kea.class2019january.mathiasg.chargefinder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

public class FilterFragment extends Fragment
{
    private static final String TAG = "FilterFragment";
    private static final String SHARED_PREFS = "sharedPrefs";
    public static final String TYPE2 = "type2";
    public static final String CHADEMO = "chademo";
    public static final String TESLA = "tesla";
    public static final String CCS = "ccs";

    private filterOnFragmentInteractionListener mListener;

    private ImageButton closeFilter;

    private Switch type2Switch;
    private Switch chademoSwitch;
    private Switch ccsSwitch;
    private Switch teslaSwitch;

    private boolean type2State;
    private boolean chademoState;
    private boolean teslaState;
    private boolean ccsState;


    public FilterFragment()
    {
        // Required empty public constructor
    }

    public static FilterFragment newInstance()
    {
        FilterFragment fragment = new FilterFragment();
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
        View view = inflater.inflate(R.layout.filter_layout, container, false);

        //  filter switches for toggling markers
        closeFilter = view.findViewById(R.id.closeFilter);
        type2Switch = view.findViewById(R.id.switch_type2);
        chademoSwitch = view.findViewById(R.id.switch_chademo);
        teslaSwitch = view.findViewById(R.id.switch_tesla);
        ccsSwitch = view.findViewById(R.id.switch_ccs);

        //  each switch saves a state in the sharedprefs

        type2Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    saveFilter(true, TYPE2);
                }
                else
                {
                    saveFilter(false, TYPE2);
                }
            }
        });

        chademoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    saveFilter(true, CHADEMO);
                }
                else
                {
                    saveFilter(false, CHADEMO);
                }
            }
        });

        teslaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    saveFilter(true, TESLA);
                }
                else
                {
                    saveFilter(false, TESLA);
                }
            }
        });

        ccsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    saveFilter(true, CCS);
                }
                else
                {
                    saveFilter(false, CCS);
                }
            }
        });

        closeFilter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendBack();
            }
        });

        //  makes sure the switch state corresponds with the state in sharedprefs upon loading
        loadFilter();
        updateViews();

        return view;
    }

    public void saveFilter(Boolean state, String tag)
    {
        Log.d(TAG, "saveFilter: called. Status is " + state);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(tag, state);
        editor.apply();
    }

    public void loadFilter()
    {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        type2State = sharedPreferences.getBoolean(TYPE2, false);
        chademoState = sharedPreferences.getBoolean(CHADEMO, false);
        teslaState = sharedPreferences.getBoolean(TESLA, false);
        ccsState = sharedPreferences.getBoolean(CCS, false);
    }

    public void sendBack()
    {
        if (mListener != null)
        {
            mListener.filterOnFragmentInteraction();
        }
    }

    //
    public void updateViews()
    {
        type2Switch.setChecked(type2State);
        chademoSwitch.setChecked(chademoState);
        teslaSwitch.setChecked(teslaState);
        ccsSwitch.setChecked(ccsState);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof filterOnFragmentInteractionListener)
        {
            mListener = (filterOnFragmentInteractionListener) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement aboutOnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    public interface filterOnFragmentInteractionListener
    {
        void filterOnFragmentInteraction();
    }
}
