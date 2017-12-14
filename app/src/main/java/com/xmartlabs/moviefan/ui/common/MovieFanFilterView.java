package com.xmartlabs.moviefan.ui.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.xmartlabs.moviefan.R;

/**
 * Created by bruno on 12/14/17.
 */
public class MovieFanFilterView extends FrameLayout {
  public MovieFanFilterView(@NonNull Context context) {
    super(context);
    init();
  }

  public MovieFanFilterView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public MovieFanFilterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init(){
    inflate(getContext(), R.layout.movie_filters, this);
  }
}
