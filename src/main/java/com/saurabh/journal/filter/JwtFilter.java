package com.saurabh.journal.filter;

import com.saurabh.journal.service.UserDetailServiceImpl;
import com.saurabh.journal.service.UserService;
import com.saurabh.journal.utilis.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Service
public class JwtFilter  extends OncePerRequestFilter {
     @Autowired
     private JwtUtil jwtUtil;
     @Autowired
     private UserDetailServiceImpl  userDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (token != null && token.startsWith("Bearer ")) {
          jwt = token.substring(7);
          username=jwtUtil.extractUsername(jwt);

        }
      if (username !=null){
          UserDetails userDetails=userDetailService.loadUserByUsername(username);
          if (jwtUtil.validateToken(jwt,userDetails.getUsername())){
              UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
              authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              SecurityContextHolder.getContext().setAuthentication(authenticationToken);

          }
      }
      filterChain.doFilter(request,response);
    }
}
//@Service
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private UserDetailServiceImpl userDetailService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String authHeader = request.getHeader("Authorization");
//        String jwt = null;
//        String username = null;
//
//        try {
//            if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                jwt = authHeader.substring(7); // remove "Bearer "
//                username = jwtUtil.extractUsername(jwt); // ⚠️ risky line
//            }
//
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//                UserDetails userDetails =
//                        userDetailService.loadUserByUsername(username);
//
//                if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
//
//                    UsernamePasswordAuthenticationToken authentication =
//                            new UsernamePasswordAuthenticationToken(
//                                    userDetails, null, userDetails.getAuthorities());
//
//                    authentication.setDetails(
//                            new WebAuthenticationDetailsSource().buildDetails(request));
//
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//
//        } catch (Exception e) {
//            // 🔥 VERY IMPORTANT
//            System.out.println("JWT validation failed: " + e.getMessage());
//            // Do NOT throw exception – allow request to continue as unauthenticated
//        }
//
//        filterChain.doFilter(request, response); hello saurabh jhyyy jj
//    }
//}