package naero.naeroserver.member.service;

import jakarta.transaction.Transactional;
import naero.naeroserver.entity.auth.TblUserRole;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.member.dto.UserDTO;
import naero.naeroserver.member.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TblUser user = userRepository.findByUsername(username);
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);


        List<GrantedAuthority> authorities = new ArrayList<>();

        for(TblUserRole userRole : user.getTblUserRoles()) {
            String authorityName = userRole.getRole().getRoleName();
            authorities.add(new SimpleGrantedAuthority(authorityName));
        }
        userDTO.setAuthorities(authorities);
        return userDTO;
    }
}
