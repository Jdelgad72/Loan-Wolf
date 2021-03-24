package com.example.loanwolf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapterLeaderboard {

    // Declare Variables
    Context mContext;
    ArrayList<LeaderboardListObject> Leaderboard;

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
        rank.setText(Leaderboard.get(position).getrank());
        name.setText(Leaderboard.get(position).getname());
        email.setText(Leaderboard.get(position).getemail());
        starRating.setText(Leaderboard.get(position).getstarRating());
        return view;
    }
}
