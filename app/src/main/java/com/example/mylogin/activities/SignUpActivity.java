package com.example.mylogin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.mylogin.Model.Account;
import com.example.mylogin.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    private static final String ACCOUNTS_SIGNED = "com.example.mylogin.activities.accounts";
    private TextView mUserNameText;
    private TextView mPasswordText;
    private Button mSignUp;
    private ArrayList<Account> mAccounts = new ArrayList<>();
    private CoordinatorLayout mSignUpCoordinatorLayout;
    public static String UPDATED_ACCOUNTS = "com.example.mylogin.activities.updatedAccounts";
    public final static String SIGN_UP_USER_NAME = "com.example.mylogin.activities.SignUpUserName";
    public final static String SIGN_UP_PASSWORD = "com.example.mylogin.activities.SignUpPassword";
    private Intent signUpIntent = new Intent();

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ACCOUNTS_SIGNED, mAccounts);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);
        findAllViews();
        Intent intentSignUp = getIntent();
        mAccounts = (ArrayList<Account>) intentSignUp.
                getSerializableExtra("com.example.mylogin.activities.Accounts");
        mUserNameText.setText(intentSignUp.
                getStringExtra("com.example.mylogin.activities.tempUserName"));
        mPasswordText.setText(intentSignUp.
                getStringExtra("com.example.mylogin.activities.tempPassword"));
        if (savedInstanceState != null && savedInstanceState.getSerializable(ACCOUNTS_SIGNED) != null
        && savedInstanceState.getSerializable(ACCOUNTS_SIGNED) instanceof ArrayList)
            mAccounts = (ArrayList<Account>) savedInstanceState.getSerializable(ACCOUNTS_SIGNED);
        setOnClickListeners();

    }

    private void findAllViews() {
        mUserNameText = findViewById(R.id.user_name_sign_up);
        mPasswordText = findViewById(R.id.password_text_sign_up);
        mSignUp = findViewById(R.id.signup_button);
        mSignUpCoordinatorLayout = findViewById(R.id.coordinator_layout_sign_up);
    }

    private void setOnClickListeners() {
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account temp = new Account(mUserNameText.getText().toString(),mPasswordText.getText().toString());
               if (mAccounts.contains(temp)){
                   Snackbar.make(mSignUpCoordinatorLayout,
                           getString(R.string.sign_up_failed_message),
                           Snackbar.LENGTH_LONG).show();
               }else{
                   addAccount();
                putExtras();}
            }
        });
    }

    private void addAccount() {
        mAccounts.add(new Account(mUserNameText.getText().toString(),
                mPasswordText.getText().toString()));
        Snackbar.make(mSignUpCoordinatorLayout,
                getString(R.string.sign_up_successful_message),
                Snackbar.LENGTH_LONG).show();
    }

    private void putExtras() {
        signUpIntent.putExtra(UPDATED_ACCOUNTS, mAccounts);
        signUpIntent.putExtra(SIGN_UP_USER_NAME, mUserNameText.getText().toString());
        signUpIntent.putExtra(SIGN_UP_PASSWORD, mPasswordText.getText().toString());
        setResult(RESULT_OK, signUpIntent);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);


    }
}
