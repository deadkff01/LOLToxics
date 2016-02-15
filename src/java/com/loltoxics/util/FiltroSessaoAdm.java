/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loltoxics.util;

import java.io.IOException;
import javax.faces.application.ResourceHandler;

import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author deadkff01
 */
@WebFilter("/toxiclogado/*")
public class FiltroSessaoAdm implements Filter {
    
    @Override
    public void init(FilterConfig config) throws ServletException {
      
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
//session.getAttribute("user")
        if (session == null || session.getAttribute("administrador") == null) {
            response.sendRedirect(request.getContextPath() + "/login.xhtml"); 
        } else {
            chain.doFilter(req, res); 
        }
    }

    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }
    
}
