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
    private static final String TYPE2 = "type2";

    private filterOnFragmentInteractionListener mListener;

    private ImageButton closeFilter;

    private Switch type2Switch;
    private Switch chademoSwitch;
    private Switch ccsSwitch;
    private Switch teslaSwitch;

    private boolean type2State;


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

        closeFilter = view.findViewById(R.id.closeFilter);
        type2Switch = view.findViewById(R.id.switch_type2);

        type2Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    saveFilter(true);
                }
                else
                {
                    saveFilter(false);
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

        loadFilter();
        updateViews();

        return view;
    }

    public void saveFilter(Boolean state)
    {
        Log.d(TAG, "saveFilter: called. Status is " + state);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(TYPE2, state);
        editor.apply();
    }

    public void loadFilter()
    {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        type2State = sharedPreferences.getBoolean(TYPE2, false);
    }

    public void sendBack()
    {
        if (mListener != null)
        {
            mListener.filterOnFragmentInteraction();
        }
    }

    public void updateViews()
    {
        type2Switch.setChecked(type2State);
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
