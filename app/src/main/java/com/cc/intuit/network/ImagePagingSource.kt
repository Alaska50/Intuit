package com.cc.intuit.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cc.intuit.models.ImageData
import retrofit2.HttpException
import java.io.IOException

class ImagePagingSource : PagingSource<Int, ImageData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageData> {
        val position = params.key ?: 1

        return try {
            val result = AuthorAPI.retrofitService.getData(position, params.loadSize)
            val nextKey = if (result.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = result,
                prevKey = if (position == 1) null else position -1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, ImageData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
           val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}