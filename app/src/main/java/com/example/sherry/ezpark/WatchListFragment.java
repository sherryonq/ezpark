package com.example.sherry.ezpark;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WatchListFragment extends Fragment {
    ArrayList<Integer> mSelectedLocation;
    List<String> confirmedLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_watch_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Watch List");
        View v = getView();

        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedLocation = new ArrayList<>();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("Select a location")
                        .setMultiChoiceItems(R.array.LocationArray, null,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which,
                                                        boolean isChecked) {
                                        if (isChecked) {
                                            mSelectedLocation.add(which);
                                        } else if (mSelectedLocation.contains(which)) {
                                            mSelectedLocation.remove(Integer.valueOf(which));
                                        }
                                    }
                                })

                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                String[] array = getResources().getStringArray(R.array.LocationArray);
                                confirmedLocation = new ArrayList<>();
                                for (int i : mSelectedLocation) {
                                    confirmedLocation.add(array[i]);
                                }

                                final View v = getView();
                                final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                                        R.layout.simple_tv, R.id.text1, confirmedLocation);

                                ListView listView = v.findViewById(R.id.list_view);
                                listView.setAdapter(adapter);

                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                        confirmedLocation.remove(position);
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mSelectedLocation.clear();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}
