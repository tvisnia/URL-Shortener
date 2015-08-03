package com.tomek.ujebse;


import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orm.SugarDb;
import com.orm.SugarRecord;

import org.json.JSONObject;

import rest.client.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;


public class ShortcuttingFragment extends Fragment {

    public static final String LOG_TAG = "Shortcutting Fragment: ";
    public static final String ERROR_MESSAGE = "Wystąpił błąd";
    private static String originalLink;
    private static String shortcutLink;
    private Button button;
    private EditText inputLine;
    private EditText outputLine;

    private ProgressDialog progress;


    public ShortcuttingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_shortcutting, container, false);
        inputLine = (EditText) view.findViewById(R.id.inputLine);
        outputLine = (EditText) view.findViewById(R.id.outputLine);
        button = (Button) view.findViewById(R.id.shortcuttingButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                originalLink = String.valueOf(inputLine.getText());

                RestClient restClient = new RestClient();
                progress = ProgressDialog.show(getActivity(), "Ujebywanie...", "Proszę czekać...");
                restClient.getConnectionInterface().getLink(originalLink, new Callback<String>() {
                    @Override
                    public void success(String s, Response response) {
                        progress.dismiss();
                        Log.d("success! :", s);
                        if (s.startsWith("Error")) {
                            failure(null);
                        } else {
                            shortcutLink = s;
                            outputLine.setText(s);
                            Links linksDB = new Links(originalLink, shortcutLink);
                            linksDB.save();
                        }
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        progress.dismiss();
                        Utils.makeLongToast(getActivity(), ERROR_MESSAGE);
                    }
                });
            }
        });
        return view;
    }


}
