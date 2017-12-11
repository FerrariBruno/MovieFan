package com.xmartlabs.moviefan.retrofit;

import android.support.annotation.NonNull;
import android.util.Log;

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

/**
 * Created by bruno on 12/8/17.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestProvider {
  private static RestProvider instance = new RestProvider();

  @NonNull
  public static RestProvider getInstance(){
    return instance;
  }

  @NonNull
  public FilmsService provideFilmService(){
    Retrofit retrofit = provideRetrofit();
    return retrofit.create(FilmsService.class);
  }

  @NonNull
  public GenresService provideGenresService(){
    Retrofit retrofit = provideRetrofit();
    return retrofit.create(GenresService.class);
  }

  @NonNull
  private Retrofit provideRetrofit(){
    //noinspection ConstantConditions
    return new Retrofit.Builder()
        .baseUrl(HttpUrl.parse(MovieFanApplication.getContext().getResources().getString(R.string.base_url)))
        .addConverterFactory(GsonConverterFactory.create(createGsonWithSerializationPolicy()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(createClientWithInterceptors(createCurlInterceptor(), createLoggingInterceptor(),
            createQueryInterceptor()))
        .build();
  }

  @NonNull
  private Gson createGsonWithSerializationPolicy() {
    return new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
  }

  @NonNull
  private OkHttpClient createClientWithInterceptors(@NonNull CurlInterceptor curlInterceptor,
                                                    @NonNull HttpLoggingInterceptor loggingInterceptor,
                                                    @NonNull QueryInterceptor sessionInterceptor){
    return new OkHttpClient.Builder()
        .addInterceptor(sessionInterceptor)
        .addInterceptor(curlInterceptor)
        .addInterceptor(loggingInterceptor)
        .build();
  }

  @NonNull
  private CurlInterceptor createCurlInterceptor(){
    return new CurlInterceptor(message -> Log.d("Ok2Curl", message));
  }

  @NonNull
  private QueryInterceptor createQueryInterceptor(){
    return new QueryInterceptor();
  }

  @NonNull
  private HttpLoggingInterceptor createLoggingInterceptor(){
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return loggingInterceptor;
  }
}
