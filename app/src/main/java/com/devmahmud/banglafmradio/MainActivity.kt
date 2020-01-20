package com.devmahmud.banglafmradio

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var player: SimpleExoPlayer
    private lateinit var mediaSource: MediaSource
    private lateinit var dataSourceFactory: DefaultDataSourceFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player = ExoPlayerFactory.newSimpleInstance(this)

        dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "exoPlayerSample"))

        mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(RADIO_URL))


        with(player) {
            prepare(mediaSource)
            btnStart.setOnClickListener {
                playWhenReady = true
            }

            btnStop.setOnClickListener {
                playWhenReady = false
            }
        }

    }

    override fun onDestroy() {
        player.playWhenReady = false
        super.onDestroy()
    }

    companion object {
        // const val RADIO_URL = "http://airspectrum.cdnstream1.com:8114/1648_128"
        const val RADIO_URL = "http://69.175.39.13/stream"
    }
}
