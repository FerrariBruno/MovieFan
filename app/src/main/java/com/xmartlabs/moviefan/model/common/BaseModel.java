package com.xmartlabs.moviefan.model.common;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.xmartlabs.moviefan.database.converters.LocalDateTimeConverter;

import org.threeten.bp.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by bruno on 1/9/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseModel extends com.raizlabs.android.dbflow.structure.BaseModel {
  public static final String CREATED_DATE_SERIALIZED_NAME = "CreatedDate";
  public static final String MODIFIED_DATE_SERIALIZED_NAME = "ModifiedDate";
  public static final String SYNC_STATUS_SERIALIZED_NAME = "Status";

  @Column(typeConverter = LocalDateTimeConverter.class)
  @NotNull
  @SerializedName(CREATED_DATE_SERIALIZED_NAME)
  public LocalDateTime createDate = LocalDateTime.now();
  @Column(typeConverter = LocalDateTimeConverter.class)
  @NotNull
  @SerializedName(MODIFIED_DATE_SERIALIZED_NAME)
  public LocalDateTime modifiedDate = LocalDateTime.now();
  @Column
  @NotNull
  @SerializedName(SYNC_STATUS_SERIALIZED_NAME)
  public SyncStatus status = SyncStatus.CREATED;

  @WorkerThread
  public boolean setStatusAndSave(@NonNull SyncStatus status) {
    this.status = status;
    return save();
  }
}
