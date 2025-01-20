package com.gabrielspassos.factory

import com.gabrielspassos.client.CountryClient
import com.google.gson.Gson
import java.net.Socket
import java.net.http.HttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLEngine
import javax.net.ssl.TrustManager
import javax.net.ssl.X509ExtendedTrustManager

object BeanFactory {

    private val trustManager: TrustManager = object: X509ExtendedTrustManager() {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?, engine: SSLEngine?) { }
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) { }
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?, socket: Socket?) { }
        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?, engine: SSLEngine?) { }
        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) { }
        override fun getAcceptedIssuers(): Array<X509Certificate> { return arrayOf() }
        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?, socket: Socket?) { }
    }

    fun buildCountryClient(): CountryClient {
        return CountryClient(buildHttpClient(), buildGsonMapper())
    }

    private fun buildHttpClient(): HttpClient {
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf(trustManager), SecureRandom())

        return HttpClient.newBuilder().sslContext(sslContext).build()
    }

    private fun buildGsonMapper(): Gson {
        return Gson()
    }
}