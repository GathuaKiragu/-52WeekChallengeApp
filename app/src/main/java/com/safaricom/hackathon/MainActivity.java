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
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
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
        mCalculate.setEnabled(false);
        mCalculate.setAlpha(.5f);
        mCalculate.setVisibility(View.GONE);
        mHeaderLayout = findViewById(R.id.headerLayout);
        mAdapter = new ChallengeDataAdapter(mData);
        recyclerView =  findViewById(R.id.recycler_view1);

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

                    computeValues();

                }
            }
        });

    }

    private void computeValues() {
        mHeaderLayout.setVisibility(View.VISIBLE);
        mData.clear();

        Calendar c = Calendar.getInstance();
        Date date=c.getTime();

        c.setTime(date);

        int weekOfYear = c.get(Calendar.WEEK_OF_YEAR);
        int weeks = 53;
        for (int i = weekOfYear; i < weeks; i++) {
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