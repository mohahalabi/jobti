package ch.supsi.highway.jobti.config;

import ch.supsi.highway.jobti.model.User;
import ch.supsi.highway.jobti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userService.findById(Integer.parseInt(id));
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> auth = AuthorityUtils.createAuthorityList (user.getRole().getRoleName());
        return new org.springframework.security.core.userdetails.User(id+"", user.getPassword(), true, true, true, true, auth);
    }
}
