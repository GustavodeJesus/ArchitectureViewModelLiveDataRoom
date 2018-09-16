package com.geeklabs.architectureviewmodellivedataroom

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface WordDao {

    @get:Query("SELECT * from word_table ORDER BY word ASC")
    val alphabetizedWords: LiveData<List<Word>>

    @Insert
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()
}
