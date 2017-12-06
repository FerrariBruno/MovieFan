package com.xmartlabs.moviefan.controller;

import android.support.annotation.NonNull;

import com.xmartlabs.moviefan.ui.models.Film;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 12/6/17.
 */
public class FilmController {
  private static FilmController filmController = new FilmController();

  private FilmController() {}

  @NonNull
  public static FilmController getFilmControllerInstance() {
    return filmController;
  }

  @NonNull
  public List<Film> getLatestFilms() {
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
  public List<Film> getThisYearsFilms() {
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

  @NonNull
  public List<Film> getSpecificYearFilms() {
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
}
