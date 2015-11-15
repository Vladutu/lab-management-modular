package com.iquestint.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is a controller that handles login and logout operations.
 *
 * @author Georgian Vladutu
 */
@Controller
@RequestMapping("/")
public class LoginController {

    /**
     * Redirects the user to the login method in this controller.
     *
     * @return String
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultRoute() {
        return "redirect:/login";
    }

    /**
     * Returns the login page.
     *
     * @return String
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    /**
     * This method logs out the user and redirects him/her to the login page.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return String
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        }
        else {
            userName = principal.toString();
        }
        return userName;
    }

}
