package com.example.loanwolf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapterViewReviews extends BaseAdapter {

    // Declare Variables
    Context mContext;
    ArrayList<LeaderboardListObject> ViewReviews;
    ArrayList<LeaderboardListObject> filterList;

    public ListViewAdapterViewReviews(Context context, ArrayList<LeaderboardListObject> viewreviews) {
        mContext = context;
        this.ViewReviews = viewreviews;
        this.filterList = viewreviews;
    }

    public int getCount() {
        return ViewReviews.size();
    }

    public Object getItem(int position) {
        return ViewReviews.get(position);
    }

    public long getItemId(int position) {
        return ViewReviews.indexOf(getItem(position));
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view==null){
            view = inflater.inflate(R.layout.list_view_viewreviews, null);
        }
        TextView name = view.findViewById(R.id.TextView2);
        TextView rating = view.findViewById(R.id.TextView3);
        TextView comment = view.findViewById(R.id.TextView4);

        // Set the results into TextViews
        name.setText(ViewReviews.get(position).getrank());
        rating.setText(ViewReviews.get(position).getname());
        comment.setText(ViewReviews.get(position).getemail());
        return view;
    }
}
