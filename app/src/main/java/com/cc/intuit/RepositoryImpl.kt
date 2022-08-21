package com.cc.intuit

import androidx.paging.*
import com.cc.intuit.models.ImageData
import com.cc.intuit.network.ImagePagingSource
import com.cc.intuit.network.NETWORK_PAGE_SIZE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class RepositoryImpl: Repository {

    override fun getData(viewModelScope: CoroutineScope): Flow<PagingData<ImageData>> {
        val data = Pager(
            PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                maxSize = 60,
                enablePlaceholders = false)
        ) {
            ImagePagingSource()
        }.flow
            .cachedIn(viewModelScope)

        return data
    }
}