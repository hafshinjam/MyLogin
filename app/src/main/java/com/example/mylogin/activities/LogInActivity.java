package com.example.mylogin.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mylogin.Model.Account;
import com.example.mylogin.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import static com.example.mylogin.activities.SignUpActivity.SIGN_UP_PASSWORD;
import static com.example.mylogin.activities.SignUpActivity.SIGN_UP_USER_NAME;
import static com.example.mylogin.activities.SignUpActivity.UPDATED_ACCOUNTS;


public class LogInActivity extends AppCompatActivity {
    private String ACCOUNTS = "com.example.mylogin.activities.Accounts";
    private String TEMP_USER_NAME = "com.example.mylogin.activities.tempUserName";
    private String TEMP_PASSWORD = "com.example.mylogin.activities.tempPassword";
    private CoordinatorLayout mCoordinatorLayout;
    private TextView mUsernameTextView;
    private TextView mPasswordTextView;
    private Button mLogInButton;
    private Button mSignUpButton;
    private ArrayList<Account> mAccounts = new ArrayList<>();
    public static final int LOG_IN_ACTIVITY_REQUEST_CODE = 0;
    private String ACCOUNTS_SAVED = "com.example.mylogin.activities.accounts";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED && data == null)
            return;
        if (requestCode == LOG_IN_ACTIVITY_REQUEST_CODE) {
            mAccounts = (ArrayList<Account>) data.getSerializableExtra(UPDATED_ACCOUNTS);
            mUsernameTextView.setText(data.getStringExtra(SIGN_UP_USER_NAME));
            mPasswordTextView.setText(data.getStringExtra(SIGN_UP_PASSWORD));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ACCOUNTS_SAVED, mAccounts);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        if (savedInstanceState != null && savedInstanceState.getSerializable(ACCOUNTS_SAVED) != null)
            mAccounts = (ArrayList<Account>) savedInstanceState.getSerializable(ACCOUNTS_SAVED);
        findAllViews();
        setOnClickListeners();
    }

    public void findAllViews() {
        mUsernameTextView = findViewById(R.id.user_name_sign_up);
        mPasswordTextView = findViewById(R.id.password_text_sign_up);
        mLogInButton = findViewById(R.id.login_button);
        mSignUpButton = findViewById(R.id.signup_button);
        mCoordinatorLayout = findViewById(R.id.coordinator_layout_sign_in);
    }

    public void setOnClickListeners() {
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAccounts.size() > 0) {
                    for (int i = 0; i < mAccounts.size(); i++) {
                        if (mAccounts.get(i).getUserName().equals(mUsernameTextView.getText().toString())) {
                            if (mAccounts.get(i).getPassword().equals(mPasswordTextView.getText().toString())) {
                                Snackbar.make(mCoordinatorLayout,
                                        getString(R.string.login_message), Snackbar.LENGTH_LONG).show();
                                break;
                            } else
                                Snackbar.make(mCoordinatorLayout,
                                        getString(R.string.login_failed_message), Snackbar.LENGTH_LONG).show();
                        } else if (!mAccounts.get(i).getUserName().equals(mUsernameTextView.getText().toString())
                                && i == mAccounts.size() - 1)
                            Snackbar.make(mCoordinatorLayout,
                                    getString(R.string.login_failed_message), Snackbar.LENGTH_LONG).show();
                    }
                } else Snackbar.make(mCoordinatorLayout,
                        getString(R.string.login_failed_message), Snackbar.LENGTH_LONG).show();
            }
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                intent.putExtra(ACCOUNTS, mAccounts);
                intent.putExtra(TEMP_USER_NAME, mUsernameTextView.getText().toString());
                intent.putExtra(TEMP_PASSWORD, mPasswordTextView.getText().toString());
                startActivityForResult(intent, LOG_IN_ACTIVITY_REQUEST_CODE);
            }
        });
    }
}