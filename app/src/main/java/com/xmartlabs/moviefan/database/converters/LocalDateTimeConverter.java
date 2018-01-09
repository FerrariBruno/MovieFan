package com.xmartlabs.moviefan.database.converters;

import com.annimon.stream.Optional;
import com.raizlabs.android.dbflow.converter.TypeConverter;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;

import javax.annotation.Nullable;

/**
 * Created by bruno on 1/9/18.
 */
@com.raizlabs.android.dbflow.annotation.TypeConverter
public class LocalDateTimeConverter extends TypeConverter<Long, LocalDateTime> {
  @Override
  public Long getDBValue(LocalDateTime localDate) {
    return localDate.toEpochSecond(ZoneOffset.UTC);
  }

  @Override
  public LocalDateTime getModelValue(@Nullable Long epoch) {
    return Optional.ofNullable(epoch)
        .map(Instant::ofEpochSecond)
        .map(instant -> instant.atZone(ZoneOffset.UTC).toLocalDateTime())
        .orElse(null);
  }
}
