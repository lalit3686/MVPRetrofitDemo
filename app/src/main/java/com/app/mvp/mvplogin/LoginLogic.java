package com.app.mvp.mvplogin;

public class LoginLogic {
  public boolean login(String username, String password) {
    return "james".equals(username) && "bond".equals(password);
  }
}
