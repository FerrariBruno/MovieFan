package com.xmartlabs.moviefan.ui.recyclerfragmentexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.annimon.stream.IntStream;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.MovieFanFragment;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;

@FragmentWithArgs
public class RecyclerExampleFragment extends MovieFanFragment<RecyclerExampleView, RecyclerExamplePresenter>
    implements RecyclerExampleView {
  @BindView(R.id.recyclerView)
  RecyclerView recyclerView;

  @NonNull
  @Override
  protected RecyclerExamplePresenter createPresenter() {
    return new RecyclerExamplePresenter();
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_recycler_example;
  }

  @Override
  public void setupView() {
    List<String> strings = IntStream.range(1, 40)
        .mapToObj(index -> String.format(Locale.getDefault(), "Item %d", index))
        .toList();

    recyclerView.setAdapter(new RecyclerExampleAdapter(strings));
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(layoutManager);
  }
}
