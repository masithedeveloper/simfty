package com.simfyafrica.assessment.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.simfyafrica.assessment.BuildConfig
import com.simfyafrica.assessment.data.network.WebService
import dagger.Module
import dagger.Provides

import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val READ_TIME_OUT = 20000L
const val WRITE_TIME_OUT = 20000L
const val CONNECT_TIME_OUT = 20000L
const val CACHE_SIZE = 20 * 1024 * 1024L //20 MiB
const val API_BASE_URL = "https://api.thecatapi.com/v1/images/"

@Module(includes = [ContextModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor, httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache)
            : OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) builder.addInterceptor(httpLoggingInterceptor)
        builder
            .cache(cache)
            .addInterceptor(interceptor)
            .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideHttpInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("x-api-key", "565d3701-3347-40fd-8e7b-efa5386b221c")
                .build()
            return@Interceptor it.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideWebService(retrofit: Retrofit): WebService {
        return retrofit.create(WebService::class.java)
    }
}