package com.personal.shadylady;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.personal.shadylady.ui.home.HomeFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class CustomAdapter extends android.widget.BaseAdapter{
    LayoutInflater _inflater;
    ArrayList<Client> _items;
    static FirebaseFirestore _db;
    List<Map<String, Object>> _clients;
    HomeFragment _hf;

    public CustomAdapter(Context context) {
        _inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _items = new ArrayList<>();
        _db = FirebaseFirestore.getInstance();
    }

    public CustomAdapter(Context context, HomeFragment hf) {
        _inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _hf = hf;
        _db = FirebaseFirestore.getInstance();

    }

    public static void addClient(Map<String, Object> client) {
        // Create a new user with a first, middle, and last name
//        Map<String, Object> client = new HashMap<>();
//        client.put("first", "Alan");
//        client.put("last", "Turing");
//        client.put("born", 1912);
//        client.put("gender", "Male");
//        client.put("phone", "812-704-9619");
//        client.put("email", "mail1@joel.com");
//        client.put("epay", "Venmo");
//        client.put("notes", "this is a note");

// Add a new document with a generated ID
        _db.collection("clients")
                .add(client)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public List<Map<String, Object>> getClients() {
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
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                        ListView list = _hf.getView().findViewById(R.id.list_item);
                        ArrayAdapter arrayAdapter =
                                new ArrayAdapter(list.getContext(), android.R.layout.simple_list_item_1, clients);
                        list.setAdapter(arrayAdapter);
                    }
                });

        return _clients;
    }

    @Override
    public int getCount() {
        return _items.size();
    }

    @Override
    public Object getItem(int position) {
        return _items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = _inflater.inflate(R.layout.listitem, null);

        ImageView letterImage = view
                .findViewById(R.id.gmailitem_letter);
        TextView titleText = view
                .findViewById(R.id.gmailitem_title);

        ColorGenerator generator = ColorGenerator.MATERIAL;

        Client client = _items.get(position);

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(String.valueOf(client.first.charAt(0)) + client.last.charAt(0), Color.parseColor("#FF7F50"));
        letterImage.setImageDrawable(drawable);
//        titleText.setText(client.first + " " + client.last);

        return view;
    }
}
