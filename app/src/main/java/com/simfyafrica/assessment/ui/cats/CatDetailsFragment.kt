package com.simfyafrica.assessment.ui.cats

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.simfyafrica.assessment.R
import com.simfyafrica.assessment.databinding.CatDetailsFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CatDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CatDetailsViewModel by lazy {
        viewModelFactory.create(CatDetailsViewModel::class.java)
    }

    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private lateinit var mediaSource: MediaSource
    private lateinit var dataSourceFactory: DefaultDataSourceFactory

    private lateinit var binding: CatDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CatDetailsFragmentBinding.inflate(inflater, container, false)
        binding.catDetailViewModel = viewModel

        viewModel.catData.observe(viewLifecycleOwner, Observer {
            binding.titleText.text = it.title
            binding.descriptionText.text = it.description
        })
        binding.lifecycleOwner = this

        initializePlayer()

        with(simpleExoPlayer) {
            prepare(mediaSource)
            binding.playSoundButton.setOnClickListener {
                playWhenReady = true
            }
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val id = arguments?.getString("id")
        val position = arguments?.getInt("position", -1)
        if (id != null) {
            if (position != null) {
                viewModel.getCatDetails(id, position)
            }
        }
    }

    fun initializePlayer(){
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context)
        dataSourceFactory = DefaultDataSourceFactory(context, Util.getUserAgent(context, "Simfy"))
        mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(getString(
            R.string.cat_moew)))
    }

    private fun releasePlayer() {
        simpleExoPlayer.release()
    }

    public override fun onStart() {
        super.onStart()

        if (Util.SDK_INT > 23) initializePlayer()
    }

    public override fun onResume() {
        super.onResume()

        if (Util.SDK_INT <= 23) initializePlayer()
    }

    public override fun onPause() {
        super.onPause()

        if (Util.SDK_INT <= 23) releasePlayer()
    }

    public override fun onStop() {
        super.onStop()

        if (Util.SDK_INT > 23) releasePlayer()
    }

    companion object {
        @JvmStatic
        val TAG = CatDetailsFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = CatDetailsFragment().apply {
            arguments = Bundle()
        }
    }
}