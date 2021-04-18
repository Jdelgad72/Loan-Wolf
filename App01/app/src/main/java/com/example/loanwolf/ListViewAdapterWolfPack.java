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

public class ListViewAdapterWolfPack extends BaseAdapter {

    Context mContext;
    ArrayList<WolfPackListObject> wolfPack;
    ListViewAdapterOpenLoans.CustomFilter filter;
    ArrayList<WolfPackListObject> filterList;

    public ListViewAdapterWolfPack(Context context, ArrayList<WolfPackListObject> wolfPack) {
        mContext = context;
        this.wolfPack = wolfPack;
        this.filterList = wolfPack;
    }

    @Override
    public int getCount() {
        return wolfPack.size();
    }

    @Override
    public Object getItem(int position) {
        return wolfPack.get(position);
    }

    @Override
    public long getItemId(int position) {
        return wolfPack.indexOf(getItem(position));
    }

    @SuppressLint("SetTextI18n")
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view==null){
            view = inflater.inflate(R.layout.list_view_wolf_pack, null);
        }

        TextView lenderBorrowerTxtView = view.findViewById(R.id.TextView2);
        TextView amountTxtView = view.findViewById(R.id.TextView3);
        TextView interestTxtView = view.findViewById(R.id.TextView4);
        TextView startDateTxtView = view.findViewById(R.id.TextView5);
        TextView numPaymentsTxtView = view.findViewById(R.id.TextView6);
        TextView timeIntervalTxtView = view.findViewById(R.id.TextView7);

        // Set the results into TextViews
        lenderBorrowerTxtView.setText(wolfPack.get(position).getBorrowerLender()+"s");
        amountTxtView.setText(wolfPack.get(position).getAmount());
        interestTxtView.setText(String.valueOf(Float.valueOf(wolfPack.get(position).getInterestRate()) * 100));
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = wolfPack.get(position).getStartDate();
        try {
            Date date = formatter.parse(dateInString);
            @SuppressLint("SimpleDateFormat") DateFormat out = new SimpleDateFormat("MMM dd, yyyy");
            startDateTxtView.setText(out.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        numPaymentsTxtView.setText(wolfPack.get(position).getNumPayments());
        timeIntervalTxtView.setText(wolfPack.get(position).getPaymentType());
        return view;
    }

}
