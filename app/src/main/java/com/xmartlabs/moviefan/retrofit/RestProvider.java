package com.xmartlabs.moviefan.retrofit;

import android.support.annotation.NonNull;

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
  private final String BASE_URL = "https://api.themoviedb.org/3/";

  @NonNull
  public static RestProvider getInstance(){
    return instance;
  }

  @NonNull
  public Retrofit provideRetrofit(){
    //noinspection ConstantConditions
    return new Retrofit.Builder()
        .baseUrl(HttpUrl.parse(BASE_URL))
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(createClientWithInterceptors(createLoggingInterceptor(), createQueryInterceptor()))
        .build();
  }

  @NonNull
  private OkHttpClient createClientWithInterceptors(@NonNull HttpLoggingInterceptor loggingInterceptor,
                                                    @NonNull QueryInterceptor sessionInterceptor){
    return new OkHttpClient.Builder()
        .addInterceptor(sessionInterceptor)
        .addInterceptor(loggingInterceptor)
        .build();
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
