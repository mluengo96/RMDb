package com.mluengo.rmdb.di

import android.content.Context
import androidx.room.Room
import com.mluengo.rmdb.data.local.database.RmdbDatabase
import com.mluengo.rmdb.data.local.database.util.LocationConverter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    private val converter = LocationConverter(Moshi.Builder().build())

    @Provides
    @Singleton
    fun providesRmdbDatabase(
        @ApplicationContext context: Context,
    ): RmdbDatabase = Room.databaseBuilder(
        context,
        RmdbDatabase::class.java, "rmdb_database")
        .addTypeConverter(converter)
        .build()
}