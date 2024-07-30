package my.vehicle.management.sapmle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import my.vehicle.management.sapmle.dao.UserRepository;
import my.vehicle.management.sapmle.entity.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user) {
        user.setPassword(user.getPassword());
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    public User getUserLogin(String username) throws UsernameNotFoundException {
        User theUser = userRepository.findByUsername(username);
        if (theUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(theUser.getUsername())
//                .password(theUser.getPassword())
//                //.roles(theUser.getRoles())
//                .build();
        return theUser;
        
    }

}
