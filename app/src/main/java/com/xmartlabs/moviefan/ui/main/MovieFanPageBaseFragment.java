package com.xmartlabs.moviefan.ui.main;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.BaseFragment;
import com.xmartlabs.moviefan.ui.common.GeneralSingleSubscriber;
import com.xmartlabs.moviefan.ui.common.OnFilterAppliedListener;
import com.xmartlabs.moviefan.ui.models.Film;
import com.xmartlabs.moviefan.ui.models.Genre;
import com.xmartlabs.moviefan.ui.recyclerview.FilmsRecyclerViewAdapter;
import com.xmartlabs.moviefan.ui.recyclerview.OnDemandRecyclerViewScrollListener;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Single;

/**
 * Created by bruno on 12/5/17.
 */
public abstract class MovieFanPageBaseFragment extends BaseFragment implements OnFilterAppliedListener {
  @BindView(R.id.films)
  RecyclerView filmsRecyclerView;

  private boolean adultContent = false;
  @NonNull
  private Optional<Genre> genre = Optional.empty();

  @NonNull
  private FilmsRecyclerViewAdapter adapter = createFilmsAdapter();
  @Nullable
  private OnDemandRecyclerViewScrollListener scrollListener;

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
    initRecyclerView(view);
  }

  private void initRecyclerView(@NonNull View view) {
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
    filmsRecyclerView.setLayoutManager(layoutManager);
    filmsRecyclerView.setAdapter(adapter);
    filmsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    initRecyclerViewOnScrollListener();
  }

  private void initRecyclerViewOnScrollListener() {
    scrollListener = new OnDemandRecyclerViewScrollListener() {
      @Override
      protected void loadNextPage(int page) {
        bindFilmsToRecyclerView(page, genre, adultContent);
      }
    };
    filmsRecyclerView.addOnScrollListener(scrollListener);
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_movie_page;
  }

  @Override
  public void onFilterApplied(@NonNull Optional<Genre> genre, boolean adultContent) {
    this.genre = genre;
    this.adultContent = adultContent;
    updateRecyclerView();
  }

  @MainThread
  protected void bindFilmsToRecyclerView(int pageNumber, @NonNull Optional<Genre> genre, boolean adultContent) {
    requestFilms(pageNumber, genre, adultContent)
        .compose(prepareSingleForSubscription())
        .subscribe(new GeneralSingleSubscriber<List<Film>>() {
          @Override
          public void onSuccess(@NonNull List<Film> films){
            Stream.of(films)
                .forEach(film -> adapter.addFilm(film));
            adapter.notifyDataSetChanged();
          }
        });
  }

  private void updateRecyclerView() {
    adapter.clearData();
    adapter.notifyDataSetChanged();
    filmsRecyclerView.removeOnScrollListener(scrollListener);
    updateRecyclerViewOnScrollListener();
  }

  private void updateRecyclerViewOnScrollListener() {
    scrollListener = new OnDemandRecyclerViewScrollListener() {
      @Override
      protected void loadNextPage(int page) {
        bindFilmsToRecyclerView(page, genre, adultContent);
      }
    };
    filmsRecyclerView.addOnScrollListener(scrollListener);
  }

  @NonNull
  protected abstract FilmsRecyclerViewAdapter createFilmsAdapter();

  @CheckResult
  @NonNull
  protected abstract Single<List<Film>> requestFilms(int pageNumber, Optional<Genre> genre, boolean adultContent);
}
