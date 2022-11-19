package com.example.adventapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adventapp.R
import com.example.adventapp.databinding.MainFragmentBinding
import com.example.adventapp.di.getAppComponent
import com.example.adventapp.ui.UiModel
import com.example.common.extensions.argument
import com.example.common.extensions.viewModels
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

internal class MainFragment : Fragment() {

    companion object {
        private const val ARG = "ARG"

        fun newInstance(uiModel: UiModel) = MainFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG, uiModel)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: MainViewModel.Factory

    private lateinit var binding: MainFragmentBinding

    private val uiModel by argument(ARG, UiModel.EMPTY)

    private val viewModel by viewModels { viewModelFactory.get(uiModel) }

    override fun onAttach(context: Context) {
        context.getAppComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.main_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        with(binding) {
            val date = "01-01-2023 00:00:00"
            mainFragmentToolbarTextView.text = "${uiModel.mode.string}'s Advent"
            mainFragmentCountdownView.start(countDown(date, Date()))
            mainFragmentButtonStart.setOnClickListener { viewModel.onStartButtonClicked() }
            mainFragmentButtonAbout.setOnClickListener { viewModel.onAboutButtonClicked() }
            mainFragmentButtonExit.setOnClickListener { viewModel.onExitButtonClicked() }
            main.setBackgroundResource(uiModel.backgroundImageId)
        }
    }

    override fun onStart() {
        super.onStart()
        binding.mainFragmentBlur.startBlur()
    }

    override fun onStop() {
        super.onStop()
        binding.mainFragmentBlur.pauseBlur()
    }

    private fun countDown(countDate: String? = "00-00-0000 00:00:00", currentDate: Date): Long {
        return SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(countDate).time - currentDate.time
    }
}
