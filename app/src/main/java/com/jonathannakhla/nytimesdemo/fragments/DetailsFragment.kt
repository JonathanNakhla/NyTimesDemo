package com.jonathannakhla.nytimesdemo.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.jonathannakhla.nytimesdemo.R
import com.jonathannakhla.nytimesdemo.viewmodels.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment: Fragment() {
    companion object {
        const val TAG = "DetailsFragment"
        private const val ARTICLE_ID_KEY = "articleId"

        fun newInstance(articleId: Int): DetailsFragment {
            val detailsFragment = DetailsFragment()

            val args = Bundle()
            args.putInt(ARTICLE_ID_KEY, articleId)
            detailsFragment.arguments = args

            return detailsFragment
        }
    }

    private val detailsViewModel by viewModel<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setTransitionName(article_image, "article_image")
        ViewCompat.setTransitionName(article_title, "article_title")

        val articleId = arguments?.getInt(ARTICLE_ID_KEY) ?: 0
        val article = detailsViewModel.getArticle(articleId)

        article_title.text = article.title
        article_abstract.text = article.abstract

        postponeEnterTransition()
        Glide.with(this)
            .load(article.multimedias?.get(0)?.url)
            .placeholder(R.drawable.placeholder_drawable)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            })
            .into(article_image)

            fab.setOnClickListener {
                detailsViewModel.shareArticle(article, this)
            }

    }
}