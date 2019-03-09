package com.gregcodes.requester

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RequestRepository {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(request: Request)

    @Query("SELECT * FROM request")
    fun findAll() : List<Request>

}