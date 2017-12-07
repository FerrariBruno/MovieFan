package com.xmartlabs.moviefan.controller;

import android.support.annotation.NonNull;

import com.xmartlabs.moviefan.ui.models.Film;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by bruno on 12/6/17.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilmController {
  private static final FilmController FILM_CONTROLLER = new FilmController();

  @NonNull
  public static FilmController getInstance() {
    return FILM_CONTROLLER;
  }

  @NonNull
  public List<Film> getLatestFilms(int pageNumber) {
    //TODO replace with service call when service is done
    return getHardcodedLatestFilms();
  }

  @NonNull
  public List<Film> getThisYearsFilms(int pageNumber) {
    //TODO replace with service call when service is done
    return getHardcodedThisYearsFilms();
  }

  @NonNull
  public List<Film> getSpecificYearFilms(int pageNumber) {
    //TODO replace with service call when service is done
    return getHardcodedSpecificYearFilms();
  }

  @NonNull
  private List<Film> getHardcodedLatestFilms() {
    List<Film> filmData = new ArrayList<>();

    Film thirdFilm = Film.builder()
        .title("The shining")
        .duration("2:50 hs - Sci-Fi, Drama")
        .popularity(8.12f)
        .description("a description")
        .genres(new ArrayList<>())
        .build();
    filmData.add(thirdFilm);

    Film fourthFilm = Film.builder()
        .title("The shining")
        .duration("2:50 hs - Sci-Fi, Drama")
        .popularity(8.12f)
        .description("a description")
        .genres(new ArrayList<>())
        .build();
    filmData.add(fourthFilm);
    return filmData;
  }

  @NonNull
  private List<Film> getHardcodedSpecificYearFilms() {
    List<Film> filmData = new ArrayList<>();
    Film firstFilm = Film.builder()
        .title("2001: a space Odyssey")
        .duration("2:50 hs - Sci-Fi, Drama")
        .popularity(8.12f)
        .description("a description")
        .genres(new ArrayList<>())
        .build();
    filmData.add(firstFilm);

    Film secondFilm = Film.builder()
        .title("The shining")
        .duration("2:50 hs - Sci-Fi, Drama")
        .popularity(8.12f)
        .description("a description")
        .genres(new ArrayList<>())
        .build();
    filmData.add(secondFilm);
    return filmData;
  }

  @NonNull
  private List<Film> getHardcodedThisYearsFilms() {
    List<Film> filmData = new ArrayList<>();
    Film firstFilm = Film.builder()
        .title("2001: a space Odyssey")
        .duration("2:50 hs - Sci-Fi, Drama")
        .popularity(8.12f)
        .description("a description")
        .genres(new ArrayList<>())
        .build();
    filmData.add(firstFilm);

    return filmData;
  }
}
