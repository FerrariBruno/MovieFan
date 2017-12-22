package com.xmartlabs.moviefan.helper;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.Locale;

/**
 * Created by bruno on 12/21/17.
 */
public class DateHelper extends com.xmartlabs.bigbang.core.helper.DateHelper {
  public static final DateTimeFormatter SHORT_DATE_FORMATTER_DASH_SEPARATED = DateTimeFormatter.ofPattern("yyyy-MM-dd",
      Locale.getDefault());
}
