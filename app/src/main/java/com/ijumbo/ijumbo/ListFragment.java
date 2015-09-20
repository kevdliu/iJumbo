package com.ijumbo.ijumbo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ijumbo.ijumbo.hlv.HeaderListView;

public class ListFragment extends Fragment {

    public static final String DATA = "data";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.list_fragment, container, false);

        Bundle args = getArguments();
        HeaderListView list = (HeaderListView) rootView.findViewWithTag("HLVF");
        list.setClickable(true);

        DiningListAdapter adapter = new DiningListAdapter(getActivity(),
                args.getStringArrayList(DATA));
        list.setAdapter(adapter);

        return rootView;
    }
}
