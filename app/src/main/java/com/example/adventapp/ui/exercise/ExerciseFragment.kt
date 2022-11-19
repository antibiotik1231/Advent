package com.example.adventapp.ui.exercise

import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adventapp.R
import com.example.adventapp.databinding.ExerciseFragmentBinding
import com.example.common.extensions.argument
import com.example.adventapp.di.getAppComponent
import com.example.adventapp.ui.ShowHintDialogCommand
import com.example.adventapp.ui.UiModel
import com.example.common.extensions.viewModels
import com.example.adventapp.ui.dialog.HintDialogFragment
import com.example.common.extensions.observe
import com.example.common.mvvm.ViewCommand
import javax.inject.Inject

internal class ExerciseFragment : Fragment() {

    companion object {
        private const val ARG = "ARG"
        private const val POSITION = "POSITION"
        private const val DIALOG = "DIALOG"

        fun newInstance(uiModel: UiModel, position: Int) = ExerciseFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG, uiModel)
                putInt(POSITION, position)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ExerciseViewModel.Factory

    private lateinit var binding: ExerciseFragmentBinding

    private val uiModel by argument(ARG, UiModel.EMPTY)

    private val position: Int by argument(POSITION, 0)

    private val viewModel: ExerciseViewModel by viewModels {
        viewModelFactory.get(
            uiModel,
            position
        )
    }

    override fun onAttach(context: Context) {
        context.getAppComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.exercise_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ExerciseFragmentBinding.bind(view)
        with(binding) {
            exerciseFragmentTextView.movementMethod = ScrollingMovementMethod()

            exerciseFragmentButtonSubmit.setOnClickListener {
                viewModel.onSubmitButtonClicked(exerciseFragmentEditText.text.toString().trim())
            }
            exerciseFragmentToolbar.setNavigationOnClickListener { viewModel.onBackPressed() }
            exercise.setBackgroundResource(uiModel.backgroundImageId)
            exerciseFragmentButtonHint.setOnClickListener {
                viewModel.onHintButtonClicked()
            }
        }

        observe(viewModel.viewState, this::handleViewState)
        observe(viewModel.viewCommands, this::handleViewCommands)
    }

    private fun handleViewState(viewState: ExerciseViewState) {
        binding.exerciseFragmentTextView.text = viewState.currentQuestion?.text
    }

    private fun handleViewCommands(viewCommand: ViewCommand) {
        if (viewCommand is ShowHintDialogCommand) {
            HintDialogFragment.newInstance(position, viewCommand.hint)
                .show(childFragmentManager, DIALOG)
        }
    }
}
