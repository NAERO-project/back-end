package naero.naeroserver.member.service;

import jakarta.transaction.Transactional;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.member.dto.UserDTO;
import naero.naeroserver.member.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Object findByusername(String username) {
        return userRepository.findByUsername(username);
    }

/*    @Transactional
    public Object updateUserDetail(UserDTO user){
        return userRepository.updateByUsername(modelMapper.map(user, TblUser.class));
    }*/

    @Transactional
    public Object withdrawUser(String username) {
        TblUser getUser = userRepository.findByUsername(username);

        getUser.setWithStatus("Y");

        return null;
    }
}
