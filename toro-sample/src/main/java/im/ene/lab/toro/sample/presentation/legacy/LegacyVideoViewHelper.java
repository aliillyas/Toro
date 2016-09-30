/*
 * Copyright 2016 eneim@Eneim Labs, nam@ene.im
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

package im.ene.lab.toro.sample.presentation.legacy;

import android.media.MediaPlayer;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.view.View;
import im.ene.lab.toro.PlayerViewHelper;
import im.ene.lab.toro.ToroPlayer;

/**
 * Created by eneim on 9/29/16.
 */

public class LegacyVideoViewHelper extends PlayerViewHelper
    implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

  private boolean playWhenReady;
  private MediaPlayer mediaPlayer;

  public LegacyVideoViewHelper(@NonNull ToroPlayer player, @NonNull View itemView) {
    super(player, itemView);
  }

  public void setPlayWhenReady(boolean playWhenReady) {
    this.playWhenReady = playWhenReady;
  }

  @Override public void onPrepared(MediaPlayer mp) {
    this.mediaPlayer = mp;
    this.player.onVideoPrepared();
    super.onPrepared(this.itemView, this.itemView.getParent());

    if (playWhenReady) {
      this.player.onPlaybackStarted();
    } else {
      this.player.onPlaybackPaused();
    }
  }

  @Override public void onCompletion(MediaPlayer mp) {
    this.player.onPlaybackCompleted();
    super.onCompletion();
  }

  public final void preparePlayer() {
    if (this.mediaPlayer != null) {
      this.mediaPlayer.prepareAsync();
    }
  }

  public final void releasePlayer() {
    if (this.mediaPlayer != null) {
      this.mediaPlayer.release();
      this.mediaPlayer = null;
    }
  }

  public final void setVolume(@FloatRange(from = 0.0, to = 1.0) float volume) {
    if (this.mediaPlayer != null) {
      this.mediaPlayer.setVolume(volume, volume);
    }
  }
}
