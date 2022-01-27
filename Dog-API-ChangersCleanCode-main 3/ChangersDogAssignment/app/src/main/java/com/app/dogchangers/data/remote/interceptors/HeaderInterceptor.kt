package com.app.dogchangers.data.remote.interceptors

import com.app.dogchangers.domain.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()
        builder.header("Accept", "application/json")
        builder.header("Content-Type", "application/json")
        builder.header("x-api-key", Constants.X_API_KEY)

        return chain.proceed(builder.build())
    }

}