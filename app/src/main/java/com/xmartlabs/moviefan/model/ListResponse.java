package com.xmartlabs.moviefan.model;

import android.support.annotation.NonNull;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Created by bruno on 12/21/17.
 */
@Builder
@Data
public class ListResponse<T> {
  private int totalPages;
  private int totalResults;
  private int page;

  @NonNull
  private List<T> results;
}
