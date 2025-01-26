package com.bluewave.modernapp.data.di

import com.bluewave.modernapp.data.DefaultOneModelRepository
import com.bluewave.modernapp.data.OneModelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataModule{

    @Singleton
    @Binds
    fun bindsOneModelRepository(
        oneModelRepository: DefaultOneModelRepository
    ): OneModelRepository
}

class FakeMyModelRepository @Inject constructor() : OneModelRepository {
    override val myItems: Flow<List<String>> = flowOf(fakeMyItems)

    override suspend fun add(name: String) {
        throw NotImplementedError()
    }
}

val fakeMyItems = listOf("One", "Two", "Three")