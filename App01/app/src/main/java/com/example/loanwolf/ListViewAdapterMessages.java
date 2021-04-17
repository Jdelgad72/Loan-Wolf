package com.example.loanwolf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        TextView nameTxtView = view.findViewById(R.id.textView);
        TextView messageTxtView = view.findViewById(R.id.message);
        TextView dateTxtView = view.findViewById(R.id.date);
        TextView timeTxtView = view.findViewById(R.id.time);
        TextView statusTxtView = view.findViewById(R.id.type);


        // Set the results into TextViews
        nameTxtView.setText(messages.get(position).getName());
        messageTxtView.setText(messages.get(position).getMessage());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = messages.get(position).getDate();
        try {
            Date date = formatter.parse(dateInString);
            @SuppressLint("SimpleDateFormat") DateFormat out = new SimpleDateFormat("MMM dd, yyyy");
            dateTxtView.setText(out.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

