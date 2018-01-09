package com.xmartlabs.moviefan.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.annimon.stream.Stream;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ManyToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.xmartlabs.bigbang.core.model.EntityWithId;
import com.xmartlabs.moviefan.database.MovieFanDatabase;
import com.xmartlabs.moviefan.database.converters.GenresConverter;
import com.xmartlabs.moviefan.model.common.BaseModel;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by bruno on 12/21/17.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@ManyToMany(referencedTable = Genre.class)
@NoArgsConstructor
@Table(database = MovieFanDatabase.class, cachingEnabled = true)
public class Film extends BaseModel implements EntityWithId<Long> {
  @PrimaryKey
  private Long id;
  @Column
  private float popularity;

  @Column(typeConverter = GenresConverter.class)
  @NonNull
  private Genre[] genres;
  @Column
  @Nullable
  private String overview;
  @Column
  @Nullable
  private String posterPath;
  @Column
  @NonNull
  private String title;

  @NonNull
  public List<Genre> getGenresAsList() {
    return Stream.ofNullable(genres)
        .flatMap(Stream::of)
        .toList();
  }

  public void setGenresFromList(@NonNull List<Genre> genres) {
    this.genres = Stream.ofNullable(genres)
        .toArray(Genre[]::new);
  }
}
