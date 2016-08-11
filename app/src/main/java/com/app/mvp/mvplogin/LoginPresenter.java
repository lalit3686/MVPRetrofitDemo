package com.app.mvp.mvplogin;

import android.text.TextUtils;

import com.app.mvp.demo.R;

public class LoginPresenter {
  private LoginCallBack callBack;
  private LoginLogic logic;

  public LoginPresenter(LoginCallBack callBack, LoginLogic logic) {
    this.callBack = callBack;
    this.logic = logic;
  }

  public void authenticateUser() {
    String username = callBack.getUsername();
    if (TextUtils.isEmpty(username)) {
      callBack.showUsernameError(R.string.username_error);
      return;
    }
    String password = callBack.getPassword();
    if (TextUtils.isEmpty(password)) {
      callBack.showPasswordError(R.string.password_error);
      return;
    }
    boolean loginSucceeded = logic.login(username, password);
    if (loginSucceeded) {
      callBack.startMainActivity();
      return;
    }
    callBack.showLoginError(R.string.login_failed);
  }
}
