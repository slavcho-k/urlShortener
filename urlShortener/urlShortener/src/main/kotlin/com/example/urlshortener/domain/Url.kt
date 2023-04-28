package com.example.urlshortener.domain

import jakarta.persistence.*

@Entity
@Table(name = "urls")
data class Url(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "long_url")
    val longUrl: String,

    @Column(name = "short_url")
    val shortUrl: String
)

