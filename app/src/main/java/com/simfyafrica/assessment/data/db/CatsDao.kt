package com.simfyafrica.assessment.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import com.simfyafrica.assessment.data.model.Cat

@Dao
interface CatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCat(cat: Cat): Completable

    @Query("DELETE FROM cat_table")
    fun deleteAllCats(): Completable

    @Query("SELECT * FROM cat_table")
    fun getAllCats(): LiveData<List<Cat>>

    @Query("SELECT * FROM cat_table WHERE id = :id ")
    fun getACat(id: String): LiveData<Cat>
}