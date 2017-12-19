package com.xmartlabs.moviefan.ui.common;

import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.xmartlabs.moviefan.ui.models.Genre;

/**
 * Created by bruno on 12/18/17.
 */
public interface OnFilterAppliedListener {
  void onFilterApplied(@NonNull Optional<Genre> genre, boolean adultContent);
}
