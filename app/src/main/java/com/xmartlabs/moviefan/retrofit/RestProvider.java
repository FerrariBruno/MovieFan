package com.xmartlabs.moviefan.retrofit;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moczul.ok2curl.CurlInterceptor;
import com.xmartlabs.moviefan.MovieFanApplication;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.services.FilmsService;
import com.xmartlabs.moviefan.services.GenresService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by bruno on 12/8/17.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestProvider {
  private final static Retrofit RETROFIT = provideRetrofit();
  private final static FilmsService FILMS_SERVICE = RETROFIT.create(FilmsService.class);
  private final static GenresService GENRES_SERVICE = RETROFIT.create(GenresService.class);
  private final static RestProvider instance = new RestProvider();

  @NonNull
  public static RestProvider getInstance(){
    return instance;
  }

  @NonNull
  public FilmsService provideFilmService(){
    return FILMS_SERVICE;
  }

  @NonNull
  public GenresService provideGenresService(){
    return GENRES_SERVICE;
  }

  @NonNull
  private static Retrofit provideRetrofit(){
    //noinspection ConstantConditions
    return new Retrofit.Builder()
        .baseUrl(HttpUrl.parse(MovieFanApplication.getContext().getString(R.string.base_url)))
        .addConverterFactory(GsonConverterFactory.create(createGsonWithSerializationPolicy()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(createClientWithInterceptors(createCurlInterceptor(), createLoggingInterceptor(),
            createApiKeyQueryInterceptor()))
        .build();
  }

  @NonNull
  private static Gson createGsonWithSerializationPolicy() {
    return new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
  }

  @NonNull
  private static OkHttpClient createClientWithInterceptors(@NonNull CurlInterceptor curlInterceptor,
                                                    @NonNull HttpLoggingInterceptor loggingInterceptor,
                                                    @NonNull ApiKeyQueryInterceptor sessionInterceptor){
    return new OkHttpClient.Builder()
        .addInterceptor(sessionInterceptor)
        .addInterceptor(curlInterceptor)
        .addInterceptor(loggingInterceptor)
        .build();
  }

  @NonNull
  private static CurlInterceptor createCurlInterceptor(){
    return new CurlInterceptor(message -> Timber.d(message));
  }

  @NonNull
  private static ApiKeyQueryInterceptor createApiKeyQueryInterceptor(){
    return new ApiKeyQueryInterceptor();
  }

  @NonNull
  private static HttpLoggingInterceptor createLoggingInterceptor(){
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return loggingInterceptor;
  }
}
