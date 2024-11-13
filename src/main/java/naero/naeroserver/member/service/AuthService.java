package naero.naeroserver.member.service;

import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.exception.DuplicatedMemberEmailException;
import naero.naeroserver.exception.DuplicatedUsernameException;
import naero.naeroserver.exception.LoginFailedException;
import naero.naeroserver.jwt.TokenDTO;
import naero.naeroserver.jwt.TokenProvider;
import naero.naeroserver.member.dto.UserDTO;
import naero.naeroserver.member.repository.UserRepository;
import naero.naeroserver.member.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userDao;
    private final UserRoleRepository userRoleRepository;
    private final TokenProvider tokenProvider;
    private final ModelMapper modelMapper;

    public AuthService(PasswordEncoder passwordEncoder,
                       UserRepository userDao, UserRoleRepository userRoleRepository,
                       TokenProvider tokenProvider, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.userRoleRepository = userRoleRepository;
        this.tokenProvider = tokenProvider;
        this.modelMapper = modelMapper;
    }

    public Object signin(UserDTO user) {
        System.out.println(user);
        TblUser getuser =  userDao.findByUsername(user.getUsername());
        if (getuser == null){
            System.out.println("아이디 오류");
            throw new LoginFailedException("가입하지 않은 사용자 입니다.");
        }
        if (getuser.getWithStatus().equals("Y")){
            System.out.println("탈퇴한 사용자 오류");
            throw new LoginFailedException("탈퇴한 사용자 계정입니다.");
        }

        //플레인 텍스트 임시 로직 확인
        /*if (!getuser.getPassword().equals(user.getPassword())){
            System.out.println("비밀번호 오류");
            throw new LoginFailedException("비밀번호가 일지하지 않습니다.");
        }*/

        if(!passwordEncoder.matches(user.getPassword(), getuser.getPassword())){
            System.out.println("비밀번호 오류");
            throw new LoginFailedException("비밀번호가 일지하지 않습니다.");
        }

        //인증 토큰 발급
        return tokenProvider.generateTokenDTO(getuser);
    }

    public Object signup(UserDTO user) {

        return null;
    }

    public void dupulicateIdCheck(String username) {
        if(userDao.existsByUsername(username)){
            throw new DuplicatedUsernameException("중복된 아이디가 존재합니다.");
        }
    }


    public void dupulicateEmailCheck(String email) {
        if(userDao.existsByUserEmail(email)){
            throw new DuplicatedMemberEmailException("중복된 이메일이 존재합니다.");
        }
    }

}
