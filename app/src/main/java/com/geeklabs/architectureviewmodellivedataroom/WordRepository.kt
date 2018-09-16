package com.geeklabs.architectureviewmodellivedataroom

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */

class WordRepository constructor(application: Application) {
    private val mWordDao: WordDao
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        mWordDao = db!!.wordDao()
        allWords = mWordDao.alphabetizedWords
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    fun insert(word: Word) {
        insertAsyncTask(mWordDao).execute(word)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) : AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}