package com.example.loanwolf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListViewAdapterOpenLoans extends BaseAdapter implements Filterable {

    // Declare Variables
    Context mContext;
    ArrayList<OpenLoanListObject> openLoans;
    ListViewAdapterOpenLoans.CustomFilter filter;
    ArrayList<OpenLoanListObject> filterList;

    public ListViewAdapterOpenLoans(Context context, ArrayList<OpenLoanListObject> openLoans) {
        mContext = context;
        this.openLoans = openLoans;
        this.filterList = openLoans;
    }

    // Filter Class
    @Override
    public Filter getFilter() {
        if(filter == null){
            filter=new ListViewAdapterOpenLoans.CustomFilter();
        }

        return filter;
    }

    @Override
    public int getCount() {
        return openLoans.size();
    }

    @Override
    public Object getItem(int position) {
        return openLoans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return openLoans.indexOf(getItem(position));
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view==null){
            view = inflater.inflate(R.layout.list_view_open_loan, null);
        }

        TextView lenderBorrowerTxtView = view.findViewById(R.id.TextView2);
        TextView amountTxtView = view.findViewById(R.id.TextView3);
        TextView interestTxtView = view.findViewById(R.id.TextView4);
        TextView startDateTxtView = view.findViewById(R.id.TextView5);
        TextView numPaymentsTxtView = view.findViewById(R.id.TextView6);
        TextView timeIntervalTxtView = view.findViewById(R.id.TextView7);

        // Set the results into TextViews
        lenderBorrowerTxtView.setText(openLoans.get(position).getBorrowerLender());
        amountTxtView.setText(openLoans.get(position).getAmount());
        interestTxtView.setText(String.valueOf(Float.valueOf(openLoans.get(position).getInterestRate()) * 100));
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = openLoans.get(position).getStartDate();
        try {
            Date date = formatter.parse(dateInString);
            @SuppressLint("SimpleDateFormat") DateFormat out = new SimpleDateFormat("MMM dd, yyyy");
            startDateTxtView.setText(out.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        numPaymentsTxtView.setText(openLoans.get(position).getNumPayments());
        timeIntervalTxtView.setText(openLoans.get(position).getPaymentType());
        return view;
    }

    class CustomFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint){

            FilterResults results = new FilterResults();

            if(constraint!= null && constraint.length()>0){
                constraint = constraint.toString().toUpperCase();
                ArrayList<OpenLoanListObject> filters = new ArrayList<OpenLoanListObject>();

                for(int i=0;i<filterList.size();i++){
                    if(filterList.get(i).getBorrowerLender().toUpperCase().contains(constraint)){
                        OpenLoanListObject p = new OpenLoanListObject(filterList.get(i).getBorrowerLender(), filterList.get(i).getOpenLoanID(), filterList.get(i).getAmount(), filterList.get(i).getInterestRate(), filterList.get(i).getPaymentType(), filterList.get(i).getStartDate(), filterList.get(i).getNumPayments());
                        filters.add(p);
                    }
                }
                results.count=filters.size();
                results.values=filters;
            }else{
                results.count=filterList.size();
                results.values=filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results){
            openLoans = (ArrayList<OpenLoanListObject>) results.values;
            notifyDataSetChanged();
        }
    }
}
