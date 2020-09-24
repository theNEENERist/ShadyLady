package com.personal.shadylady;

import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

import static android.content.ContentValues.TAG;

public class FirebaseHelper {
    FirebaseFirestore _db;
    List<Client> _clients;

    public FirebaseHelper() {
        _db = FirebaseFirestore.getInstance();
        _clients = new ArrayList<Client>();
    }

    public void getClients(final FirebaseCallback callback) {
        _db.collection("clients")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Client> clients = new ArrayList<>();

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                _clients = (List<Map<String, Object>>) document.get("clients");

                                Client client = document.toObject(Client.class);
                                clients.add(client);
                            }
                            callback.getClientsCallback(clients);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
//                        ListView list = _hf.getView().findViewById(R.id.list_item);
//                        android.widget.ArrayAdapter arrayAdapter =
//                                new android.widget.ArrayAdapter(list.getContext(), android.R.layout.simple_list_item_1, clients);
//                        list.setAdapter(arrayAdapter);

                    }
                });
    }
}
