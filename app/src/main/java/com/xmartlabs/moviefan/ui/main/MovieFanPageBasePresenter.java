package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.CheckResult;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.xmartlabs.moviefan.controller.films.FilmController;
import com.xmartlabs.moviefan.helper.GeneralFlowableSubscriber;
import com.xmartlabs.moviefan.helper.GeneralObservableSubscriber;
import com.xmartlabs.moviefan.helper.GeneralSingleSubscriber;
import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.model.Genre;
import com.xmartlabs.moviefan.ui.common.MovieFanPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by bruno on 1/2/18.
 */
public abstract class MovieFanPageBasePresenter<T extends MovieFanPageBaseView> extends MovieFanPresenter<T> {
  @Inject
  protected FilmController filmController;

  @NonNull
  protected abstract FilmsRecyclerViewAdapter createFilmsAdapter();

  @CheckResult
  @NonNull
  protected abstract Flowable<List<Film>> requestFilms(int pageNumber, @NonNull Optional<Genre> genre, boolean adultContent);

  @Override
  public void attachView(@NonNull T movieFanPageBaseView) {
    super.attachView(movieFanPageBaseView);
    movieFanPageBaseView.setupView();
  }

  @NonNull
  OnDemandRecyclerViewScrollListener createRecyclerViewOnScrollListener(Optional<Genre> genre, boolean adultContent) {
    return new OnDemandRecyclerViewScrollListener() {
      @Override
      protected void loadNextPage(int page) {
        bindFilmsToRecyclerView(page, genre, adultContent);
      }
    };
  }

  @MainThread
  private void bindFilmsToRecyclerView(int pageNumber, @NonNull Optional<Genre> genre, boolean adultContent) {
    executeOnViewIfPresent(view ->
        requestFilms(pageNumber, genre, adultContent)
            .compose(view.keepAliveWhileVisibleFlowable())
            .subscribe(new GeneralFlowableSubscriber<List<Film>>() {
              @Override
              public void onNext(@NonNull List<Film> films){
                view.addFilms(films);
              }
            })
    );
  }
}
