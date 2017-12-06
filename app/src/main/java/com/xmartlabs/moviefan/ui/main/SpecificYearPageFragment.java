package com.xmartlabs.moviefan.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.R;
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
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
    List<Film> specificYearFilms = FilmController.getFilmControllerInstance().getSpecificYearFilms();
    bindFilmsToRecyclerView(specificYearFilms);
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.movie_page_fragment;
  }

  @Override
  protected FilmsRecyclerViewAdapter createFilmsAdapter() {
    return new FilmsRecyclerViewAdapter();
  }
}
