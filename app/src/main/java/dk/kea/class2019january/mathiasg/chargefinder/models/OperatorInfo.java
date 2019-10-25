package dk.kea.class2019january.mathiasg.chargefinder.models;

public class OperatorInfo
{

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
