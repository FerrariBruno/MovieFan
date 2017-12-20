package com.xmartlabs.moviefan.ui.login;

import com.xmartlabs.moviefan.ui.common.MovieFanView;

public interface LoginView extends MovieFanView {
  void setIsLoading(boolean loading);
  void gotoRecyclerExampleActivity();
}
