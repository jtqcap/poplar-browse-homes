package com.poplarhomes.browsehomes.domain

import com.apollographql.apollo3.ApolloClient
import com.poplarhomes.browsehomes.GetBuildingsQuery
import com.poplarhomes.browsehomes.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBuildingsUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val apolloClient: ApolloClient
) {

    suspend fun execute(): List<GetBuildingsQuery.Building?> =
        withContext(dispatcher) {
            val buildings = apolloClient.query(GetBuildingsQuery()).execute().data?.buildings

            if (buildings.isNullOrEmpty()) emptyList()
            else buildings
        }

}
