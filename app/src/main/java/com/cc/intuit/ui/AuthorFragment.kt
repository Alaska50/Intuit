package com.cc.intuit.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cc.intuit.*
import com.cc.intuit.models.ImageData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AuthorFragment: Fragment(R.layout.author) {


    private val viewModel: ViewModel by activityViewModels()
    private val pagingAdapter = ImageAdapter(ImageComparator) { data ->
        rowClicked(data)
    }

    private var recyclerView: RecyclerView? = null
    private var button: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        /**
         * NOTE : can use view binding
         */
        recyclerView = view?.findViewById(R.id.recyclerview)
        button = view?.findViewById(R.id.retryButton)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button?.setOnClickListener {
            pagingAdapter.retry()
        }

        pagingAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        pagingAdapter.addLoadStateListener { states ->
            if (states.prepend is LoadState.Error ||
                    states.append is LoadState.Error ||
                    states.refresh is LoadState.Error) {
                button?.visibility = View.VISIBLE

            } else {
                button?.visibility = View.GONE

            }
        }

        recyclerView?.layoutManager = LinearLayoutManager(context)


        recyclerView?.adapter = pagingAdapter

        getData()

    }

    private fun getData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getData().collect { data ->
                pagingAdapter.submitData(data)
            }
        }
    }

    private fun rowClicked(data: ImageData?) {
        /**
         * NOTE : can use a nav graph if app is going to stick to a single activity
         * with many fragments structure
         */
        val details = DetailFragment.newInstance(data)
        parentFragmentManager.beginTransaction().replace(R.id.fragment_layout, details)
            .addToBackStack("").commit()
    }

    companion object {
        fun newInstance(): AuthorFragment {
            return AuthorFragment()
        }
    }
}