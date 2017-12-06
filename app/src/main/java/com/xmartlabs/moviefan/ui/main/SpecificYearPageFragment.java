package com.xmartlabs.moviefan.ui.main;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.controller.FilmController;
import com.xmartlabs.moviefan.ui.models.Film;
import com.xmartlabs.moviefan.ui.recyclerview.FilmsRecyclerViewAdapter;

import java.util.List;

/**
 * Created by bruno on 12/1/17.
 */
@FragmentWithArgs
public class SpecificYearPageFragment extends MovieFanPageBaseFragment {
  @Override
  protected FilmsRecyclerViewAdapter createFilmsAdapter() {
    return new FilmsRecyclerViewAdapter();
  }

  @Override
  protected List<Film> requestFilms() {
    return FilmController.getInstance().getSpecificYearFilms(1);
  }
}
