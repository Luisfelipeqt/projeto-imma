package br.com.projectcedro.backend.projectcedro.config.security;

import br.com.projectcedro.backend.projectcedro.entities.Usuarios;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    // algumas constantes utilitarias
    private static final String EMISSOR = "ProjetoCedro";
    private static final String TOKEN_HEADER = "Bearer ";
    private static final String TOKEN_KEY = "01234567890123456789012345678901";
    private static final long UMA_SEMANA = 604800000L;
    private static final long UM_MINUTO = 60 * UMA_SEMANA;

    public static AuthToken encodeToken(Usuarios usuario){
        Key secretKey = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes());
        String tokenJWT = Jwts.builder().setSubject(usuario.getLogin())
                .setIssuer(EMISSOR)
                .setExpiration(new Date(System.currentTimeMillis() + UM_MINUTO))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        AuthToken token = new AuthToken(TOKEN_HEADER + tokenJWT);
        return token;
    }

    public static Authentication decodeToken(HttpServletRequest request){
        try {
            String jwtToken = request.getHeader("Authorization");
            jwtToken = jwtToken.replace(TOKEN_HEADER, "");

            // Fazendo o "parse" do token
            Jws<Claims> jwtClaims = Jwts.parserBuilder()
                    .setSigningKey(TOKEN_KEY.getBytes())
                    .build()
                    .parseClaimsJws(jwtToken);
            // Começando a extrair as informações
            String usuario = jwtClaims.getBody().getSubject();
            String emissor = jwtClaims.getBody().getIssuer();
            Date validade = jwtClaims.getBody().getExpiration();

            if (usuario.length() > 0 && emissor.equals(EMISSOR) && validade.after(new Date(System.currentTimeMillis()))) {
                // caso a requisição tenha cabeçalho correto eu gero um "token" interno com as informações relevantes
                return new UsernamePasswordAuthenticationToken(
                        "user", null, Collections.emptyList());
            }
        }
        catch(Exception e){
            System.out.println("DEBUG - ERRO ao decodificar TOKEN" + e.getMessage());
        }

        return null;
    }
}
