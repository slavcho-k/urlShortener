package com.example.urlshortener.service

import com.example.urlshortener.domain.Url
import com.example.urlshortener.repository.UrlRepo
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Service
import java.security.MessageDigest

@Service
class UrlShortenerService(private val repository: UrlRepo) {

    fun shortenUrl(longUrl: String): String {
        val objectMapper = ObjectMapper()
        val url = objectMapper.readValue(longUrl, Url::class.java)
        val existingUrl = repository.searchByLongUrl(url.longUrl)

        return existingUrl?.shortUrl ?: run {
            val hashed = hash(longUrl)
            repository.save(Url(longUrl = url.longUrl, shortUrl = hashed))
            hashed
        }
    }

    private fun hash(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(input.toByteArray())
        val hexString = hash.joinToString("") { "%02x".format(it) }

        return hexString.substring(0, 6)
    }

    fun getLongUrl(shortUrl: String, response: HttpServletResponse) {
        val url = repository.searchByShortUrl(shortUrl)

        url?.let {
            response.sendRedirect(it.longUrl)
        } ?: response.sendError(HttpServletResponse.SC_NOT_FOUND)
    }

}

