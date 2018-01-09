package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.NonNull;

import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.ui.common.MovieFanView;

import java.util.List;

/**
 * Created by bruno on 1/2/18.
 */
public interface MovieFanPageBaseView extends MovieFanView {
  void addFilmsAndNotifyAdapter(@NonNull List<Film> films);
  void setupView();
}
