package com.jonathannakhla.nytimesdemo.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonathannakhla.nytimesdemo.R
import com.jonathannakhla.nytimesdemo.adapters.TopStoriesAdapter
import com.jonathannakhla.nytimesdemo.data.Article
import com.jonathannakhla.nytimesdemo.utils.ArticleClickListener
import com.jonathannakhla.nytimesdemo.utils.into
import com.jonathannakhla.nytimesdemo.viewmodels.MainViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment: Fragment() {

    companion object {
        const val TAG = "MainFragment"

        fun newInstance() = MainFragment()
    }

    private val mainViewModel by viewModel<MainViewModel>()

    private val disposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe_container.setOnRefreshListener {
            loadTopStories(false)
        }

        recyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        loadTopStories(true)
    }

    private fun loadTopStories(showProgressBar: Boolean) {
        mainViewModel.getTopStories()
            .singleOrError()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                recyclerview.adapter = TopStoriesAdapter(emptyList(), getArticleClickListener())
                if (showProgressBar) progress_bar.visibility = View.VISIBLE
            }
            .map { recyclerview.adapter = TopStoriesAdapter(it, getArticleClickListener()) }
            .subscribe(
                {
                    Log.d(TAG, "Initial loading of list complete!")
                    swipe_container.isRefreshing = false
                    progress_bar.visibility = View.GONE
                },
                {
                    Log.e(TAG, "Error with initial loading of list", it)
                }
            )
            .into(disposable)
    }

    private fun getArticleClickListener(): ArticleClickListener {
       return object : ArticleClickListener {
            override fun onArticleClick(article: Article, sharedImage: View, sharedText: View) {
                mainViewModel.navigateToArticleDetails(
                    this@MainFragment,
                    activity,
                    article,
                    sharedImage,
                    sharedText)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        val searchView = menu.findItem(R.id.search_menu_item).actionView as SearchView

        mainViewModel.getSearchObservable(searchView)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { recyclerview.adapter = TopStoriesAdapter(it, getArticleClickListener()) }
            .subscribe(
                {
                    Log.d(TAG, "Searching complete!")
                },
                {
                    Log.e(TAG, "Error filtering results", it)
                }
            )
            .into(disposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
    }
}