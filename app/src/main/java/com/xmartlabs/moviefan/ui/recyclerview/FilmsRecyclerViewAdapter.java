package com.xmartlabs.moviefan.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.xmartlabs.moviefan.MovieFanApplication;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.MovieFanCircleImageView;
import com.xmartlabs.moviefan.ui.common.MovieFanImageView;
import com.xmartlabs.moviefan.ui.models.Film;
import com.xmartlabs.moviefan.ui.models.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.NonNull;

/**
 * Created by bruno on 12/4/17.
 */
public class FilmsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private final int DETAILED_FILM_VIEWHOLDER_LIMIT = 3;

  @NonNull
  private List<Film> films = new ArrayList<>();

  private enum FilmViewHolder {
    DETAILED_FILM_VIEWHOLDER,
    COLLAPSED_FILM_VIEWHOLDER
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView;
    switch (getFilmViewHolderFromViewType(viewType)) {
      case DETAILED_FILM_VIEWHOLDER:
        itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_detailed_film, parent, false);
        return new DetailedFilmViewHolder(itemView);
      case COLLAPSED_FILM_VIEWHOLDER:
        itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_collapsed_film, parent, false);
        return new CollapsedFilmViewHolder(itemView);
      default:
        throw new ExceptionInInitializerError(String.format(Locale.US,"viewType: %d was not found", viewType));
    }
  }

  private FilmViewHolder getFilmViewHolderFromViewType(int viewType){
    return FilmViewHolder.values()[viewType];
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    Film film = films.get(position);
    switch (getFilmViewHolderFromViewType(getItemViewType(position))) {
      case DETAILED_FILM_VIEWHOLDER:
        DetailedFilmViewHolder detailedHolder = (DetailedFilmViewHolder) holder;
        detailedHolder.bind(film);
        break;
      case COLLAPSED_FILM_VIEWHOLDER:
        CollapsedFilmViewHolder collapsedHolder = (CollapsedFilmViewHolder) holder;
        collapsedHolder.bind(film);
        break;
      default:
        throw new ExceptionInInitializerError(String.format(Locale.US,"viewType: %d was not found",
            getItemViewType(position)));
    }
  }

  @Override
  public int getItemViewType(int position) {
    return (position < DETAILED_FILM_VIEWHOLDER_LIMIT) ?
        FilmViewHolder.DETAILED_FILM_VIEWHOLDER.ordinal() :
        FilmViewHolder.COLLAPSED_FILM_VIEWHOLDER.ordinal();
  }

  @Override
  public int getItemCount() {
    return films.size();
  }

  public void addFilm(@NonNull Film film) {
    this.films.add(film);
  }

  public void clearData() {
    this.films.clear();
  }

  static class DetailedFilmViewHolder extends RecyclerView.ViewHolder {
    private final String DELIMITER = ", ";

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
      ButterKnife.bind(this, view);
    }

    void bind(@NonNull Film film) {
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

  static class CollapsedFilmViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.round_poster)
    MovieFanCircleImageView posterImageView;
    @BindView(R.id.collapsed_title)
    TextView titleTextView;

    CollapsedFilmViewHolder(@NonNull View view) {
      super(view);
      ButterKnife.bind(this, view);
    }

    void bind(@NonNull Film film) {
      posterImageView.loadImageWithPicassoIncludingPlaceholder(film.getPosterPath());
      titleTextView.setText(film.getTitle());
    }
  }
}
