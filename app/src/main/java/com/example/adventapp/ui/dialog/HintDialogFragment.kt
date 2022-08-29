package com.example.adventapp.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adventapp.R
import com.example.adventapp.databinding.HintDialogFragmentBinding
import com.example.common.extensions.argument
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

internal class HintDialogFragment : BottomSheetDialogFragment() {

    companion object {
        private const val POSITION = "POSITION"
        private const val HINT = "HINT"

        fun newInstance(position: Int, hint: String) = HintDialogFragment().apply {
            arguments = Bundle().apply {
                putInt(POSITION, position)
                putString(HINT, hint)
            }
        }
    }

    private val position by argument(POSITION, 0)

    private val hint by argument(HINT, "")

    private lateinit var binding: HintDialogFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.hint_dialog_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HintDialogFragmentBinding.bind(view)
        with(binding) {
            hintTextview.text = "asdasda"
            hintButtonClose.setOnClickListener { dismiss() }
        }
    }
}