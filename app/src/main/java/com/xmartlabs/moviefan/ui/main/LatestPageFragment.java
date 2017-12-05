package com.xmartlabs.moviefan.ui.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.models.Film;
import com.xmartlabs.moviefan.ui.recyclerview.FilmsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 12/1/17.
 */
@FragmentWithArgs
public class LatestPageFragment extends MovieFanPageBaseFragment {
  @NonNull
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);
    bindFilmsToRecyclerView(prepareFilmData());
    return view;
  }

  @Override
  protected void bindAdapterToRecyclerView() {
    adapter = new FilmsRecyclerViewAdapter();
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.movie_page_fragment;
  }

  private List<Film> prepareFilmData() {
    List<Film> filmData = new ArrayList<>();
    Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(),
        R.drawable.navbar_logo);
    Film firstFilm = Film.builder()
        .poster(bitmap)
        .title("2001: a space Odyssey")
        .duration("2:50 hs - Sci-Fi, Drama")
        .popularity(8.12f)
        .description("a description")
        .genres(new ArrayList<>())
        .build();
    filmData.add(firstFilm);

    Film secondFilm = Film.builder()
        .poster(bitmap)
        .title("The shining")
        .duration("2:50 hs - Sci-Fi, Drama")
        .popularity(8.12f)
        .description("a description")
        .genres(new ArrayList<>())
        .build();
    filmData.add(secondFilm);

    Film thirdFilm = Film.builder()
        .poster(bitmap)
        .title("The shining")
        .duration("2:50 hs - Sci-Fi, Drama")
        .popularity(8.12f)
        .description("a description")
        .genres(new ArrayList<>())
        .build();
    filmData.add(thirdFilm);

    Film fourthFilm = Film.builder()
        .poster(bitmap)
        .title("The shining")
        .duration("2:50 hs - Sci-Fi, Drama")
        .popularity(8.12f)
        .description("a description")
        .genres(new ArrayList<>())
        .build();
    filmData.add(fourthFilm);
    return filmData;
  }
}
