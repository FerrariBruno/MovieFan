package com.xmartlabs.moviefan.ui.common;

import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.annimon.stream.function.Consumer;
import com.xmartlabs.bigbang.ui.mvp.BaseMvpPresenter;

/**
 * Created by bruno on 1/2/18.
 */
public abstract class MovieFanPresenter<T extends MovieFanView> extends BaseMvpPresenter<T> {
  @Override
  public void attachView(@NonNull T view) {
    super.attachView(view);
  }

  @NonNull
  private Optional<T> getOptionalView() {
    return Optional.ofNullable(getView());
  }

  protected void executeOnViewIfPresent(@NonNull Consumer<T> action) {
    getOptionalView()
        .ifPresent(action);
  }
}
