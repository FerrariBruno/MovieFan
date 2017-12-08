package com.xmartlabs.moviefan.controller;

import android.support.annotation.NonNull;
import android.util.Log;

import com.xmartlabs.moviefan.retrofit.RestProvider;
import com.xmartlabs.moviefan.services.FilmsService;
import com.xmartlabs.moviefan.ui.models.Film;
import com.xmartlabs.moviefan.ui.models.FilmResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Retrofit;

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
    Retrofit retrofit = RestProvider.getInstance().provideRetrofit();
    FilmsService service = retrofit.create(FilmsService.class);
    service.getLatestFilms("release_date.desc", "2017-12-08",
        null, null, null, pageNumber)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
    .subscribe(new SingleObserver<FilmResponse>() {
      @Override
      public void onSubscribe(Disposable d) {
        Log.d("SUCCESS", "hola");
      }

      @Override
      public void onSuccess(FilmResponse response) {
        Log.d("SUCCESS", "hola");
      }

      @Override
      public void onError(Throwable e) {
        Log.d("SUCCESS", "hola");
      }
    });
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
        .popularity(8.12f)
        .overview("a description")
        .genres(new ArrayList<>())
        .build();
    filmData.add(thirdFilm);

    Film fourthFilm = Film.builder()
        .title("The shining")
        .popularity(8.12f)
        .overview("a description")
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
        .popularity(8.12f)
        .overview("a description")
        .genres(new ArrayList<>())
        .build();
    filmData.add(firstFilm);

    Film secondFilm = Film.builder()
        .title("The shining")
        .popularity(8.12f)
        .overview("a description")
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
        .popularity(8.12f)
        .overview("a description")
        .genres(new ArrayList<>())
        .build();
    filmData.add(firstFilm);

    return filmData;
  }
}
