package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.controller.FilmController;
import com.xmartlabs.moviefan.ui.models.Film;
import com.xmartlabs.moviefan.ui.recyclerview.FilmsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

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

  @NonNull
  @Override
  protected List<Film> requestFilms(int pageNumber) {
    List<Film> requestedFilms = new ArrayList<>();
    FilmController.getInstance().getThisYearsFilms(pageNumber)
        .subscribe(films -> {
          Stream.of(films)
              .forEach(requestedFilms::add);
        });
    return requestedFilms;
  }
}
