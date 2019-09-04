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
    private ImageButton btnClose;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.about_layout, container, false);
        btnClose = (ImageButton) view.findViewById(R.id.closeAbout);

        btnClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                //  TODO: Navigate to main / close fragment
            }
        });

        return view;
    }
}
