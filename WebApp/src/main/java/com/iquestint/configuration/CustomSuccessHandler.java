package com.iquestint.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class is a handler that redirect an user after login depending on his/her role.
 *
 * @author Georgian Vladutu
 */
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * This method extracts the roles of currently logged-in user and returns
     * appropriate URL according to his/her role.
     *
     * @param authentication Authentication
     * @return String
     */
    protected String determineTargetUrl(Authentication authentication) {
        String url = "";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = new ArrayList<String>();

        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        if (isAdmin(roles)) {
            url = "/admin/students/";
        }
        else if (isProfessor(roles)) {
            url = "/professor/home/";
        }
        else if (isStudent(roles)) {
            url = "/student/home/";
        }

        return url;
    }

    private boolean isAdmin(List<String> roles) {
        return roles.contains("ROLE_ADMIN");
    }

    private boolean isProfessor(List<String> roles) {
        return roles.contains("ROLE_PROFESSOR");
    }

    private boolean isStudent(List<String> roles) {
        return roles.contains("ROLE_STUDENT");
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
