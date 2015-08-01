package com.tomek.ujebse;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;



public class HistoryFragment extends Fragment {
    private ListView listview;


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinksAdapter linksAdapter = new LinksAdapter(getActivity().getApplicationContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        listview = (ListView) view.findViewById(R.id.list_view);
        listview.setAdapter(linksAdapter);
        return view;
    }


}
