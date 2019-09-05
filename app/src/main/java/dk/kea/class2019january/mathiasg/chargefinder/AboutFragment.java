package dk.kea.class2019january.mathiasg.chargefinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AboutFragment extends Fragment
{

    private ImageButton closeButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View RootView = inflater.inflate(R.layout.about_layout, container, false);
        closeButton = (ImageButton) RootView.findViewById(R.id.closeAbout);


        closeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getFragmentManager().beginTransaction().hide(AboutFragment.this).commit();

            }
        });

        return RootView;
    }

}
