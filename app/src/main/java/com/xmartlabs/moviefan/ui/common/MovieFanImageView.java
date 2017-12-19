package com.xmartlabs.moviefan.ui.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.squareup.picasso.Picasso;
import com.xmartlabs.moviefan.R;

/**
 * Created by bruno on 12/13/17.
 */
public class MovieFanImageView extends AppCompatImageView {
  public MovieFanImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MovieFanImageView(Context context) {
    super(context);
  }

  public MovieFanImageView(Context context, AttributeSet attrs, int defStyle) {
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
