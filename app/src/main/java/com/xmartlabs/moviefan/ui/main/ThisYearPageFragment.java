package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.controller.FilmController;
import com.xmartlabs.moviefan.ui.models.Film;
import com.xmartlabs.moviefan.ui.models.Genre;
import com.xmartlabs.moviefan.ui.recyclerview.FilmsRecyclerViewAdapter;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by bruno on 12/1/17.
 */
@FragmentWithArgs
public class ThisYearPageFragment extends MovieFanPageBaseFragment {
  @NonNull
  @Override
  protected FilmsRecyclerViewAdapter createFilmsAdapter() {
    return new FilmsRecyclerViewAdapter();
  }

  @CheckResult
  @NonNull
  @Override
  protected Single<List<Film>> requestFilms(int pageNumber, @NonNull Optional<Genre> genre, boolean adultContent) {
    return FilmController.getInstance().getThisYearsFilms(pageNumber, genre, adultContent);
  }
}
