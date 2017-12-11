package com.xmartlabs.moviefan.ui.main;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.annimon.stream.Stream;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.BaseFragment;
import com.xmartlabs.moviefan.ui.common.GeneralSingleSubscriber;
import com.xmartlabs.moviefan.ui.models.Film;
import com.xmartlabs.moviefan.ui.recyclerview.FilmsRecyclerViewAdapter;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Single;

/**
 * Created by bruno on 12/5/17.
 */
public abstract class MovieFanPageBaseFragment extends BaseFragment {
  @BindView(R.id.films)
  RecyclerView filmsRecyclerView;

  private final int FIRST_PAGE = 1;
  @NonNull
  private FilmsRecyclerViewAdapter adapter = createFilmsAdapter();

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
    initRecyclerView(view);
    bindFilmsToRecyclerView();
  }

  private void initRecyclerView(@NonNull View view) {
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
    filmsRecyclerView.setLayoutManager(layoutManager);
    filmsRecyclerView.setAdapter(adapter);
    filmsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.movie_page_fragment;
  }

  @MainThread
  protected void bindFilmsToRecyclerView() {
    requestFilms(FIRST_PAGE)
        .subscribe(new GeneralSingleSubscriber<List<Film>>() {
          @Override
          public void onSuccess(@NonNull List<Film> films){
            Stream.of(films)
                .forEach(film -> adapter.addFilm(film));
          }
        });
    adapter.notifyDataSetChanged();
  }

  @NonNull
  protected abstract FilmsRecyclerViewAdapter createFilmsAdapter();

  @NonNull
  protected abstract Single<List<Film>> requestFilms(int pageNumber);
}
