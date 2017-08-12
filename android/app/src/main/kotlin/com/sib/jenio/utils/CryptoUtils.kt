package com.sib.jenio.utils

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * Created by itock on 8/12/2017.
 */
object CryptoUtils {
    fun b64e(bytes: ByteArray): String {
        return String(Base64.encode(bytes, Base64.DEFAULT))
    }

    fun b64d(string: String): ByteArray {
        return Base64.decode(string, Base64.DEFAULT)
    }

    fun generateSecret(size: Int): ByteArray {
        val secureRandom = SecureRandom.getInstance("SHA1PRNG")

        val secret = ByteArray(size)
        secureRandom.nextBytes(secret)

        return secret
    }

    fun computeHmacDigest(secret: ByteArray, bytes: ByteArray): ByteArray {
        val hmac = Mac.getInstance("HmacSHA256")
        hmac.init(SecretKeySpec(secret, "HmacSHA256"))

        return hmac.doFinal(bytes)
    }

    fun computeTokenResponse(secret: ByteArray, tokenString: String, userId: String): ByteArray {
        val token = tokenString.toByteArray().slice(0..5).toByteArray()
        val padding = generateSecret(4)
        val tokenWithPadding = token + padding

        val tokenResponse = computeHmacDigest(secret, tokenWithPadding)
        val tokenResponseWithPadding = tokenResponse + padding

        val finalResponse = tokenResponseWithPadding + userId.toByteArray()

        return finalResponse
    }
}