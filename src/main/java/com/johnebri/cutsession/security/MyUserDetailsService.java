package com.johnebri.cutsession.security;

import com.johnebri.cutsession.dao.UserDao;
import com.johnebri.cutsession.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author John on 12/2/22
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public MyUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByUsername(username);
        if(!user.isPresent()) {
            try {
                throw new Exception("User not found");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        User theUser = user.get();

        return new org.springframework.security.core.userdetails.User(theUser.getUsername(),
                theUser.getPassword(), new ArrayList<>());
    }
}
