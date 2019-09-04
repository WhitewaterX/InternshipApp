package dk.kea.class2019january.mathiasg.chargefinder;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter
{

    //List of the fragments
    private final List<Fragment> mFragmentList = new ArrayList<>();
    //List of the fragment titles
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public SectionsStatePagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title)
    {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        return mFragmentList.size();
    }

}

