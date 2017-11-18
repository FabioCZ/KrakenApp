package com.pxp200.krakenapp;

import android.content.Intent;
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

import com.pxp200.krakenapp.Storage.UsernamePreference;
import com.pxp200.krakenapp.model.User;
import com.pxp200.krakenapp.model.UserResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {
    @BindView(R.id.sign_in_username) EditText usernameEdit;
    @BindView(R.id.sign_in_continue) Button continueButton;
    @BindView(R.id.sign_in_progress) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        if(UsernamePreference.exists(this)) {
            usernameEdit.setText(UsernamePreference.get(this));
            onContinueClick();
        }

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
        final String name = usernameEdit.getText().toString();
        if(name == null || name.length() == 0) {
            Toast.makeText(this, "Yo, enter in a username", Toast.LENGTH_LONG).show();
        } else {
            continueButton.setEnabled(false);
            usernameEdit.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.animate();
            KrakenApplication.getKrakenApi(this).getUser(new User(name))
                    .enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            UsernamePreference.set(getApplicationContext(), response.body().getName());
                            Intent intent = new Intent(SignInActivity.this, MapsActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
//                        String username = usernameEdit.getText().toString();
//                        UsernamePreference.set(getApplicationContext(), username);
//
//                        Intent intent = new Intent(SignInActivity.this, MapsActivity.class);
//                        startActivity(intent);
//                        finish();
                            progressBar.setVisibility(View.GONE);
                            continueButton.setEnabled(true);
                            usernameEdit.setEnabled(true);
                            Toast.makeText(SignInActivity.this, "Error logging in", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}
