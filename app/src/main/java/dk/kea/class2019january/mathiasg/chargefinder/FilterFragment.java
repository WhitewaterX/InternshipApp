package dk.kea.class2019january.mathiasg.chargefinder;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

public class FilterFragment extends Fragment
{

    private filterOnFragmentInteractionListener mListener;
    private OnDataPass dataPasser;
    private ImageButton closeFilter;
    private Switch type2Switch;
    private Switch chademoSwitch;
    private Switch ccsSwitch;
    private Switch teslaSwitch;

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
        chademoSwitch = view.findViewById(R.id.switch_chademo);
        ccsSwitch = view.findViewById(R.id.switch_ccs);
        teslaSwitch = view.findViewById(R.id.switch_tesla);

        type2Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    passData(true);
                }
                else
                {
                    passData(false);
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



        return view;
    }

    public void sendBack()
    {
        if (mListener != null)
        {
            mListener.filterOnFragmentInteraction();
        }
    }

    public void passData(Boolean data)
    {
        dataPasser.onDataPass(data);
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

        dataPasser =(OnDataPass) context;
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

    public interface OnDataPass
    {
        void onDataPass(Boolean data);
    }
}
