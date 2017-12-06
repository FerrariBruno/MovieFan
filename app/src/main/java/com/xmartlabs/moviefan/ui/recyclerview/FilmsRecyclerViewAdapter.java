package com.xmartlabs.moviefan.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.models.Film;

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
    COLLAPSED_FILM_VIEWHOLDER,
    ;
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

  public void addAllFilms(@NonNull List<Film> films) {
    this.films.addAll(films);
  }

  static class DetailedFilmViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.duration_genre)
    TextView durationGenre;
    @BindView(R.id.popularity)
    TextView popularity;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.poster)
    ImageView poster;

    DetailedFilmViewHolder(@NonNull View view) {
      super(view);
      ButterKnife.bind(this, view);
    }

    void bind(@NonNull Film film) {
      poster.setImageBitmap(film.getPoster());
      title.setText(film.getTitle());
      durationGenre.setText(film.getDuration());
      popularity.setText(String.valueOf(film.getPopularity()));
      description.setText(film.getDescription());
    }
  }

  static class CollapsedFilmViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.round_poster)
    ImageView poster;
    @BindView(R.id.collapsed_title)
    TextView title;

    CollapsedFilmViewHolder(@NonNull View view) {
      super(view);
      ButterKnife.bind(this, view);
    }

    void bind(@NonNull Film film) {
      poster.setImageBitmap(film.getPoster());
      title.setText(film.getTitle());
    }
  }
}
