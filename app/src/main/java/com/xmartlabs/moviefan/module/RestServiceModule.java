package com.xmartlabs.moviefan.module;

import android.content.Context;

import com.annimon.stream.Objects;
import com.xmartlabs.bigbang.retrofit.common.ServiceStringConverter;
import com.xmartlabs.moviefan.MovieFanApplication;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.helper.DateHelper;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Year;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class RestServiceModule extends com.xmartlabs.bigbang.retrofit.module.RestServiceModule {
  private static final String BASE_URL = MovieFanApplication.getContext().getResources().getString(R.string.base_url);

  @Override
  public HttpUrl provideBaseUrl(Context context) {
    return HttpUrl.parse(BASE_URL);
  }

  @Override
   public ServiceStringConverter provideStringConverter() {
    return new MovieFanServiceStringConverter();
  }


  private static class MovieFanServiceStringConverter extends ServiceStringConverter {
    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
      if (type instanceof Class) {
        if (Objects.equals(type, LocalDate.class)) {
          return value -> String.valueOf(((LocalDate) value).format(DateHelper.SHORT_DATE_FORMATTER_DASH_SEPARATED));
        } else if (Objects.equals(type, Year.class)) {
          return value -> String.valueOf(((Year) value).getValue());
        }
      }
      return super.stringConverter(type, annotations, retrofit);
    }
  }
}
