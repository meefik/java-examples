package ru.ifmo.springsecurity;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Совет для проверки доступа к методам.
 */
public class BeforeAdvice implements MethodBeforeAdvice {

    private boolean isAccess(String role) {
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        boolean access = false;
        for (GrantedAuthority auth : au.getAuthorities()) {
            if (role.equals(auth.getAuthority())) {
                access = true;
                break;
            }
        }
        return access;
    }

    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable {

        if (method.getName().equals("getUserMessage")) {
            if (!isAccess("ROLE_USER")) {
                throw new SecurityException("Access denied: " + method.getName());
            }
        }
        if (method.getName().equals("getAdminMessage")) {
            if (!isAccess("ROLE_ADMIN")) {
                throw new SecurityException("Access denied: " + method.getName());
            }
        }
    }
}
