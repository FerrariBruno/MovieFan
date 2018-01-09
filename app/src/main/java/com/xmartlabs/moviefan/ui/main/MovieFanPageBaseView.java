package com.xmartlabs.moviefan.ui.main;

import com.xmartlabs.moviefan.model.Film;
import com.xmartlabs.moviefan.ui.common.MovieFanView;

import java.util.List;

/**
 * Created by bruno on 1/2/18.
 */
public interface MovieFanPageBaseView extends MovieFanView {
  void addFilmsAndNotifyAdapter(List<Film> films);
  void setupView();
}
