package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.annimon.stream.Optional;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.model.Genre;
import com.xmartlabs.moviefan.ui.common.MovieFanFragment;
import com.xmartlabs.moviefan.ui.common.OnFilterAppliedListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by bruno on 12/22/17.
 */
public abstract class MovieFanPageBaseFragment<V extends MovieFanPageBaseView, P extends MovieFanPageBasePresenter<V>>
    extends MovieFanFragment<V, P> implements MovieFanPageBaseView, OnFilterAppliedListener {
  @BindView(R.id.films)
  RecyclerView filmsRecyclerView;

  private boolean adultContent = false;
  @NonNull
  private Optional<Genre> genre = Optional.empty();

  @Nullable
  private FilmsRecyclerViewAdapter adapter;
  @Nullable
  private OnDemandRecyclerViewScrollListener scrollListener;

  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_movie_page;
  }

  @Override
  public void addFilms(@NonNull List<Film> films) {
    //noinspection ConstantConditions
    adapter.addItems(films);
    adapter.notifyDataSetChanged();
  }

  @Override
  public void setFilms(@NonNull List<Film> films) {
    //noinspection ConstantConditions
    adapter.setItems(films);
    adapter.notifyDataSetChanged();
  }

  @Override
  public void setupView() {
    initRecyclerView();
  }

  private void initRecyclerView() {
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
    filmsRecyclerView.setLayoutManager(layoutManager);
    initRecyclerViewAdapter();
    filmsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    setupRecyclerViewOnScrollListener();
  }

  private void initRecyclerViewAdapter() {
    adapter = getPresenter().createFilmsAdapter();
    filmsRecyclerView.setAdapter(adapter);
  }

  private void setupRecyclerViewOnScrollListener() {
    scrollListener = getPresenter().createRecyclerViewOnScrollListener(genre, adultContent);
    filmsRecyclerView.addOnScrollListener(scrollListener);
  }

  @Override
  public void onFilterApplied(@NonNull Optional<Genre> genre, boolean adultContent) {
    this.genre = genre;
    this.adultContent = adultContent;
    updateRecyclerView();
  }

  @UiThread
  private void updateRecyclerView() {
    //noinspection ConstantConditions
    adapter.clearAll();
    adapter.notifyDataSetChanged();
    filmsRecyclerView.removeOnScrollListener(scrollListener);
    setupRecyclerViewOnScrollListener();
  }
}
