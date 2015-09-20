package com.ijumbo.ijumbo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListFragment extends Fragment {

    public static String ROWS = "rows";
    public static String HEADERS = "headers";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.list_fragment, container, false);

        Bundle args = getArguments();
        ((TextView) rootView.findViewById(R.id.text)).setText(args.getString("ARG"));
        return rootView;
    }
}
