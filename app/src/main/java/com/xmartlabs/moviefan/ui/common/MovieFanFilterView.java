package com.xmartlabs.moviefan.ui.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.xmartlabs.moviefan.MovieFanApplication;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.models.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.Setter;

/**
 * Created by bruno on 12/14/17.
 */
public class MovieFanFilterView extends FrameLayout {
  private static final long ANY_GENRE_IDENTIFIER = -1;
  @NonNull
  private static final Genre ANY_GENRE = new Genre(ANY_GENRE_IDENTIFIER,
           MovieFanApplication.getContext().getString(R.string.genre_spinner_hint)); //Empty Genre for Spinner hint
  @BindView(R.id.genre_spinner)
  Spinner genreSpinnerView;
  @BindView(R.id.adult_content_switch)
  SwitchCompat adultContentSwitchView;

  @Setter
  private OnFilterAppliedListener onFilterAppliedListener;

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
  }

  public void initGenresSpinner(@NonNull Map<Long, Genre> genres) {
    List<Genre> genresFromService = loadGenresFromService(genres);
    ArrayAdapter<Genre> adapter = new ArrayAdapter<>(
            getContext(), android.R.layout.simple_spinner_item, genresFromService);
    adapter.setDropDownViewResource(R.layout.custom_spinner_item);
    genreSpinnerView.setAdapter(adapter);
  }

  @NonNull
  private List<Genre> loadGenresFromService(@NonNull Map<Long, Genre> genres) {
    List<Genre> genresFromService = new ArrayList<>();
    genresFromService.add(ANY_GENRE);
    Stream.of(genres)
        .map(Map.Entry::getValue)
        .forEach(genresFromService::add);
    return genresFromService;
  }

  @OnClick(R.id.apply_filters)
  public void onApplyButtonClicked() {
    Optional.ofNullable(onFilterAppliedListener)
        .ifPresent(listener -> listener.onFilterApplied(getGenreFromSpinner(), getAdultContentPreferenceFromSwitch()));
  }

  @NonNull
  private Optional<Genre> getGenreFromSpinner() {
    return ANY_GENRE.equals(genreSpinnerView.getSelectedItem()) ?
        Optional.empty() :
        Optional.of((Genre) genreSpinnerView.getSelectedItem());
  }

  private boolean getAdultContentPreferenceFromSwitch() {
    return adultContentSwitchView.isChecked();
  }
}
