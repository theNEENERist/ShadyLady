package com.personal.shadylady.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.personal.shadylady.AddClientActivity;
import com.personal.shadylady.ArrayAdapter;
import com.personal.shadylady.Client;
import com.personal.shadylady.CustomAdapter;
import com.personal.shadylady.FirebaseCallback;
import com.personal.shadylady.FirebaseHelper;
import com.personal.shadylady.R;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    FloatingActionButton button;
    FirebaseHelper fbh;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        fbh = new FirebaseHelper();

        fbh.getClients(new FirebaseCallback() {
            @Override
            public void getClientsCallback(List<Client> clientList) {
                ListView list = root.findViewById(R.id.list_item);
                ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.list_content, clientList);
                list.setAdapter(aa);
            }
        });
        addListenerOnButton(root);

        return root;
    }

    public void addListenerOnButton(View root) {
        button = root.findViewById(R.id.add_client);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getActivity(), AddClientActivity.class);
                startActivity(intent);
            }
        });
    }
}