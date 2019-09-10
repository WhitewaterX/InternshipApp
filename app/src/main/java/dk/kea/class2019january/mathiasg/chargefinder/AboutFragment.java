package dk.kea.class2019january.mathiasg.chargefinder;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class AboutFragment extends Fragment
{

    private OnFragmentInteractionListener mListener;
    private ImageButton closeAbout;

    public AboutFragment()
    {
        // Required empty public constructor
    }


    public static AboutFragment newInstance()
    {
        AboutFragment fragment = new AboutFragment();

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
        View view = inflater.inflate(R.layout.about_layout, container, false);

        closeAbout = view.findViewById(R.id.closeAbout);

        closeAbout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendBack();
            }
        });

        return view;
    }


    //  Closes the about tab
    private void sendBack()
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
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


    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction();
    }
}
