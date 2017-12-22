package com.xmartlabs.moviefan;

import com.xmartlabs.bigbang.core.controller.SharedPreferencesController;
import com.xmartlabs.bigbang.core.module.CoreAndroidModule;
import com.xmartlabs.bigbang.core.module.GsonModule;
import com.xmartlabs.bigbang.core.module.OkHttpModule;
import com.xmartlabs.bigbang.core.module.PicassoModule;
import com.xmartlabs.bigbang.core.module.SessionInterceptor;
import com.xmartlabs.bigbang.core.providers.AccessTokenProvider;
import com.xmartlabs.bigbang.retrofit.module.RestServiceModule;
import com.xmartlabs.bigbang.retrofit.module.ServiceGsonModule;
import com.xmartlabs.moviefan.controller.SessionController;
import com.xmartlabs.moviefan.controller.films.FilmController;
import com.xmartlabs.moviefan.controller.films.FilmServiceController;
import com.xmartlabs.moviefan.controller.genres.GenreController;
import com.xmartlabs.moviefan.controller.genres.GenreServiceController;
import com.xmartlabs.moviefan.module.ControllerModule;
import com.xmartlabs.moviefan.module.RestServiceApiModule;
import com.xmartlabs.moviefan.module.RestServiceModuleAdditions;
import com.xmartlabs.moviefan.ui.StartActivity;
import com.xmartlabs.moviefan.ui.login.LoginActivity;
import com.xmartlabs.moviefan.ui.login.LoginFragment;
import com.xmartlabs.moviefan.ui.login.LoginPresenter;
import com.xmartlabs.moviefan.ui.onboarding.OnboardingActivity;
import com.xmartlabs.moviefan.ui.onboarding.OnboardingFragment;
import com.xmartlabs.moviefan.ui.onboarding.OnboardingPresenter;
import com.xmartlabs.moviefan.ui.onboarding.page.OnboardingPageFragment;
import com.xmartlabs.moviefan.ui.recyclerfragmentexample.RecyclerExampleActivity;
import com.xmartlabs.moviefan.ui.recyclerfragmentexample.RecyclerExampleFragment;
import com.xmartlabs.moviefan.ui.recyclerfragmentexample.RecyclerExamplePresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
    CoreAndroidModule.class,
    ControllerModule.class,
    GsonModule.class,
    ServiceGsonModule.class,
    OkHttpModule.class,
    PicassoModule.class,
    RestServiceApiModule.class,
    RestServiceModule.class,
    RestServiceModuleAdditions.class
})
public interface ApplicationComponent {
  void inject(MovieFanApplication movieFanApplication);

  void inject(LoginActivity loginActivity);
  void inject(OnboardingActivity onboardingActivity);
  void inject(RecyclerExampleActivity recyclerExampleActivity);
  void inject(StartActivity startActivity);

  void inject(LoginFragment loginFragment);
  void inject(OnboardingFragment onboardingFragment);
  void inject(OnboardingPageFragment onboardingPageFragment);
  void inject(RecyclerExampleFragment recyclerExampleFragment);

  void inject(LoginPresenter loginPresenter);
  void inject(OnboardingPresenter onboardingPresenter);
  void inject(RecyclerExamplePresenter recyclerExamplePresenter);

  void inject(FilmController filmController);
  void inject(FilmServiceController filmServiceController);
  void inject(GenreController genreController);
  void inject(GenreServiceController genreServiceController);
  void inject(SessionController sessionController);
  void inject(SharedPreferencesController sharedPreferencesController);

  void inject(AccessTokenProvider accessTokenProvider);
  void inject(SessionInterceptor sessionInterceptor);
}
