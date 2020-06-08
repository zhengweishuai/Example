package com.mvvm.vm

import androidx.lifecycle.viewModelScope
import com.network.INetResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


fun <T> BaseViewModel.request(
        block: suspend () -> INetResponse<T>,
        success: (T) -> Unit,
        failure: (Throwable) -> Unit = {}) {

    viewModelScope.launch(Dispatchers.Main) {
        this@request.showLoading()
        runCatching {
            withContext(Dispatchers.IO) { block() }
        }.onSuccess {
            success(it.data)
            this@request.hideLoading()
        }.onFailure {
            failure(it)
            this@request.hideLoading()
        }
    }
}