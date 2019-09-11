package dk.kea.class2019january.mathiasg.chargefinder;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FilterFragment extends Fragment
{

    private filterOnFragmentInteractionListener mListener;
    private ImageButton closeFilter;

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

    // TODO: Rename method, update argument and hook method into UI event
    public void sendBack()
    {
        if (mListener != null)
        {
            mListener.filterOnFragmentInteraction();
        }
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
