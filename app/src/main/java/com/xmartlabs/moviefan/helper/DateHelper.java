package com.xmartlabs.moviefan.helper;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bruno on 12/13/17.
 */
public class DateHelper {
  private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

  @NonNull
  public static String getTodaysDate(){
    return DATE_FORMATTER.format(new Date());
  }

  public static int getCurrentYear(){
    return Calendar.getInstance().get(Calendar.YEAR);
  }
}
