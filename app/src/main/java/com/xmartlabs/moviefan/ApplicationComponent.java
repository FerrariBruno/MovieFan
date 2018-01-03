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
import com.xmartlabs.moviefan.ui.latest.LatestPageFragment;
import com.xmartlabs.moviefan.ui.latest.LatestPagePresenter;
import com.xmartlabs.moviefan.ui.main.HomeActivity;
import com.xmartlabs.moviefan.ui.main.TabsFragment;
import com.xmartlabs.moviefan.ui.main.TabsPresenter;
import com.xmartlabs.moviefan.ui.specificYear.SpecificYearPageFragment;
import com.xmartlabs.moviefan.ui.specificYear.SpecificYearPagePresenter;
import com.xmartlabs.moviefan.ui.thisYear.ThisYearPageFragment;
import com.xmartlabs.moviefan.ui.thisYear.ThisYearPagePresenter;

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

  void inject(StartActivity startActivity);
  void inject(HomeActivity homeActivity);

  void inject(LatestPageFragment latestPageFragment);
  void inject(SpecificYearPageFragment specificYearPageFragment);
  void inject(TabsFragment tabsFragment);
  void inject(ThisYearPageFragment thisYearPageFragment);

  void inject(LatestPagePresenter latestPagePresenter);
  void inject(SpecificYearPagePresenter specificYearPagePresenter);
  void inject(TabsPresenter tabsPresenter);
  void inject(ThisYearPagePresenter thisYearPagePresenter);

  void inject(FilmController filmController);
  void inject(FilmServiceController filmServiceController);
  void inject(GenreController genreController);
  void inject(GenreServiceController genreServiceController);
  void inject(SessionController sessionController);
  void inject(SharedPreferencesController sharedPreferencesController);

  void inject(AccessTokenProvider accessTokenProvider);
  void inject(SessionInterceptor sessionInterceptor);
}
