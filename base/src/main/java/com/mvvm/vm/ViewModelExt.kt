package com.mvvm.vm

import androidx.lifecycle.viewModelScope
import com.network.INetResponse
import com.network.NeworkConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * params:
 * block 网络请求函数
 * success 请求成功回调
 * failure 请求失败回调
 * showLoading loading显示开关（默认打开）
 * showToast 请求异常toast开关（默认打开）
 */
fun <T> BaseViewModel.request(
        block: suspend () -> INetResponse<T>?,
        success: (T?) -> Unit,
        failure: (Throwable) -> Unit = {},
        showLoading: Boolean = false,
        showToast: Boolean = false) {

    viewModelScope.launch(Dispatchers.Main) {
        if (showLoading) this@request.showLoading()
        runCatching {
            withContext(Dispatchers.IO) { block() }
        }.onSuccess { iNetResponse ->
            iNetResponse?.let {
                if (it.errorCode == NeworkConstant.INET_REQUEST_SUCCESS) {
                    success(it.data)
                } else if (showToast) {
                    this@request.showToast(it.errorMsg)
                }
            }
            if (showLoading) this@request.hideLoading()
        }.onFailure {
            failure(it)
            if (showLoading) this@request.hideLoading()
        }
    }
}