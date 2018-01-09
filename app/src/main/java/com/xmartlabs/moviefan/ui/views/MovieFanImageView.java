package com.xmartlabs.moviefan.ui.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.squareup.picasso.Picasso;
import com.xmartlabs.moviefan.R;

/**
 * Created by bruno on 1/5/18.
 */
public class MovieFanImageView extends AppCompatImageView {
  public MovieFanImageView(@NonNull Context context) {
    super(context);
  }

  public MovieFanImageView(@NonNull Context context, @NonNull AttributeSet attrs) {
    super(context, attrs);
  }

  public MovieFanImageView(@NonNull Context context, @NonNull AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public void loadImageWithPicassoIncludingPlaceholder(@Nullable String url){
    Picasso.with(getContext())
        .load(url)
        .error(R.drawable.ic_error_loading_film_image)
        .placeholder(R.drawable.progress_animation)
        .into(this);
  }
}
