package com.app.mvp.mvplogin;

public interface LoginCallBack {
  String getUsername();

  void showUsernameError(int resId);

  String getPassword();

  void showPasswordError(int resId);

  void startMainActivity();

  void showLoginError(int resId);
}
