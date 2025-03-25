package com.elewa.si_ware_task.core.data.remote

import com.elewa.si_ware_task.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val newRequest: Request = originalRequest.newBuilder()
            .addHeader("x-api-key", BuildConfig.API_Key)
            .build()
        return chain.proceed(newRequest)
    }
}