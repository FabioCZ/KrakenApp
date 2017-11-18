package com.pxp200.krakenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {
    @BindView(R.id.sign_in_username)
    EditText usernameEdit;

    @BindView(R.id.sign_in_continue)
    Button continueButton;

    @BindView(R.id.sign_in_progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        usernameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onContinueClick();
                    return true;
                }
                return false;
            }
        });
    }

    @OnClick(R.id.sign_in_continue)
    public void onContinueClick() {
        continueButton.setEnabled(false);
        usernameEdit.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.animate();
        KrakenApplication.getKrakenApi(this).getUserData(usernameEdit.getText().toString())
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Intent intent = new Intent(SignInActivity.this, MapsActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Intent intent = new Intent(SignInActivity.this, MapsActivity.class);
                        startActivity(intent);
                        finish();
                        //progressBar.setVisibility(View.GONE);
                        //continueButton.setEnabled(true);
                        //usernameEdit.setEnabled(true);
                        //Toast.makeText(SignInActivity.this, "Error logging in", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
