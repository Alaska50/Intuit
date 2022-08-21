package com.cc.intuit.ui

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.cc.intuit.Repository
import com.cc.intuit.RepositoryImpl
import com.cc.intuit.models.ImageData
import com.cc.intuit.network.ImagePagingSource
import com.cc.intuit.network.NETWORK_PAGE_SIZE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.onEmpty

class ViewModel : ViewModel() {

    /**
     * NOTE : can use a sealed class/enum to represent view states.
     * This can include a spinner.
     */

    private val repo: Repository = RepositoryImpl()

    var initialKey = 1

    private val _author = MutableLiveData<String>("?")
    val author: LiveData<String> = _author

    private val _image_url = MutableLiveData("")
    val image_url: LiveData<String> = _image_url


    fun setAuthor(author: String) {
        _author.value = author
    }
    fun setImageUrl(url: String) {
        _image_url.value = url
    }

    private var dataFlow: Flow<PagingData<ImageData>>? = null

    fun getData(): Flow<PagingData<ImageData>> {

        return if (dataFlow != null) {
            dataFlow as Flow<PagingData<ImageData>>
        } else {
            val data = repo.getData(viewModelScope)
            dataFlow = data

            data
        }

    }


}