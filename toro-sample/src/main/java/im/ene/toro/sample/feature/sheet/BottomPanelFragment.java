/*
 * Copyright 2017 eneim@Eneim Labs, nam@ene.im
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.ene.toro.sample.feature.sheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import im.ene.toro.Toro;
import im.ene.toro.sample.BaseToroFragment;
import im.ene.toro.sample.R;
import im.ene.toro.sample.feature.basic4.Basic4Adapter;

/**
 * Created by eneim on 3/14/17.
 */

public class BottomPanelFragment extends BaseToroFragment {

  private static final String TAG = "ToroLib:BottomSheet";

  public static BottomPanelFragment newInstance() {
    Bundle args = new Bundle();
    BottomPanelFragment fragment = new BottomPanelFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  MainListAdapter adapter;

  Basic4Adapter bottomListAdapter;
  @BindView(R.id.bottom_sheet) RecyclerView bottomSheet;
  BottomSheetBehavior bottomSheetBehavior;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
  }

  Unbinder unbinder;

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);

    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(layoutManager);
    adapter = new MainListAdapter();
    recyclerView.setAdapter(adapter);

    LinearLayoutManager bottomSheetLayoutManager = new LinearLayoutManager(getContext());
    bottomSheet.setLayoutManager(bottomSheetLayoutManager);
    bottomListAdapter = new Basic4Adapter();
    bottomSheet.setAdapter(bottomListAdapter);
    bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

    Toro.register(bottomSheet);

    bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
      @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
        Log.w(TAG, "onStateChanged() called with: bottomSheet = [" + Integer.toHexString(
            bottomSheet.hashCode()) + "], newState = [" + newState + "]");
        // Apply pause/resume logic
        if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
          Toro.pause();
        } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
          Toro.resume();
        }
      }

      @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {

      }
    });
    bottomSheetBehavior.setPeekHeight(256);
    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

    // check first state
    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
      Toro.pause();
    }
  }

  @Override public void onDestroyView() {
    Toro.unregister(bottomSheet);
    if (unbinder != null) {
      unbinder.unbind();
    }
    super.onDestroyView();
  }
}
