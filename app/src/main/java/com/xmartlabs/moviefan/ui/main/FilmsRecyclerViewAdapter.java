package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Objects;
import com.annimon.stream.Stream;
import com.xmartlabs.bigbang.ui.common.recyclerview.BaseRecyclerViewAdapter;
import com.xmartlabs.bigbang.ui.common.recyclerview.RecycleItemType;
import com.xmartlabs.bigbang.ui.common.recyclerview.SimpleItemRecycleItemType;
import com.xmartlabs.bigbang.ui.common.recyclerview.SingleItemBaseViewHolder;
import com.xmartlabs.moviefan.MovieFanApplication;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.model.Genre;
import com.xmartlabs.moviefan.ui.views.MovieFanCircleImageView;
import com.xmartlabs.moviefan.ui.views.MovieFanImageView;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * Created by bruno on 1/5/18.
 */
public class FilmsRecyclerViewAdapter extends BaseRecyclerViewAdapter {
  private static final int DETAILED_FILM_VIEWHOLDER_LIMIT = 3;

  @NonNull
  private final SimpleItemRecycleItemType<Film, CollapsedFilmViewHolder> collapsedItemType =
      new SimpleItemRecycleItemType<Film, CollapsedFilmViewHolder>() {
        @NonNull
        @Override
        public CollapsedFilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
          return new CollapsedFilmViewHolder(inflateView(parent, R.layout.item_collapsed_film));
        }
      };
  @NonNull
  private final SimpleItemRecycleItemType<Film, DetailedFilmViewHolder> detailedItemType =
      new SimpleItemRecycleItemType<Film, DetailedFilmViewHolder>() {
        @NonNull
        @Override
        public DetailedFilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
          return new DetailedFilmViewHolder(inflateView(parent, R.layout.item_detailed_film));
        }
      };

  @MainThread
  void addItems(@NonNull List<Film> films) {
    int detailedViewholdersToBeAdded = Math.max(0, DETAILED_FILM_VIEWHOLDER_LIMIT - getItems().size());

    Stream.of(films)
        .limit(detailedViewholdersToBeAdded)
        .forEach(film -> addItemWithoutNotifying(detailedItemType, film, true));

    Stream.of(films)
        .skip(detailedViewholdersToBeAdded)
        .forEach(film -> addItemWithoutNotifying(collapsedItemType, film, true));
  }

  @MainThread
  void setItems(@NonNull List<Film> films) {
    List<Pair<? extends RecycleItemType, Film>> items = Stream
        .concat(
            Stream.of(films)
                .limit(DETAILED_FILM_VIEWHOLDER_LIMIT)
                .map(film -> new Pair<>(detailedItemType, film)),
            Stream.of(films)
                .skip(DETAILED_FILM_VIEWHOLDER_LIMIT)
                .map(film -> new Pair<>(collapsedItemType, film)))
        .collect(Collectors.toList());

    setMultipleTypeItems(items, this::areFilmsTheSame, this::areFilmContentsTheSame);
  }

  private boolean areFilmsTheSame(@NonNull Film firstFilm, @NonNull Film secondFilm) {
    return Objects.equals(firstFilm.getId(), secondFilm.getId());
  }

  private boolean areFilmContentsTheSame(@NonNull Film firstFilm, @NonNull Film secondFilm) {
    return Objects.equals(firstFilm.getTitle(), secondFilm.getTitle())
        && Objects.equals(firstFilm.getPosterPath(), secondFilm.getPosterPath())
        && Objects.equals(firstFilm.getOverview(), secondFilm.getOverview());
  }

  static final class DetailedFilmViewHolder extends SingleItemBaseViewHolder<Film> {
    @BindView(R.id.poster)
    MovieFanImageView posterImageView;
    @BindView(R.id.title)
    TextView titleTextView;
    @BindView(R.id.genres)
    TextView genresTextView;
    @BindView(R.id.popularity)
    TextView popularityTextView;
    @BindView(R.id.description)
    TextView descriptionTextView;

    DetailedFilmViewHolder(@NonNull View view) {
      super(view);
    }

    @Override
    public void bindItem(@NonNull Film film) {
      super.bindItem(film);
      posterImageView.loadImageWithPicassoIncludingPlaceholder(film.getPosterPath());
      //noinspection ConstantConditions
      genresTextView.setText(joinGenresWithACommaDelimiter(film.getGenres()));
      titleTextView.setText(film.getTitle());
      popularityTextView.setText(String.format(Locale.US, "%s %.2f",
          MovieFanApplication.getContext().getString(R.string.popularity),
          film.getPopularity()));
      descriptionTextView.setText(film.getOverview());
    }

    @NonNull
    private String joinGenresWithACommaDelimiter(@Nullable List<Genre> genres){
      return Stream.ofNullable(genres)
          .map(Genre::getName)
          .collect(Collectors.joining(", "));
    }
  }

  static final class CollapsedFilmViewHolder extends SingleItemBaseViewHolder<Film> {
    @BindView(R.id.round_poster)
    MovieFanCircleImageView posterImageView;
    @BindView(R.id.collapsed_title)
    TextView titleTextView;

    CollapsedFilmViewHolder(@NonNull View view) {
      super(view);
    }

    @Override
    public void bindItem(@NonNull Film film) {
      super.bindItem(film);
      posterImageView.loadImageWithPicassoIncludingPlaceholder(film.getPosterPath());
      titleTextView.setText(film.getTitle());
    }
  }
}
