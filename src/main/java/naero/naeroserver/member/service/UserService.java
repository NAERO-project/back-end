package naero.naeroserver.member.service;

import jakarta.transaction.Transactional;
import naero.naeroserver.entity.user.TblProducer;
import naero.naeroserver.entity.user.TblProducerGrade;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.exception.DuplicatedUsernameException;
import naero.naeroserver.exception.LoginFailedException;
import naero.naeroserver.exception.UpdateUserException;
import naero.naeroserver.jwt.TokenProvider;
import naero.naeroserver.manage.DTO.ManageUserDTO;
import naero.naeroserver.member.dto.ManageSearchDTO;
import naero.naeroserver.member.dto.ProducerDTO;
import naero.naeroserver.member.dto.UserDTO;
import naero.naeroserver.member.dto.UserGradeDTO;
import naero.naeroserver.manage.repository.SearchRepository;
import naero.naeroserver.member.repository.ProducerRepository;
import naero.naeroserver.member.repository.UserGradeRepository;
import naero.naeroserver.member.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final SearchRepository searchRepository;
    private final ProducerRepository producerRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, SearchRepository searchRepository, ProducerRepository producerRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.searchRepository = searchRepository;
        this.producerRepository = producerRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public Object findByusername(String username) {
        UserDTO result =  modelMapper.map(userRepository.findByUsername(username) , UserDTO.class);
        if(producerRepository.existsById(result.getUserId())){
            return modelMapper.map(producerRepository.findByProducerId(result.getUserId()), ProducerDTO.class);
        }
//        result.setGrade(modelMapper.map(userGradeRepository.findByGradeId(result.getGrade().getGradeId()), UserGradeDTO.class));
        return result;
    }

    public Object findProducerByusername(String username) {

        TblUser getUser =  userRepository.findByUsername(username);
        TblProducer result = producerRepository.findByProducerId(getUser.getUserId());
        if (result == null){
            throw new LoginFailedException("사업자 등록이 되지 않은 사용자입니다.");
        }
        return modelMapper.map( result, ProducerDTO.class);
    }

/*    @Transactional
    public Object updateUserDetail(UserDTO user){
        return userRepository.updateByUsername(modelMapper.map(user, TblUser.class));
    }*/

    @Transactional
    public Object withdrawUser(String username) {

        TblUser getUser = userRepository.findByUsername(username);
            if (getUser == null) {
                throw new UpdateUserException("사용자를 찾을 수 없습니다.");
            }
        getUser.setWithStatus("Y");

        return modelMapper.map(getUser, UserDTO.class);

    }

    @Transactional
    public Object updateDetail(UserDTO user) {
        TblUser getUser = userRepository.findByUsername(user.getUsername());
        if (getUser == null) {
            throw new UpdateUserException("사용자를 찾을 수 없습니다.");
        }

        if (user.getUserFullName() != null) {
            getUser.setUserFullname(user.getUserFullName());
        }
        if (user.getUserPhone() != null) {
            getUser.setUserPhone(user.getUserPhone());
        }
        if(user.getPassword()!=null){
            getUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return modelMapper.map(getUser, UserDTO.class);

    }

    public Page<UserDTO> getAllUserPage(Integer page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("userID").ascending());
        Page<TblUser> usersPage =userRepository.findAll(pageable);
        List<TblUser> users = usersPage.getContent();
        for(TblUser user : users){
            System.out.println(user);
        }
        System.out.println("Users on this page: " + users);
        return usersPage.map(user -> modelMapper.map(user, UserDTO.class));
    }

    public List<ManageUserDTO> searchUser(Integer page, int size, ManageSearchDTO crit) {
        List<ManageUserDTO> result = searchRepository.searchUser(crit, page, size);

        return result;
    }

    public String searchUserPage(Integer page, int size, ManageSearchDTO crit){
        // 전체 페이지 수 계산 < 동시 반환이 안됨. 따로 호출
        int totalCount = searchRepository.getTotalCount(crit);
        int totalPages = (int) Math.ceil((double) totalCount / size);

        return  (page+1)+"/"+totalPages;
    }

    public   List<ManageUserDTO> searchProducer(Integer page, int size, ManageSearchDTO crit) {
        List<ManageUserDTO> result = searchRepository.searchProducer(crit, page, size);

        return result;
    }

    public String searchProducerPage(Integer page, int size, ManageSearchDTO crit){
        // 전체 페이지 수 계산 < 동시 반환이 안됨. 따로 호출
        int totalCount = searchRepository.getTotalCount(crit);
        int totalPages = (int) Math.ceil((double) totalCount / size);

        return  (page+1)+"/"+totalPages;
    }

    public Object findByUserEmail(String email) {
        TblUser getUser = userRepository.findByUserEmail(email);
        if (getUser !=null)
            return getUser.getUsername();

        throw  new LoginFailedException("해당 이메일로 가입한 회원이 없습니다.");
    }

    public Page<ProducerDTO> getAllProducerPage(Integer page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("producerId").ascending());
        Page<TblProducer> usersPage =producerRepository.findAll(pageable);
        List<TblProducer> users = usersPage.getContent();
        System.out.println("Users on this page: " + users);
        return usersPage.map(user -> modelMapper.map(user, ProducerDTO.class));
    }

    @Transactional
    public void withdrawProducer(String username) {

        int userId = userRepository.findByUsername(username).getUserId();
        TblProducer getProducer = producerRepository.findByProducerId(userId);
        if (getProducer == null) {
            throw new UpdateUserException("사용자를 찾을 수 없습니다.");
        }
        getProducer.setWithStatus("Y");

    }

    //유저와 관리자 공용으로 사용할 것
    @Transactional
    public Object updateProducerDetail(ProducerDTO producer, String username) {
        //사용자 정보중에 바꿀 수 있는 데이터...
        //busi_no producer_add producer_name producer_phone delivery_fee delivery_crit
        int userId = userRepository.findByUsername(username).getUserId();
        TblProducer getUser = producerRepository.findByProducerId(userId);

        if (getUser == null) {
            throw new UpdateUserException("사용자를 찾을 수 없습니다.");
        }

        if (producer.getProducerName() != null) {
            getUser.setProducerName(producer.getProducerName());
        }
        if (producer.getProducerPhone() != null) {
            getUser.setProducerPhone(producer.getProducerPhone());
        }
        if (producer.getDeliveryFee() != null) {
            getUser.setDeliveryFee(producer.getDeliveryFee());
        }
        if (producer.getDeliveryCrit() != null) {
            getUser.setDeliveryCrit(producer.getDeliveryCrit());
        }
        if (producer.getProducerAdd() != null) {
            getUser.setProducerAdd(producer.getProducerAdd());
        }
        if (producer.getBusiNo() != null) {
            getUser.setBusiNo(producer.getBusiNo());
        }

        return modelMapper.map(getUser, ProducerDTO.class);
    }

    @Transactional
    public Object convertToProducer(ProducerDTO producer, String username) {
        TblUser getUser = userRepository.findByUsername(username);
        int userId = getUser.getUserId();

        if(producerRepository.existsById(userId)){
            throw new DuplicatedUsernameException("같은 아이디로 가입된 판매자가 있습니다.");
        }

        producerRepository.insertProducer(userId);
        updateProducerDetail(producer,getUser.getUsername());
        System.out.println(producerRepository.findByProducerId(userId));

        return tokenProvider.generateTokenDTO(userRepository.findById(userId));
    }

}
