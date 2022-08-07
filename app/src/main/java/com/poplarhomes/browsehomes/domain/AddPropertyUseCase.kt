package com.poplarhomes.browsehomes.domain

import com.apollographql.apollo3.ApolloClient
import com.poplarhomes.browsehomes.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AddPropertyUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val apolloClient: ApolloClient
) {
}
