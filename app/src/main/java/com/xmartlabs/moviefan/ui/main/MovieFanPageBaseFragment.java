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

import com.annimon.stream.Stream;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.BaseFragment;
import com.xmartlabs.moviefan.ui.common.GeneralSingleSubscriber;
import com.xmartlabs.moviefan.ui.common.OnFilterAppliedListener;
import com.xmartlabs.moviefan.ui.models.Film;
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

  @Nullable
  private String adultContent;
  @Nullable
  private String genreId;

  @NonNull
  private FilmsRecyclerViewAdapter adapter = createFilmsAdapter();
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

  private void initRecyclerViewOnScrollListener(){
    scrollListener = new OnDemandRecyclerViewScrollListener() {
      @Override
      protected void loadNextPage(int page) {
        bindFilmsToRecyclerView(page, genreId, adultContent);
      }
    };
    filmsRecyclerView.addOnScrollListener(scrollListener);
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_movie_page;
  }

  @MainThread
  protected void bindFilmsToRecyclerView(int pageNumber, String genreId, String adultContent) {
    requestFilms(pageNumber, genreId, adultContent)
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

  @NonNull
  protected abstract FilmsRecyclerViewAdapter createFilmsAdapter();

  @CheckResult
  @NonNull
  protected abstract Single<List<Film>> requestFilms(int pageNumber, String genreId, String adultContent);

  @Override
  public void onFilterApplied(@NonNull String genreId, @NonNull String adultContent) {
    this.genreId = genreId.equals("null") ? "" : genreId;
    this.adultContent = adultContent;
    updateRecyclerView();
  }

  private void updateRecyclerView() {
    adapter.clearData();
    adapter.notifyDataSetChanged();
    filmsRecyclerView.removeOnScrollListener(scrollListener);
    initRecyclerViewOnScrollListener();
  }
}
