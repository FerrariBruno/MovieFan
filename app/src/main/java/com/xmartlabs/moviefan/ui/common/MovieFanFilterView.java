package com.xmartlabs.moviefan.ui.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.controller.GenreController;
import com.xmartlabs.moviefan.ui.models.Film;
import com.xmartlabs.moviefan.ui.models.Genre;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import lombok.Setter;

/**
 * Created by bruno on 12/14/17.
 */
public class MovieFanFilterView extends FrameLayout {
  @BindView(R.id.genre_spinner)
  Spinner genreSpinnerView;
  @BindView(R.id.adult_content_switch)
  SwitchCompat adultContentSwitchView;

  @Setter
  private OnFilterAppliedListener filterApplyListener;

  public MovieFanFilterView(@NonNull Context context) {
    this(context, null);
  }

  public MovieFanFilterView(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public MovieFanFilterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    inflate(getContext(), R.layout.view_movie_filters, this);
    ButterKnife.bind(this);
    //populateGenresSpinner();
  }

  /*private void populateGenresSpinner(){
    GenreController.getInstance().getAllGenres()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new GeneralSingleSubscriber<Map<Long, Genre>>() {
          @Override
          public void onSuccess(@NonNull Map<Long, Genre> genres){
            List<String>
            Stream.of(genres)
                .forEach(genre -> genreSpinnerView.);
          }
        });
  }*/

  @OnClick(R.id.apply_filters)
  public void onApplyButtonClicked() {
    Optional.ofNullable(filterApplyListener)
        .ifPresent(listener -> listener.onFilterApplied(getGenreIdFromSpinner(), getAdultContentPreferenceFromSwitch()));
  }

  @NonNull
  private String getGenreIdFromSpinner() {
    //return genreSpinnerView.getSelectedItem().toString();
    return "25";
  }

  @NonNull
  private String getAdultContentPreferenceFromSwitch() {
    return String.valueOf(adultContentSwitchView.isChecked());
  }
}
