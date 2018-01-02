package com.xmartlabs.moviefan.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.xmartlabs.moviefan.ui.common.MovieFanView;

/**
 * Created by bruno on 1/2/18.
 */
public interface TabsView extends MovieFanView {
  //FIXME not sure if this methods go here
  boolean onOptionsItemSelected(@NonNull MenuItem item);
  void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater);
  void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState);
}
