package com.conexa.saude.api.desafiotecnicobackendconexa.config;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Medico;
import com.conexa.saude.api.desafiotecnicobackendconexa.repositories.MedicoRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private Date validAt;

    private Map<String, Date> BLACK_LIST_TOKENS = new HashMap<>();

    @Autowired
    private MedicoRepository medicoRepository;

    public String generateToken(String username) {
        validAt = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(validAt)
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public Date getExpiration() {
        return validAt;
    }

    public boolean validToken(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if (username != null && expirationDate != null && now.before(expirationDate)
                    && !BLACK_LIST_TOKENS.containsKey(token)) {
                return true;
            }
        }
        return false;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

    public void logout(String token) {
        killToken(token);
    }

    private void killToken(String token) {
        try {
            Date newDateExpiration = new Date(System.currentTimeMillis());
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            claims.getBody().setExpiration(newDateExpiration);

            cleanBlackListTokensExpired(newDateExpiration);
            BLACK_LIST_TOKENS.put(token, claims.getBody().getExpiration());
            log.info("Token bloqueado com sucesso!");

            log.info("Temos {} tokens bloqueados!", BLACK_LIST_TOKENS.size());
        } catch (Exception ex) {
            log.info("Erro ao expirar token");
        }
    }

    private void cleanBlackListTokensExpired(Date newDateExpiration) {
        BLACK_LIST_TOKENS.entrySet().removeIf(t -> t.getValue().before(newDateExpiration));
    }

    public Medico getUsuarioLogadoByToken(String token) {
        Claims claims = getClaims(token);
        Medico medico = null;
        if (claims != null) {
            String username = claims.getSubject();
            medico = medicoRepository.findByEmail(username);
        }

        return medico;
    }
}
