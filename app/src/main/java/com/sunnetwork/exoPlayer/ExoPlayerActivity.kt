package com.sunnetwork.exoPlayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.sunnetwork.databinding.ActivityExoPlayerBinding

class ExoPlayerActivity : AppCompatActivity() {

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityExoPlayerBinding.inflate(layoutInflater)
    }
    private lateinit var simpleExoPlayer: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        setExoPlayer()
    }

    private fun setExoPlayer() {
        viewBinding.playerView.findViewById<ImageView>(com.sunnetwork.R.id.playerBackIv).setOnClickListener {
            finish()
        }
        viewBinding.playerView.findViewById<TextView>(com.sunnetwork.R.id.playerTitle).text = intent.getStringExtra("title")?:""
        val videoUri = Uri.parse(intent.getStringExtra("url")?:"")
        val bandwidthMeter = DefaultBandwidthMeter()
        val trackSelector = DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this@ExoPlayerActivity,trackSelector)
        val factory = DefaultHttpDataSourceFactory("exoplayer_video")
        val extractorFactory = DefaultExtractorsFactory()
        val mediaSource = ExtractorMediaSource(videoUri,factory,extractorFactory,null,null)
        viewBinding.playerView.let {
            it.player = simpleExoPlayer
            it.keepScreenOn = true
        }

        simpleExoPlayer.let {
            it.prepare(mediaSource)
            it.addListener(object : Player.EventListener{
                override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {
                }

                override fun onTracksChanged(
                    trackGroups: TrackGroupArray?,
                    trackSelections: TrackSelectionArray?
                ) {
                }

                override fun onLoadingChanged(isLoading: Boolean) {
                }

                override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                    if (playbackState == Player.STATE_BUFFERING){
                        viewBinding.progressBar.visibility = View.VISIBLE

                    }else if (playbackState == Player.STATE_READY){
                        viewBinding.progressBar.visibility = View.GONE
                    }
                }

                override fun onRepeatModeChanged(repeatMode: Int) {
                }

                override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
                }

                override fun onPlayerError(error: ExoPlaybackException?) {
                }

                override fun onPositionDiscontinuity(reason: Int) {
                }

                override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {
                }

                override fun onSeekProcessed() {
                }

            })
        }
    }

    override fun onPause() {
        super.onPause()
        simpleExoPlayer.let {
            it.playWhenReady = false
            it.playbackState
        }
    }

    override fun onResume() {
        super.onResume()
        simpleExoPlayer.let {
            it.playWhenReady = true
            it.playbackState
        }
    }

}