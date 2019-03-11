package com.gregcodes.requester

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface RequestRepository {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(request: Request) : Completable

    @Query("SELECT * FROM request")
    fun findAll() : Flowable<List<Request>>

    @Delete
    fun delete(item: Request) : Completable

}