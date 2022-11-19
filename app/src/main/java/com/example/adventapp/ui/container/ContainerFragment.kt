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
import com.example.adventapp.domain.entity.Mode
import com.example.adventapp.ui.UiModel
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
            uiModel: UiModel,
            description: String,
            position: Int
        ) = ContainerFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG, uiModel)
                putString(DESCRIPTION, description)
                putInt(POSITION, position)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ContainerViewModel.Factory

    private val uiModel by argument(ARG, UiModel.EMPTY)
    private val description: String by argument(DESCRIPTION, "ABOUT")
    private val position: Int by argument(POSITION, 0)

    private val viewModel: ContainerViewModel by viewModels {
        viewModelFactory.get(
            uiModel,
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
                else -> {
                    containerFragmentTitle.text = getString(R.string.about)
                    containerFragmentTextView.text =
                        if (uiModel.mode == Mode.DIANA) {
                            "Это твой Адвент календарь, чтобы начиная с самых первых чисел декабря у тебя потихоньку появлялось новогоднее настроение! И пусть в этом году тебя ждет не такой Адвент, как в прошлом году, но зато в самом конце, в канун Нового года тебя ждет сюрприз! Ну что ж, начнем?"
                        } else {
                            ""
                        }
                    containerFragmentButtonTryAgain.visibility = View.GONE
                }
            }
            containerFragmentButtonTryAgain.setOnClickListener {
                viewModel.onTryAgainButtonPressed()
            }
            containerFragmentButtonExit.setOnClickListener {
                viewModel.onBackButtonPressed()
            }
            containerFragmentToolbar.setNavigationOnClickListener { viewModel.onBackPressed() }
            container.setBackgroundResource(uiModel.backgroundImageId)
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
