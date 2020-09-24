package com.personal.shadylady;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

import static android.content.ContentValues.TAG;

public class ArrayAdapter extends android.widget.ArrayAdapter<Client> {
    LayoutInflater _inflater;
    List<Client> _clients;

    public ArrayAdapter(@NonNull Context context, int resource, @NonNull List<Client> clients) {
        super(context, resource, clients);
        _inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _clients = clients;
    }

    @Override
    public int getCount() {
        return _clients.size();
    }

    @Override
    public Client getItem(int position) {
        return _clients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = _inflater.inflate(R.layout.listitem, null);
        View rowView = _inflater.inflate(R.layout.listitem, parent, false);

        ImageView letterImage = rowView
                .findViewById(R.id.gmailitem_letter);
        TextView titleText = rowView
                .findViewById(R.id.gmailitem_title);

        ColorGenerator generator = ColorGenerator.MATERIAL;

        Client client = _clients.get(position);

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(String.valueOf(client.first.charAt(0)) + client.last.charAt(0), Color.parseColor("#FF7F50"));
        letterImage.setImageDrawable(drawable);
        titleText.setText(client.first + " " + client.last);

        return rowView;
    }
}
