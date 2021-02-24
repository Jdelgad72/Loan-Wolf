package com.example.loanwolf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter implements Filterable {

    // Declare Variables
    Context mContext;
    ArrayList<UserNames> userNames;
    CustomFilter filter;
    ArrayList<UserNames> filterList;

    public ListViewAdapter(Context context, ArrayList<UserNames> userNames) {
        mContext = context;
        this.userNames = userNames;
        this.filterList = userNames;
    }

    // Filter Class
    @Override
    public Filter getFilter() {
        if(filter == null){
            filter=new CustomFilter();
        }

        return filter;
    }

    @Override
    public int getCount() {
        return userNames.size();
    }

    @Override
    public Object getItem(int position) {
        return userNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return userNames.indexOf(getItem(position));
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view==null){
            view = inflater.inflate(R.layout.list_view_items, null);
        }

        TextView nameTxt = view.findViewById(R.id.TextView2);
        TextView emailTxt = view.findViewById(R.id.email);

        // Set the results into TextViews
        nameTxt.setText(userNames.get(position).getUserName());
        emailTxt.setText(userNames.get(position).getEmail());
        return view;
    }

    class CustomFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint){

            FilterResults results = new FilterResults();

            if(constraint!= null && constraint.length()>0){
                constraint = constraint.toString().toUpperCase();
                ArrayList<UserNames> filters = new ArrayList<UserNames>();

                for(int i=0;i<filterList.size();i++){
                    if(filterList.get(i).getUserName().toUpperCase().contains(constraint)){
                        UserNames p = new UserNames(filterList.get(i).getUserName(), filterList.get(i).getEmail());
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
            userNames = (ArrayList<UserNames>) results.values;
            notifyDataSetChanged();
        }
    }
}