package com.spm.androidtesting.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        // DONT INCLUDE API KEYS IN YOUR SOURCE CODE
        val url =
            req.url.newBuilder().addQueryParameter("api_key", "y5nWAAK3Ou4OgGu5VZy4rhrQi4TTeluR")
                .build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}