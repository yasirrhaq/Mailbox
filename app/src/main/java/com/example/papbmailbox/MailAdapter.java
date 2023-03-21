package com.example.papbmailbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//Adapter will be used for dataset
public class MailAdapter
        extends RecyclerView.Adapter
        implements Filterable {

    private final Context ctx;
    private List<Mail> mails, filteredMails;

    public MailAdapter(Context ctx, List<Mail> mails) {
        this.ctx = ctx;
        this.mails = mails;
        this.filteredMails = mails;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence search) {
                String query = search.toString().toLowerCase().trim();
                List<Mail> filtered = new ArrayList<>();
                if (query.isEmpty()) {
                    filtered = mails;
                } else {
                    for (Mail m : mails) {
                        if (m.name.toLowerCase().contains(query) ||
                                m.excerpt.toLowerCase().contains(query)) {
                            filtered.add(m);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filtered;
                results.count = filtered.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredMails = (List<Mail>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class VHMail extends RecyclerView.ViewHolder {

        private TextView tvInitial, tvName, tvExcerpt;

        public VHMail(@NonNull View rowView) {
            super(rowView);
            this.tvInitial = rowView.findViewById(R.id.tvtInitial);
            this.tvName = rowView.findViewById(R.id.tvName);
            this.tvExcerpt = rowView.findViewById(R.id.tvExcerpt);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(this.ctx)
                .inflate(R.layout.row_mail, parent, false);
        return new VHMail(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VHMail vh = (VHMail) holder;
        Mail m = this.filteredMails.get(position);
        vh.tvInitial.setText(m.getInitial());
        vh.tvName.setText(m.name);
        vh.tvExcerpt.setText(m.excerpt);
    }

    @Override
    public int getItemCount() {
        return this.filteredMails.size();
    }
}
