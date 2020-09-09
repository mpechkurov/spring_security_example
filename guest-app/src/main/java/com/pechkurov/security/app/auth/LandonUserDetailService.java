package com.pechkurov.security.app.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LandonUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public LandonUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("cannot find username: " + username);
        }
        return new LandonUserPrincipal(user);
    }
}
