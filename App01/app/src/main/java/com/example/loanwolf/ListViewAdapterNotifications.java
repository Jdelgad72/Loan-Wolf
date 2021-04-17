package com.example.loanwolf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapterNotifications extends BaseAdapter {

    // Declare Variables
    Context mContext;
    ArrayList<NotificationListObject> notification;
    ArrayList<NotificationListObject> filterList;

    public ListViewAdapterNotifications(Context context, ArrayList<NotificationListObject> notifications) {
        mContext = context;
        this.notification = notifications;
        this.filterList = notifications;
    }

    public int getCount() {
        return notification.size();
    }

    public Object getItem(int position) {
        return notification.get(position);
    }

    public long getItemId(int position) {
        return notification.indexOf(getItem(position));
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view==null){
            view = inflater.inflate(R.layout.list_view_notifications, null);
        }

        TextView type = view.findViewById(R.id.type);
        TextView message = view.findViewById(R.id.message);

        // Set the results into TextViews
        message.setText(notification.get(position).getMessage());
        type.setText(notification.get(position).getType());
        return view;
    }
}
