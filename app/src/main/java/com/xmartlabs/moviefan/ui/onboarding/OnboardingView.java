package com.xmartlabs.moviefan.ui.onboarding;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.xmartlabs.moviefan.ui.common.MovieFanView;

public interface OnboardingView extends MovieFanView {
  OnboardingPageAdapter createPageAdapter();
  void setupView(@NonNull OnboardingPageAdapter pageAdapter);
  void setSkipButtonVisibility(boolean visible);
  void moveToPage(int page);
  void startActivity(Intent intent);
  void handleNextButtonVisibility();
  void goToLoginActivity();
}
