package com.jonatas.rbacapi.security;

import com.jonatas.rbacapi.domain.User;
import com.jonatas.rbacapi.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

        private final UserRepository userRepository;

        public CustomUserDetailsService(UserRepository userRepository) {
                this.userRepository = userRepository;
        }

        @Transactional
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

                var authorities = user.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                                .collect(Collectors.toSet());

                authorities.addAll(
                                user.getRoles().stream()
                                                .filter(role -> role.getPermissions() != null)
                                                .flatMap(role -> role.getPermissions().stream())
                                                .map(p -> new SimpleGrantedAuthority(p.getName()))
                                                .toList());

                return new org.springframework.security.core.userdetails.User(
                                user.getUsername(),
                                user.getPassword(),
                                authorities);
        }
}