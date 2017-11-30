package com.xmartlabs.moviefan.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.xmartlabs.moviefan.R;
import com.xmartlabs.moviefan.ui.common.BaseFragment;

/**
 * Created by bruno on 11/30/17.
 */
public class PageFragment extends BaseFragment {
  public static final String ARG_PAGE = "ARG_PAGE";

  private int mPage;

  public static PageFragment newInstance(int page) {
    Bundle args = new Bundle();
    args.putInt(ARG_PAGE, page);
    PageFragment fragment = new PageFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPage = getArguments().getInt(ARG_PAGE);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View v = super.onCreateView(inflater, container, savedInstanceState);
    TextView textView = (TextView) v;
    textView.setText("Fragment #" + mPage);
    return v;
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_page;
  }
}
