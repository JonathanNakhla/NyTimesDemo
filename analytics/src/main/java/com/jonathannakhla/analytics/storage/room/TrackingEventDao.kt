package com.jonathannakhla.analytics.storage.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TrackingEventDao {
    @Query("SELECT * FROM TrackingEventEntity")
    fun getAll(): Single<List<TrackingEventEntity>>

    @Query("DELETE FROM TrackingEventEntity")
    fun deleteAll(): Completable

    @Delete
    fun delete(vararg trackingEventEntity: TrackingEventEntity)

    @Insert
    fun insert(trackingEventEntity: TrackingEventEntity): Completable

    @Query("SELECT COUNT(*) FROM TrackingEventEntity")
    fun getCount(): Single<Int>
}