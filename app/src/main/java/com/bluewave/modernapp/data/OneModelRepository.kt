package com.bluewave.modernapp.data

import com.bluewave.modernapp.data.local.database.OneModel
import com.bluewave.modernapp.data.local.database.OneModelDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


interface OneModelRepository {
    val myItems: Flow<List<String>>

    suspend fun add(name: String)
}

class DefaultOneModelRepository @Inject constructor(
    private val oneModelDao: OneModelDao
) : OneModelRepository {
    override val myItems: Flow<List<String>> =
        oneModelDao.getMyItems().map { items -> items.map { it.name } }

    override suspend fun add(name: String) {
        oneModelDao.insert(OneModel(name = name))
    }

}