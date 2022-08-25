package com.example.adventapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adventapp.R
import com.example.adventapp.databinding.MainFragmentBinding
import com.example.common.extensions.argument
import com.example.adventapp.di.getAppComponent
import com.example.common.extensions.viewModels
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

internal class MainFragment : Fragment() {

    companion object {
        private const val ARG = "ARG"

        fun newInstance(number: Long) = MainFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG, number)
            }
        }
    }

    private val number: Long by argument(ARG, 0)

    @Inject
    lateinit var viewModelFactory: MainViewModel.Factory

    private val viewModel by viewModels { viewModelFactory.get(number) }

    lateinit var binding: MainFragmentBinding

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
            val date = "01-01-2022 00:00:00"
            mainFragmentCountdownView.start(countDown(date, Date()))
            mainFragmentButtonStart.setOnClickListener { viewModel.onStartButtonClicked(number) }
            mainFragmentButtonAbout.setOnClickListener { viewModel.onAboutButtonClicked(number) }
            mainFragmentButtonExit.setOnClickListener { viewModel.onExitButtonClicked() }
            main.setBackgroundResource(number.toInt())
        }
    }

    private fun countDown(countDate: String? = "00-00-0000 00:00:00", currentDate: Date): Long {
        return SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(countDate).time - currentDate.time
    }

    override fun onStart() {
        super.onStart()
        binding.mainFragmentBlur.startBlur()
    }

    override fun onStop() {
        super.onStop()
        binding.mainFragmentBlur.pauseBlur()
    }
}