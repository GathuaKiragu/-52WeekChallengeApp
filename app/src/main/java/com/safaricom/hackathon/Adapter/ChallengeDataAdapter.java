package com.safaricom.hackathon.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.safaricom.hackathon.Models.ChallengeModelClass;
import com.safaricom.hackathon.R;

import java.util.List;

public class ChallengeDataAdapter extends RecyclerView.Adapter<ChallengeDataAdapter.MyViewHolder> {

    private List<ChallengeModelClass> challangeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView week, depositAmount, totalAmount;

        public MyViewHolder(View view) {
            super(view);
            week =  view.findViewById(R.id.tvWeek);
            depositAmount = view.findViewById(R.id.tvDeposit);
            totalAmount = view.findViewById(R.id.tvTotal);
        }
    }


    public ChallengeDataAdapter(List<ChallengeModelClass> challangeList) {
        this.challangeList = challangeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ChallengeModelClass challenge = challangeList.get(position);
        holder.week.setText(String.valueOf(challenge.getWeek()));
        holder.depositAmount.setText(String.valueOf(challenge.getDepositAmount()));
        holder.totalAmount.setText(String.valueOf(challenge.getTotalAmount()));
    }

    @Override
    public int getItemCount() {
        return challangeList.size();
    }
}