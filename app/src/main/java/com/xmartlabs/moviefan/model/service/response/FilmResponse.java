package com.xmartlabs.moviefan.model.service.response;

import com.google.gson.annotations.SerializedName;
import com.xmartlabs.moviefan.model.Film;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * Created by bruno on 12/21/17.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FilmResponse extends Film {
  @NonNull
  @SerializedName("genre_ids")
  private List<Long> genresIds;
}
