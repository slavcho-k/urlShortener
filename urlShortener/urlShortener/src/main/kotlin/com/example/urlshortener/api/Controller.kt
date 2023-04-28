package com.example.urlshortener.api

import com.example.urlshortener.service.UrlShortenerService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@RestController
class UrlController(private val service: UrlShortenerService) {

    @PostMapping("/shorten")
    fun shortenUrl(@RequestBody longUrl: String): ResponseEntity<String> {
        val shortUrl = service.shortenUrl(longUrl)
        return ResponseEntity.ok(shortUrl)
    }

    @GetMapping("redirect/{shortCode}")
    fun redirectToUrl(@PathVariable shortCode: String, response: HttpServletResponse) {
        return service.getLongUrl(shortCode, response)
    }
}
