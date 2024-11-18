package naero.naeroserver.member.service;

import jakarta.transaction.Transactional;
import naero.naeroserver.entity.user.TblProducer;
import naero.naeroserver.entity.user.TblProducerGrade;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.exception.LoginFailedException;
import naero.naeroserver.exception.UpdateUserException;
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
    private final PasswordEncoder passwordEncoder;
    private final UserGradeRepository userGradeRepository;
    private final SearchRepository searchRepository;
    private final ProducerRepository producerRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserGradeRepository userGradeRepository, SearchRepository searchRepository, ProducerRepository producerRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userGradeRepository = userGradeRepository;
        this.searchRepository = searchRepository;
        this.producerRepository = producerRepository;
    }

    public Object findByusername(String username) {

        UserDTO result =  modelMapper.map( userRepository.findByUsername(username) , UserDTO.class);
        result.setGrade(modelMapper.map(userGradeRepository.findByGradeId(result.getGrade().getGradeId()), UserGradeDTO.class));
        return result;
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

        // 전체 데이터 개수 계산 (총 페이지 수나 총 항목 수를 구할 때 사용)
        String countQuery = "SELECT COUNT(*) FROM your_table WHERE 1=1";
        // countQuery에서 filter 및 keyword 조건을 적용해서 전체 데이터 수를 구할 수 있습니다.
        int totalCount = searchRepository.getTotalCount(crit);

        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalCount / size);

        // 결과 반환
        Map<String, Object> response = new HashMap<>();
        response.put("data", result);
        response.put("totalCount", totalCount);
        response.put("totalPages", totalPages);
        response.put("currentPage", page);

        System.out.println(response);
        return result;
    }

    public  Map<String, Object> searchProducer(Integer page, int size, ManageSearchDTO crit) {
        List<Object[]> result = searchRepository.searchProducer(crit, page, size);

        // 전체 데이터 개수 계산 (총 페이지 수나 총 항목 수를 구할 때 사용)
        String countQuery = "SELECT COUNT(*) FROM your_table WHERE 1=1";
        int totalCount = searchRepository.getTotalCount(crit);

        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalCount / size);

        // 결과 반환
        Map<String, Object> response = new HashMap<>();
        response.put("data", result);
        response.put("totalCount", totalCount);
        response.put("totalPages", totalPages);
        response.put("currentPage", page);

        System.out.println(response);
        return response;
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
}
