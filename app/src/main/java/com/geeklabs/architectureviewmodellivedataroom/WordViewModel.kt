package com.geeklabs.architectureviewmodellivedataroom


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

class WordViewModel constructor(application: Application) : AndroidViewModel(application) {

    private var mRepository: WordRepository? = null
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    var allWords: LiveData<List<Word>>? = null

    init {
        mRepository = WordRepository(application)
        allWords = mRepository!!.allWords
    }

    fun insert(word: Word) {
        mRepository!!.insert(word)
    }
}