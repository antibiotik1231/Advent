package com.example.adventapp.ui.container

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.adventapp.R
import com.example.adventapp.databinding.ContainerFragmentBinding
import com.example.adventapp.di.getAppComponent
import com.example.adventapp.domain.entity.Description
import com.example.common.extensions.argument
import com.example.common.extensions.observe
import com.example.common.extensions.viewModels
import javax.inject.Inject

internal class ContainerFragment : Fragment() {

    companion object {
        private const val ARG = "ARG"
        private const val DESCRIPTION = "DESCRIPTION"
        private const val POSITION = "POSITION"

        fun newInstance(
            backgroundImageId: Long,
            description: String,
            position: Int
        ) = ContainerFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG, backgroundImageId)
                putString(DESCRIPTION, description)
                putInt(POSITION, position)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ContainerViewModel.Factory

    private val backgroundImageId: Long by argument(ARG, 0)
    private val description: String by argument(DESCRIPTION, "ABOUT")
    private val position: Int by argument(POSITION, 0)

    private val viewModel: ContainerViewModel by viewModels {
        viewModelFactory.get(
            backgroundImageId,
            description,
            position
        )
    }

    private lateinit var binding: ContainerFragmentBinding

    override fun onAttach(context: Context) {
        context.getAppComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.container_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ContainerFragmentBinding.bind(view)
        with(binding) {
            when (description) {
                Description.CORRECT.name -> {
                    containerFragmentTextView.text = getString(R.string.correct)
                    containerFragmentTitle.text = getString(R.string.correct)
                }
                Description.INCORRECT.name -> {
                    containerFragmentTextView.text = getString(R.string.incorrect)
                    containerFragmentTitle.text = getString(R.string.incorrect)
                }
                Description.ABOUT.name -> {
                    containerFragmentTitle.text = getString(R.string.about)
                    containerFragmentTextView.text = getString(R.string.text_about)
                    containerFragmentButtonTryAgain.visibility = View.GONE
                }
            }
            containerFragmentButtonTryAgain.setOnClickListener {
                viewModel.onTryAgainButtonPressed(backgroundImageId, position)
            }
            containerFragmentButtonExit.setOnClickListener {
                viewModel.onBackButtonPressed(backgroundImageId)
            }
            containerFragmentToolbar.setNavigationOnClickListener { viewModel.onBackPressed() }
            container.setBackgroundResource(backgroundImageId.toInt())
        }

        observe(viewModel.viewState, this::handleViewState)
    }

    private fun handleViewState(viewState: ContainerViewState) {
        setImageUrl(binding.containerFragmentImageView, viewState.giphyUrl)
    }

    private fun setImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView)
            .asGif()
            .load(url)
            .into(imageView)
    }
}
