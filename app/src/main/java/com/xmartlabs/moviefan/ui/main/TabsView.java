package com.xmartlabs.moviefan.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;

import com.xmartlabs.moviefan.ui.common.MovieFanView;
import com.xmartlabs.moviefan.ui.views.MovieFanFilterView;

/**
 * Created by bruno on 1/2/18.
 */
public interface TabsView extends MovieFanView {
  void onFilterApplied(@NonNull MovieFanFilterView filterView, @NonNull BottomSheetDialog bottomSheetDialog);
  void setupViewPagerAdapter();
  void showFilterView();
}
