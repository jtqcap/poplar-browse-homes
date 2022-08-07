package com.poplarhomes.browsehomes.domain

import com.apollographql.apollo3.ApolloClient
import com.poplarhomes.browsehomes.AddBuildingMutation
import com.poplarhomes.browsehomes.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddPropertyUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val apolloClient: ApolloClient
) {

    suspend fun execute(
        address: String,
        baths: Int,
        beds: Int,
        rent: Double,
    ) = withContext(dispatcher) {
        apolloClient
            .mutation(
                AddBuildingMutation(address, baths, beds, rent)
            )
            .execute()
    }

}
