package com.example.adventapp.ui.menu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.adventapp.R
import com.example.adventapp.databinding.MenuFragmentBinding
import com.example.adventapp.di.getAppComponent
import com.example.adventapp.ui.UiModel
import com.example.adventapp.ui.recycler.StickersAdapter
import com.example.adventapp.ui.recycler.StickersModel
import com.example.common.extensions.argument
import com.example.common.extensions.viewModels
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

internal class MenuFragment : Fragment() {

    companion object {
        private const val ARG = "ARG"

        fun newInstance(uiModel: UiModel) = MenuFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG, uiModel)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: MenuViewModel.Factory

    private val uiModel by argument(ARG, UiModel.EMPTY)

    private val viewModel by viewModels { viewModelFactory.get(uiModel) }

    private lateinit var binding: MenuFragmentBinding

    private lateinit var adapter: StickersAdapter

    private val stickers = mutableListOf<StickersModel>()

    private val toasts = listOf(
        "Don't mess with Time travel",
        "Seriously!",
        "I've told you - it's useless!",
        "Don't make me say that again",
        "YOU SHALL NOT PASS!!"
    )

    override fun onAttach(context: Context) {
        context.getAppComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.menu_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stickers.clear()
        binding = MenuFragmentBinding.bind(view)
        with(binding) {
            adapter = StickersAdapter { position -> onItemClicked(position) }
            menuFragmentRecyclerView.layoutManager = GridLayoutManager(context, 3)
            menuFragmentRecyclerView.adapter = adapter
            adapter.submitList(viewModel.setItems(stickers))
            menuFragmentToolbar.setNavigationOnClickListener { viewModel.onBackPressed() }
            menu.setBackgroundResource(uiModel.backgroundImageId)
        }
    }

    private fun onItemClicked(position: Int) {
        val stickerTitle = stickers[position].title.dropLast(2).toInt()
        if (stickerTitle > Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
            Toast.makeText(context, toasts[Random.nextInt(0, toasts.size)], Toast.LENGTH_SHORT)
                .show()
        } else {
            viewModel.onItemClicked(stickerTitle)
        }
    }
}
