package ru.itis.Filter;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.repositories.ClientRepositoryImpl;
import ru.itis.services.ClientServices;
import ru.itis.services.ClientServicesImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/home, /profile, /order, /missing"})
public class AuthFilter implements Filter {

    @Autowired
    ClientServices clientServices;

    @Override
    @SneakyThrows
    public void init(FilterConfig filterConfig) throws ServletException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Class.forName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/poterashka");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        Cookie cookies[] = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    if (clientServices.isExistByCookie(cookie.getValue())) {
                        filterChain.doFilter(request, response);
                        return;
                    }
                    break;
                }
            }
            response.sendRedirect("/signIn");
            return;
        }
        response.sendRedirect("/signIn");
        return;
    }

    @Override
    public void destroy() {

    }
}
