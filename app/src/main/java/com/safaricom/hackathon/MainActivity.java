package com.safaricom.hackathon;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.safaricom.hackathon.Adapter.ChallengeDataAdapter;
import com.safaricom.hackathon.Models.ChallengeModelClass;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Initialization
    private Button mCalculate;
    private TextInputEditText mStartingAmount;
    private ArrayList<Integer> amountList = new ArrayList<>();
    private ArrayList<Integer> weeksList = new ArrayList<>();
    private ArrayList<Integer> totalAmountList = new ArrayList<>();
    private String startingAmount;
    private TextInputLayout mLayout;
    public  ArrayList<ChallengeModelClass> mData = new ArrayList<>();
    private ChallengeDataAdapter mAdapter;
    private RecyclerView recyclerView;
    private LinearLayout mHeaderLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCalculate = findViewById(R.id.next);
        mStartingAmount = findViewById(R.id.startingAmount);
        mLayout = findViewById(R.id.amountLayout);
        mCalculate.setOnClickListener(this);
        mCalculate.setEnabled(false);
        mCalculate.setAlpha(.5f);

        mHeaderLayout = findViewById(R.id.headerLayout);
        mAdapter = new ChallengeDataAdapter(mData);


        //Text Watcher
        mStartingAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                startingAmount = mStartingAmount.getText().toString().trim();
                if (!startingAmount.isEmpty()) {
                    mCalculate.setEnabled(true);
                    mCalculate.setAlpha(1);
                }
            }
        });
        recyclerView =  findViewById(R.id.recycler_view1);

    }


    //Onclick function
    @Override
    public void onClick(View v) {
        if(Integer.parseInt(startingAmount) <= 0){
            mLayout.setError("Deposit amount must be greater than Ksh.1");
        }else if(Integer.parseInt(startingAmount) > 50000000){
            mLayout.setError("Deposit amount must be less than Ksh.50,000,000");

        }else {
            computeValues();
        }
    }


    private void computeValues() {
        mHeaderLayout.setVisibility(View.VISIBLE);
        mData.clear();

        int weeks = 53;
        for (int i = 1; i < weeks; i++) {
            weeksList.add(i);
            amountList.add(i * Integer.parseInt(startingAmount));
            totalAmountList.add(i * Integer.parseInt(startingAmount) + ((i - 1) * Integer.parseInt(startingAmount)));
            ChallengeModelClass challengeModelClass = new ChallengeModelClass(i, i * Integer.parseInt(startingAmount), (i * Integer.parseInt(startingAmount) + ((i - 1) * Integer.parseInt(startingAmount))));
            mData.add(challengeModelClass);
            mAdapter.notifyDataSetChanged();
        }

        mAdapter = new ChallengeDataAdapter(mData);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

    }

}