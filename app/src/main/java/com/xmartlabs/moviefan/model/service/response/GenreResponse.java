package com.xmartlabs.moviefan.model.service.response;

import android.support.annotation.NonNull;

import com.xmartlabs.moviefan.model.Genre;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Created by bruno on 12/21/17.
 */
@Builder
@Data
public class GenreResponse {
  @NonNull
  List<Genre> genres;
}
