package com.example.loanwolf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapterMessages extends BaseAdapter{

    // Declare Variables
    Context mContext;
    ArrayList<MessageListObject> messages;
    ArrayList<MessageListObject> filterList;

    public ListViewAdapterMessages(Context context, ArrayList<MessageListObject> messages) {
        mContext = context;
        this.messages = messages;
        this.filterList = messages;
    }

    public int getCount() {
        return messages.size();
    }

    public Object getItem(int position) {
        return messages.get(position);
    }

    public long getItemId(int position) {
        return messages.indexOf(getItem(position));
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view==null){
            view = inflater.inflate(R.layout.list_view_messages, null);
        }

        TextView nameTxtView = view.findViewById(R.id.name);
        TextView messageTxtView = view.findViewById(R.id.message);
        TextView dateTxtView = view.findViewById(R.id.date);
        TextView timeTxtView = view.findViewById(R.id.time);
        TextView statusTxtView = view.findViewById(R.id.status);


        // Set the results into TextViews
        nameTxtView.setText(messages.get(position).getName());
        messageTxtView.setText(messages.get(position).getMessage());
        dateTxtView.setText(messages.get(position).getDate());
        timeTxtView.setText(messages.get(position).getTime());
        statusTxtView.setText(messages.get(position).getStatus());

        /*if(messages.get(position).getStatus().equals("Sender")){
            RelativeLayout.LayoutParams layoutParams =(RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT); //ALIGN_PARENT_RIGHT / LEFT etc.
            view.setLayoutParams(layoutParams);
        }*/
        return view;
    }
}

