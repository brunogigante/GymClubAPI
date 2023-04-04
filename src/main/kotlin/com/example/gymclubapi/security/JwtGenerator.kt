package com.example.gymclubapi.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtGenerator {
    fun generateToken(authentication: Authentication): String {
        val username = authentication.name
        val currentDate = Date()
        val expireDate = Date(currentDate.time + SecurityConstants().JWT_EXPIRATION)

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS512, SecurityConstants().JWT_SECRET)
            .compact()
    }

    fun getEmailFromJwt(token: String): String {
        val claims: Claims = Jwts.parser()
            .setSigningKey(SecurityConstants().JWT_SECRET)
            .parseClaimsJwt(token)
            .body
        return claims.subject
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(SecurityConstants().JWT_SECRET).parseClaimsJwt(token)
            return true
        }
        catch (ex: Exception){
            throw AuthenticationCredentialsNotFoundException("Jwt was expired or incorrect!")
        }
    }
}