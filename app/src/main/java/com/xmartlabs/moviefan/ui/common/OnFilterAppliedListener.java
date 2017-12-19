package com.xmartlabs.moviefan.ui.common;

import android.support.annotation.NonNull;

/**
 * Created by bruno on 12/18/17.
 */
public interface OnFilterAppliedListener {
  void onFilterApplied(@NonNull String genreId, @NonNull String adultContent);
}
