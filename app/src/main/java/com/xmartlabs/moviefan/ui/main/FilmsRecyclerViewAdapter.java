package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.xmartlabs.bigbang.ui.common.recyclerview.BaseRecyclerViewAdapter;
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
  void setItems(@NonNull List<Film> films) {
    Stream.of(films)
        .limit(DETAILED_FILM_VIEWHOLDER_LIMIT)
        .forEach(film -> addItemWithoutNotifying(detailedItemType, film, true));

    Stream.of(films)
        .skip(DETAILED_FILM_VIEWHOLDER_LIMIT)
        .forEach(film -> addItemWithoutNotifying(collapsedItemType, film, true));

    notifyDataSetChanged();
  }

  static final class DetailedFilmViewHolder extends SingleItemBaseViewHolder<Film> {
    @NonNull
    private static final String DELIMITER = ", ";

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
      genresTextView.setText(joinGenresWithACommaDelimiter(film.getGenres()));
      titleTextView.setText(film.getTitle());
      popularityTextView.setText(String.format(Locale.US, "%s %.2f",
          MovieFanApplication.getContext().getString(R.string.popularity),
          film.getPopularity()));
      descriptionTextView.setText(film.getOverview());
    }

    @NonNull
    private String joinGenresWithACommaDelimiter(@NonNull List<Genre> genres){
      return Stream.of(genres)
          .map(Genre::getName)
          .collect(Collectors.joining(DELIMITER));
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
