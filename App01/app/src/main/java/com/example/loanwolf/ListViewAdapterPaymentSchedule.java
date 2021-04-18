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

public class ListViewAdapterPaymentSchedule extends BaseAdapter {
    // Declare Variables
    Context mContext;
    ArrayList<PaymentScheduleListObject> paymentSchedule;

    public ListViewAdapterPaymentSchedule(Context context, ArrayList<PaymentScheduleListObject> paymentSchedules) {
        mContext = context;
        this.paymentSchedule = paymentSchedules;
    }

    public int getCount() {
        return paymentSchedule.size();
    }

    public Object getItem(int position) {
        return paymentSchedule.get(position);
    }

    public long getItemId(int position) {
        return paymentSchedule.indexOf(getItem(position));
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view==null){
            view = inflater.inflate(R.layout.list_view_paymentschedule, null);
        }
        TextView startDate= view.findViewById(R.id.TextView2);
        TextView amount = view.findViewById(R.id.TextView3);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = paymentSchedule.get(position).getstartDate();
        try {
            Date date = formatter.parse(dateInString);
            @SuppressLint("SimpleDateFormat") DateFormat out = new SimpleDateFormat("MMM dd, yyyy");
            startDate.setText(out.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Set the results into a TextView
        amount.setText(paymentSchedule.get(position).getamount());
                return view;
    }
}

