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

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.NonNull;

/**
 * Created by bruno on 12/4/17.
 */
public class FilmsRecyclerViewAdapter extends
    RecyclerView.Adapter<RecyclerView.ViewHolder>{
  private final int DETAILED_FILM_VIEWHOLDER = 0;

  private final int COLLAPSED_FILM_VIEWHOLDER = 1;

  private final int VIEWHOLDER_POSITION_LIMIT = 3;

  @NonNull
  private List<Film> films = new ArrayList<>();

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

    DetailedFilmViewHolder(@NonNull View view){
      super(view);
      ButterKnife.bind(this, view);
    }
  }

  static class CollapsedFilmViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.round_poster)
    ImageView poster;

    @BindView(R.id.collapsed_title)
    TextView title;

    CollapsedFilmViewHolder(@NonNull View view){
      super(view);
      ButterKnife.bind(this, view);
    }
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView;
    switch(viewType) {
      case DETAILED_FILM_VIEWHOLDER:
        itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_detailed_film, parent, false);
        return new DetailedFilmViewHolder(itemView);
      case COLLAPSED_FILM_VIEWHOLDER:
      itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_collapsed_film, parent, false);
      return new CollapsedFilmViewHolder(itemView);
      default:
        itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_collapsed_film, parent, false);
        return new CollapsedFilmViewHolder(itemView);
    }
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    Film film = films.get(position);
    switch(getItemViewType(position)){
      case DETAILED_FILM_VIEWHOLDER:
        DetailedFilmViewHolder detailedHolder = (DetailedFilmViewHolder) holder;
        detailedHolder.poster.setImageBitmap(film.getPoster());
        detailedHolder.title.setText(film.getTitle());
        detailedHolder.durationGenre.setText(film.getDuration());
        detailedHolder.popularity.setText(String.valueOf(film.getPopularity()));
        detailedHolder.description.setText(film.getDescription());
        break;
      case COLLAPSED_FILM_VIEWHOLDER:
        CollapsedFilmViewHolder collapsedHolder = (CollapsedFilmViewHolder) holder;
        collapsedHolder.poster.setImageBitmap(film.getPoster());
        collapsedHolder.title.setText(film.getTitle());
        break;
    }
  }

  @Override
  public int getItemViewType(int position) {
    return (position < VIEWHOLDER_POSITION_LIMIT) ? DETAILED_FILM_VIEWHOLDER : COLLAPSED_FILM_VIEWHOLDER;
  }

  @Override
  public int getItemCount() {
    return films.size();
  }

  public void addAllFilms(List<Film> films){
    this.films.addAll(films);
  }
}
