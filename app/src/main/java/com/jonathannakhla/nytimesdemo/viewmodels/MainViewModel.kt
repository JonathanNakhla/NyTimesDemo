package com.jonathannakhla.nytimesdemo.viewmodels

import android.transition.Fade
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.jonathannakhla.analytics.data.EventNames
import com.jonathannakhla.analytics.data.PropertyNames
import com.jonathannakhla.analytics.data.TrackingEvent
import com.jonathannakhla.analytics.track.Trackers
import com.jonathannakhla.nytimesdemo.R
import com.jonathannakhla.nytimesdemo.data.Article
import com.jonathannakhla.nytimesdemo.fragments.DetailsFragment
import com.jonathannakhla.nytimesdemo.repositories.TopStoriesRepo
import com.jonathannakhla.nytimesdemo.ui.DetailsTransition
import com.jonathannakhla.nytimesdemo.ui.RxSearchView
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit


class MainViewModel(private val topStoriesRepo: TopStoriesRepo,
                    private val rxSearchView: RxSearchView,
                    private val trackers: Trackers): ViewModel() {

    companion object {
        private const val DEBOUNCE_TIME = 300L
    }

    fun getTopStories(invalidate: Boolean = false): Observable<List<Article>> {
        return topStoriesRepo.getTopStories(invalidate)
    }

    fun getSearchObservable(searchView: SearchView): Observable<List<Article>> {
        val queryObservable = rxSearchView.getObservable(searchView)

        return queryObservable.debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
            .map { it.trim() }
            .flatMap { topStoriesRepo.searchTopStories(it) }
    }

    fun navigateToArticleDetails(fragment: Fragment,
                                 activity: FragmentActivity?,
                                 article: Article,
                                 sharedImage: View,
                                 sharedText: View) {

        trackArticleClicked(article)

        val hashCode = article.hashCode()
        val detailsFragment = DetailsFragment.newInstance(hashCode)

        detailsFragment.apply {
            sharedElementEnterTransition = DetailsTransition()
            enterTransition = Fade()
            sharedElementReturnTransition = DetailsTransition()
        }
        fragment.exitTransition = Fade()

        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.addSharedElement(sharedImage, "article_image")
            ?.addSharedElement(sharedText, "article_title")
            ?.replace(R.id.main_container, detailsFragment, DetailsFragment.TAG)
            ?.addToBackStack(null)
            ?.commit()

    }

    fun trackArticleView(article: Article) {
        trackers.track(TrackingEvent(EventNames.ARTICLE_VIEW,
            mapOf(PropertyNames.ARTICLE_TITLE to article.title,
                PropertyNames.ARTICLE_DATE to article.date,
                PropertyNames.ARTICLE_URL to article.url
            )
        ))
    }

    private fun trackArticleClicked(article: Article) {
        trackers.track(TrackingEvent(EventNames.ARTICLE_CLICK,
            mapOf(PropertyNames.ARTICLE_TITLE to article.title,
                PropertyNames.ARTICLE_DATE to article.date,
                PropertyNames.ARTICLE_URL to article.url
            )
        ))
    }
}