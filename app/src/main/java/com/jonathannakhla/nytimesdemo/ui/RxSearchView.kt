package com.jonathannakhla.nytimesdemo.ui

import androidx.appcompat.widget.SearchView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter

class RxSearchView {

    fun getObservable(searchView: SearchView): Observable<String> {
        return Observable.create<String> { emitter ->
            createListener(searchView, emitter)
        }
    }

    private fun createListener(searchView: SearchView, emitter: ObservableEmitter<String>) {
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                emitter.onComplete()
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                emitter.onNext(newText)
                return false
            }
        })
    }
}