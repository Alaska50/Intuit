package com.cc.intuit

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.cc.intuit.models.ImageData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getData(viewModelScope: CoroutineScope): Flow<PagingData<ImageData>>
}