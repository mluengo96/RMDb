package com.mluengo.rmdb.data.network.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mluengo.rmdb.data.local.database.RmdbDatabase
import com.mluengo.rmdb.data.local.entitities.CharacterEntity
import com.mluengo.rmdb.data.local.entitities.asExternalModel
import com.mluengo.rmdb.data.network.models.NetworkCharacter
import com.mluengo.rmdb.data.network.models.asEntity
import com.mluengo.rmdb.data.network.models.asExternalModel
import com.mluengo.rmdb.data.network.retrofit.RetrofitApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val database: RmdbDatabase,
    private val api: RetrofitApi,
): RemoteMediator<Int, CharacterEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) 1
                    else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val characters = api.getCharactersPaginated(
                page = loadKey
            )

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.charactersDao().clearAll()
                }
                val characterEntities = characters.body()?.results?.map(NetworkCharacter::asEntity)
                if (characterEntities != null) {
                    database.charactersDao().upsertAll(characterEntities)
                }
            }

            MediatorResult.Success(
                endOfPaginationReached = characters.body()?.results?.isEmpty() == true
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}