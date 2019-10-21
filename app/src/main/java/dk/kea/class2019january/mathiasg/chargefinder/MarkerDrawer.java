package dk.kea.class2019january.mathiasg.chargefinder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class MarkerDrawer
{
    private Context context;
    private Bitmap pin;
    private Bitmap green;

    public MarkerDrawer(Context context)
    {
        this.context = context;
        this.pin = BitmapFactory.decodeResource(context.getResources(), R.drawable.pin);
        this.green = BitmapFactory.decodeResource(context.getResources(), R.drawable.green_dot);
    }


    public Bitmap getPin()
    {
        return pin;
    }
}

