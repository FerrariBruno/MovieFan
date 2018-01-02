package com.xmartlabs.moviefan.ui.views;

import android.content.Context;
import android.util.AttributeSet;

import com.squareup.picasso.Picasso;
import com.xmartlabs.moviefan.R;

import de.hdodenhof.circleimageview.CircleImageView;

import lombok.NonNull;

/**
 * Created by bruno on 12/22/17.
 */
public class MovieFanCircleImageView extends CircleImageView {
  public MovieFanCircleImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MovieFanCircleImageView(Context context) {
    super(context);
  }

  public MovieFanCircleImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public void loadImageWithPicassoIncludingPlaceholder(@NonNull String url){
    Picasso.with(getContext())
        .load(url)
        .error(R.drawable.ic_error_loading_film_image)
        .placeholder(R.drawable.progress_animation)
        .into(this);
  }
}
