package ru.ifmo.springsecurity;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Собственная реализация AuthenticationManager.
 * Аутентификация считается успешной, если пароль идентичен логину.
 */
class SampleAuthenticationManager implements AuthenticationManager {

    // Список ролей
    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<>();

    static {
        // Добавляем роль для пользователя
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        if (auth.getName().equals(auth.getCredentials())) {
            return new UsernamePasswordAuthenticationToken(auth.getName(),
                    auth.getCredentials(), AUTHORITIES);
        }
        throw new BadCredentialsException("Bad Credentials");
    }
}
