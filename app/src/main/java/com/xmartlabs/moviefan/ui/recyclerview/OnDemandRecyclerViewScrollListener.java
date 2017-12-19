package com.xmartlabs.moviefan.ui.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import lombok.Setter;

/**
 * Created by bruno on 12/13/17.
 */
public abstract class OnDemandRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
  private static final int FIRST_PAGE = 1;
  private static final int VISIBLE_THRESHOLD_DEFAULT = 5;

  private boolean loading = true;
  private int firstVisibleItem;
  private int page = FIRST_PAGE;
  private int previousTotal;
  private int totalItemCount;
  private int visibleItemCount;
  @Setter
  private boolean enabled = true;
  @Setter
  private int visibleThreshold = VISIBLE_THRESHOLD_DEFAULT;

  private OnDemandRecyclerViewScrollListener() {
    loadNextPage(page);
  }

  @Override
  public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);

    visibleItemCount = recyclerView.getChildCount();
    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
    totalItemCount = getTotalItemCount(layoutManager);
    firstVisibleItem = getFirstVisibleItemPosition(layoutManager);

    checkIfLoadingIsNeeded();
  }

  private void checkIfLoadingIsNeeded() {
    if (loading) {
      if (totalItemCount > previousTotal) {
        loading = false;
        page++;
        previousTotal = totalItemCount;
      }
    } else if (enabled && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
      loadNextPage(page);
      loading = true;
    }
  }

  private int getFirstVisibleItemPosition(@NonNull LinearLayoutManager layoutManager) {
    return layoutManager.findFirstVisibleItemPosition();
  }

  private int getTotalItemCount(@NonNull LinearLayoutManager layoutManager) {
    return layoutManager.getItemCount();
  }

  protected abstract void loadNextPage(int page);
}
