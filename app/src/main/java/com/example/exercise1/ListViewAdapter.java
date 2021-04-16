package com.example.exercise1;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;
import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.MyViewHolder>
        implements Filterable {
    private Context context;
    private List<Kontak> contactList;
    private List<Kontak> contactListFiltered;
    private ContactsAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.textView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Kontak kontak = contactListFiltered.get(getAdapterPosition());
                    PopupMenu popup = new PopupMenu(view.getContext(), v);
                    MenuInflater inflater = popup.getMenuInflater();
                    inflater.inflate(R.menu.kontak_menu, popup.getMenu());
                    popup.show();
                    popup.setOnMenuItemClickListener(popupMenuItem -> {
                                switch (popupMenuItem.getItemId()) {

                                    case R.id.mnLihatData:
                                        Intent i = new Intent(view.getContext(), DetailKontakActivity.class);
                                        Bundle b = new Bundle();
                                        b.putString("nama", kontak.getNama());
                                        b.putString("nomor", kontak.getNomor());
                                        i.putExtras(b);
                                        startActivity(view.getContext(), i, b);
                                        break;
                                    case R.id.mnLiatNomor:
                                        Snackbar.make(v, "Nomor :" + kontak.getNomor(), LENGTH_SHORT).show();
                                        break;
                                    default:
                                        break;
                                }
                                return false;
                            }
                    );
//                    // here you can inflate your menu
//                    popup.setDropDownGravity(Gravity.RIGHT);
//                    popup.show();

                }
            });
        }
    }


    public ListViewAdapter(Context context, List<Kontak> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kontak_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Kontak contact = contactListFiltered.get(position);
        holder.name.setText(contact.getNama());
    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contactList;
                } else {
                    List<Kontak> filteredList = new ArrayList<>();
                    for (Kontak row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getNama().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<Kontak>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Kontak contact);
    }
}