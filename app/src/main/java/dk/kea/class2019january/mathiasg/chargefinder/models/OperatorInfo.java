package dk.kea.class2019january.mathiasg.chargefinder.models;

import com.google.gson.annotations.Expose;

public class OperatorInfo
{

    @Expose
    private String title;

    public OperatorInfo(String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "OperatorInfo{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle()
    {
        return title;
    }
}
