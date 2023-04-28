package com.example.urlshortener.repository

import com.example.urlshortener.domain.Url
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UrlRepo : JpaRepository<Url, Long> {
    fun searchByShortUrl(code: String): Url?
    fun searchByLongUrl(url: String): Url?
}