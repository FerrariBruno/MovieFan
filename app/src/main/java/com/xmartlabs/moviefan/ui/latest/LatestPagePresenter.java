package com.xmartlabs.moviefan.ui.latest;

import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.model.Genre;
import com.xmartlabs.moviefan.ui.main.MovieFanPageBasePresenter;

import java.util.List;

import io.reactivex.Single;
import lombok.Builder;

/**
 * Created by bruno on 1/2/18.
 */
@Builder
public class LatestPagePresenter extends MovieFanPageBasePresenter<LatestPageView> {
  @NonNull
  @Override
  protected Single<List<Film>> requestFilms(int pageNumber, @NonNull Optional<Genre> genre, boolean adultContent) {
    return filmController.getLatestFilms(pageNumber, genre, adultContent);
  }
}
