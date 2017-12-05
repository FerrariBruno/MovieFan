package com.xmartlabs.moviefan.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.BaseFragment;
import com.xmartlabs.moviefan.ui.models.Film;
import com.xmartlabs.moviefan.ui.recyclerview.FilmsRecyclerViewAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by bruno on 12/5/17.
 */
public abstract class MovieFanPageBaseFragment extends BaseFragment {
  @BindView(R.id.films)
  RecyclerView recyclerView;

  FilmsRecyclerViewAdapter adapter;

  @NonNull
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);
    initRecyclerView(view);
    return view;
  }

  private void initRecyclerView(@NonNull View view) {
    bindAdapterToRecyclerView();
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
    recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
  }

  protected abstract void bindAdapterToRecyclerView();

  protected void bindFilmsToRecyclerView(List<Film> films){
    adapter.addAllFilms(films);
    adapter.notifyDataSetChanged();
  }
}
