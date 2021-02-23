package com.example.loanwolf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<UserNames> userNamesList;
    private ArrayList<UserNames> arraylist;

    public ListViewAdapter(Context context, ArrayList<UserNames> userNamesList) {
        mContext = context;
        this.userNamesList = userNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<UserNames>();
        this.arraylist.addAll(userNamesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return userNamesList.size();
    }

    @Override
    public UserNames getItem(int position) {
        return userNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.TextView2);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(userNamesList.get(position).getUserName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        userNamesList.clear();
        if (charText.length() == 0) {
            userNamesList.addAll(arraylist);
        } else {
            for (UserNames wp : arraylist) {
                if (wp.getUserName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    userNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}