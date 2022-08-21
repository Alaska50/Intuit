package com.cc.intuit.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.distinctUntilChanged
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cc.intuit.R
import com.cc.intuit.models.ImageData

class DetailFragment : Fragment(R.layout.details) {

    private val viewModel: ViewModel by activityViewModels()

    private var authorTextView:TextView? = null
    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback {
            parentFragmentManager.popBackStackImmediate()
        }

        arguments?.let { bundle ->
            bundle.getString(AUTHOR_KEY)?.let {
                viewModel.setAuthor(it)
            }
            bundle.getString(URL_KEY)?.let {
                viewModel.setImageUrl(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        /**
         * NOTE : can use view binding
         */
        authorTextView = view?.findViewById(R.id.authorTitle)
        imageView = view?.findViewById(R.id.image)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.author
            .distinctUntilChanged()
            .observe(viewLifecycleOwner) {
            authorTextView?.text = it
        }

        viewModel.image_url
            .distinctUntilChanged()
            .observe(viewLifecycleOwner) { urlString ->
            imageView?.let { iView ->
                Glide.with(iView)
                    .load(urlString)
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(iView)
            }

        }
    }

    companion object {

        private const val AUTHOR_KEY = "AUTHOR_KEY"
        private const val URL_KEY = "URL_KEY"

        fun newInstance(data: ImageData?): DetailFragment {
            val frag = DetailFragment()
            frag.arguments = Bundle().apply {
                putString(AUTHOR_KEY, data?.author)
                putString(URL_KEY, data?.downloadURL)
            }
            return frag
        }
    }
}