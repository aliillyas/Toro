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

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import im.ene.toro.ToroAdapter;
import im.ene.toro.sample.R;
import im.ene.toro.sample.ToroApp;
import im.ene.toro.sample.feature.facebook.timeline.OgpItemViewHolder;

/**
 * Created by eneim on 3/14/17.
 */

public class MainListAdapter extends ToroAdapter<ToroAdapter.ViewHolder> {

  View.OnClickListener clickListener;

  public void setClickListener(View.OnClickListener clickListener) {
    this.clickListener = clickListener;
  }

  @Nullable @Override protected Object getItem(int position) {
    return ToroApp.getApp().getString(R.string.sample);
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.vh_fb_feed_post_text, parent, false);
    ViewHolder viewHolder = new OgpItemViewHolder(view);
    viewHolder.setOnItemClickListener(clickListener);
    return viewHolder;
  }

  @Override public int getItemCount() {
    return Integer.MAX_VALUE;
  }

}
