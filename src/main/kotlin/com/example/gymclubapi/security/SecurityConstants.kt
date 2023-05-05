package com.example.gymclubapi.security

import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys.secretKeyFor
import javax.crypto.SecretKey


object SecurityConstants {
    const val JWT_EXPIRATION = 7000000
    val JWT_SECRET: SecretKey = secretKeyFor(SignatureAlgorithm.HS512)
}
