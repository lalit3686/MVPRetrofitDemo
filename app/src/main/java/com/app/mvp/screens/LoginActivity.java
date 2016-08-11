package com.app.mvp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.mvp.demo.R;
import com.app.mvp.mvplogin.LoginCallBack;
import com.app.mvp.mvplogin.LoginLogic;
import com.app.mvp.mvplogin.LoginPresenter;

import static android.widget.Toast.LENGTH_SHORT;

public class LoginActivity extends AppCompatActivity implements LoginCallBack {

    private EditText usernameView;
    private EditText passwordView;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();

        presenter = new LoginPresenter(this, new LoginLogic());
    }

    private void initComponents(){
        usernameView = (EditText) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
    }

    public void MyOnClick(View view){
        switch (view.getId()){
            case R.id.login:
                presenter.authenticateUser();
            break;
        }
    }

    public String getUsername() {
        return usernameView.getText().toString();
    }

    @Override
    public void showUsernameError(int resId) {
        usernameView.setError(getString(resId));
    }

    @Override
    public String getPassword() {
        return passwordView.getText().toString();
    }

    @Override
    public void showPasswordError(int resId) {
        passwordView.setError(getString(resId));
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this, NextActivity.class));
    }

    @Override
    public void showLoginError(int resId) {
        Toast.makeText(this, getString(resId), LENGTH_SHORT).show();
    }
}