package com.example.loanwolf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapterLeaderboard extends BaseAdapter {

    // Declare Variables
    Context mContext;
    ArrayList<LeaderboardListObject> leaderboard;
    ArrayList<LeaderboardListObject> filterList;

    public ListViewAdapterLeaderboard(Context context, ArrayList<LeaderboardListObject> leaderboards) {
        mContext = context;
        this.leaderboard = leaderboards;
        this.filterList = leaderboards;
    }

    public int getCount() {
        return leaderboard.size();
    }

    public Object getItem(int position) {
        return leaderboard.get(position);
    }

    public long getItemId(int position) {
        return leaderboard.indexOf(getItem(position));
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view==null){
            view = inflater.inflate(R.layout.list_view_leaderboard, null);
        }
        TextView rank = view.findViewById(R.id.TextView2);
        TextView name = view.findViewById(R.id.TextView3);
        TextView email = view.findViewById(R.id.TextView4);
        TextView starRating = view.findViewById(R.id.TextView5);

        // Set the results into TextViews
        rank.setText(leaderboard.get(position).getrank());
        name.setText(leaderboard.get(position).getname());
        email.setText(leaderboard.get(position).getemail());
        starRating.setText(leaderboard.get(position).getstarRating());
        return view;
    }
}
