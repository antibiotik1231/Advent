package com.example.adventapp.ui.landing

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adventapp.R
import com.example.adventapp.databinding.LandingFragmentBinding
import com.example.adventapp.di.getAppComponent
import com.example.adventapp.domain.entity.Mode
import com.example.common.extensions.argument
import com.example.common.extensions.viewModels
import javax.inject.Inject

internal class LandingFragment : Fragment() {

    companion object {
        private const val ARG = "ARG"

        fun newInstance(backgroundImageId: Int) = LandingFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG, backgroundImageId)
            }
        }
    }

    private val backgroundImageId: Int by argument(ARG, 0)

    @Inject
    lateinit var viewModelFactory: LandingViewModel.Factory

    private val viewModel by viewModels { viewModelFactory.get(backgroundImageId) }

    private lateinit var binding: LandingFragmentBinding

    override fun onAttach(context: Context) {
        context.getAppComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.landing_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LandingFragmentBinding.bind(view)
        with(binding) {
            landingFragmentButtonBob.setOnClickListener { viewModel.onButtonPressed(Mode.BOB) }
            landingFragmentButtonDiana.setOnClickListener { viewModel.onButtonPressed(Mode.DIANA) }
            root.setBackgroundResource(backgroundImageId)
        }
    }

    override fun onStart() {
        super.onStart()
        binding.landingFragmentBlur.startBlur()
    }

    override fun onStop() {
        super.onStop()
        binding.landingFragmentBlur.pauseBlur()
    }
}