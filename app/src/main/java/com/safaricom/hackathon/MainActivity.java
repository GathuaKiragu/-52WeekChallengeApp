package com.safaricom.hackathon;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCalculate = findViewById(R.id.next);
        mStartingAmount = findViewById(R.id.startingAmount);
        mLayout = findViewById(R.id.amountLayout);
        mCalculate.setOnClickListener(this);
        mCalculate.setEnabled(false);


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
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(Integer.parseInt(startingAmount) < 0){
            mLayout.setError("Deposit amount must be greater than Ksh.1");
        }else if(Integer.parseInt(startingAmount) > 50000000){
            mLayout.setError("Deposit amount must be less than Ksh.50,000,000");

        }else {
            computeValues();
        }

    }


    private void computeValues() {
        int weeks = 52;
        for (int i = 0; i < weeks; i++) {
            weeksList.add(i);
            amountList.add(i * Integer.parseInt(startingAmount));
            totalAmountList.add(i * Integer.parseInt(startingAmount) + ((i - 1) * Integer.parseInt(startingAmount)));

        }

        Log.e("Weeks", "Weeks" + weeksList.toString());
        Log.e("Amount to Deposit", "Weeks" + amountList.toString());
        Log.e("Total Amount", "Weeks" + totalAmountList.toString());


    }
}