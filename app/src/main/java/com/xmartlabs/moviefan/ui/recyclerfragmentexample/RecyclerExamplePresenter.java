package com.xmartlabs.moviefan.ui.recyclerfragmentexample;

import com.xmartlabs.bigbang.ui.mvp.BaseMvpPresenter;

public class RecyclerExamplePresenter extends BaseMvpPresenter<RecyclerExampleView> {
  @Override
  public void attachView(RecyclerExampleView view) {
    super.attachView(view);
    view.setupView();
  }
}
