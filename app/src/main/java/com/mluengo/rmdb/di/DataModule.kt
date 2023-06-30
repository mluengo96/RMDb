package com.mluengo.rmdb.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mluengo.rmdb.data.local.database.RmdbDatabase
import com.mluengo.rmdb.data.local.entitities.CharacterEntity
import com.mluengo.rmdb.data.network.mediators.CharacterRemoteMediator
import com.mluengo.rmdb.data.network.retrofit.RetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCharacterPager(
        database: RmdbDatabase,
        api: RetrofitApi
    ): Pager<Int, CharacterEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = CharacterRemoteMediator(
                database = database,
                api = api
            ),
            pagingSourceFactory = {
                database.charactersDao().pagingSource()
            }
        )
    }
}