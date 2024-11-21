package naero.naeroserver.member.service;

import jakarta.transaction.Transactional;
import naero.naeroserver.auth.DTO.AuthDTO;
import naero.naeroserver.auth.EmailApi;
import naero.naeroserver.auth.repository.AuthRepository;
import naero.naeroserver.entity.auth.TblAuth;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.exception.*;
import naero.naeroserver.jwt.TokenProvider;
import naero.naeroserver.member.dto.UserDTO;
import naero.naeroserver.member.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userDao;
    private final AuthRepository authRepository;
    private final TokenProvider tokenProvider;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userDao, AuthRepository authRepository, TokenProvider tokenProvider, ModelMapper modelMapper, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.authRepository = authRepository;
        this.tokenProvider = tokenProvider;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
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

    @Transactional
    public Object signup(UserDTO user, int authId) {
        dupulicateIdCheck(user.getUsername());
        authSuccessCheck(authId);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        TblUser newUser = modelMapper.map(user, TblUser.class);

        TblUser result = userRepository.save(newUser);


        return result;
    }

    private void authSuccessCheck(int authId) {
        TblAuth getAuth =authRepository.findByAuthId(authId);
        if (getAuth==null || getAuth.getAuthStatus().equals("N")){
            throw new LoginFailedException("인증 정보가 잘못되었습니다.");
        }

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

    @Transactional
    public UserDTO resetPassword(UserDTO user) {
        TblUser getuser =  userDao.findByUsername(user.getUsername());

        if(!getuser.getUserEmail().equals( user.getUserEmail())){
            throw new AuthFailException("전송한 정보가 올바르지 않습니다.");
        }

        if(passwordEncoder.matches(user.getPassword(), getuser.getPassword())){
            System.out.println("비밀번호 오류");
            throw new UpdateUserException("원래의 비밀번호와 동일한 번호로 바꿀 수 없습니다.");
        }
        getuser.setPassword(passwordEncoder.encode(user.getPassword()));

        return modelMapper.map( getuser, UserDTO.class);
    }

    @Transactional
    public TblAuth sendAuthEmail(String id, String key ,String email) {
        AuthDTO newAuth = EmailApi.sendAuthEmail(id, key, email);

        TblAuth savedAuth= authRepository.save(modelMapper.map(newAuth, TblAuth.class));
        System.out.println("savedAuth"+ savedAuth);
        return savedAuth;
    }


    @Transactional
    public Object checkAuthEmail(AuthDTO auth) {
        TblAuth getAuth = authRepository.findByAuthId(auth.getAuthId());
        LocalDateTime now = LocalDateTime.now();
        if(now.isAfter(ChronoLocalDateTime.from(getAuth.getEndTime()))){
            throw new AuthFailException("만료된 인증입니다. 인증을 다시 시도해주세요.");
        }
        if(!getAuth.getAuthKey().equals( auth.getAuthKey())){
            throw new AuthFailException("인증번호가 일치하지 않습니다. 다시 시도해주세요.");
        }

        getAuth.setAuthStatus("Y");
        return getAuth;
    }
}
