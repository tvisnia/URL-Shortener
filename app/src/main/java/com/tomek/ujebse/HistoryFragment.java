package com.tomek.ujebse;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HistoryFragment extends Fragment {
    private ListView listview;
    private TextView originalLink;
    private ArrayList<Links> linkslist;
    int EXEMPLARY_DATABASE_SIZE = 5;


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);


        listview = (ListView)view.findViewById(R.id.list_view);
        originalLink = (TextView) view.findViewById(R.id.originalLink);

        String[] from = { "original", "shortcut" };
        int[] to = { R.id.originalLink, R.id.shortcutLink };
        ArrayList<Map<String, String>> list = buildData();
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), list,
                R.layout.list_item, from, to);

        listview.setAdapter(adapter);

        return view;
    }

    private ArrayList<Map<String, String>> buildData() {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (int i=0; i <= EXEMPLARY_DATABASE_SIZE; i++ ) {
            list.add(putData("tablicaOryginalnych[i]", "tablicaObciętych[i]"));
        }

        return list;
    }

    private HashMap<String, String> putData(String originalLink, String shortcutLink) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("original", originalLink);
        item.put("shortcut", shortcutLink);
        return item;
    }



}
