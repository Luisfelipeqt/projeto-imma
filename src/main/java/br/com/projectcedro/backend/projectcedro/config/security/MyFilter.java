package br.com.projectcedro.backend.projectcedro.config.security;

import br.com.projectcedro.backend.projectcedro.config.security.exceptions.ErroDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class MyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getHeader("Authorization") != null){
            // recupera o cabeçalho
            System.out.println(request.getHeader("Authorization"));
            Authentication auth = TokenUtil.decodeToken(request);
            // cabeçalho de autorização existe, preciso ver se é válido
            if(auth != null){
                // se o meu "token" for valida eu passo a requisição pra frente, indicando que ela está autenticada
                SecurityContextHolder.getContext().setAuthentication(auth);

            }
            else{
                // token existe, mas não é valido, precisa customizar a mensagem de erro(senão da erro 403 sem mensagem)
                System.out.println("Erro no token");
                ErroDTO erro = new ErroDTO(401, "Usuário não autorizado para este sistema.");
                response.setStatus(erro.getStatus());
                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().print(mapper.writeValueAsString(erro));
                response.getWriter().flush();
                return;
            }
        }


        // Passa a requisição pra frente
        filterChain.doFilter(request, response);
    }
}
