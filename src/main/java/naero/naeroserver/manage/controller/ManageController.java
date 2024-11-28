package naero.naeroserver.manage.controller;

import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.member.dto.ManageSearchDTO;
import naero.naeroserver.member.dto.ProducerDTO;
import naero.naeroserver.member.dto.UserDTO;
import naero.naeroserver.member.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager")
public class ManageController {
    private final UserService userService;

    private final int SIZE = 20;
    public ManageController(UserService userService) {
        this.userService = userService;
    }

    //page 는 0부터 받음
    @GetMapping("/alluser/{page}")
    public ResponseEntity<ResponseDTO> allUserList(@PathVariable Integer page){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message",
                userService.getAllUserPage(page,SIZE)
        ));
    }

    //username으로 사업자 정보까지 가져와지면 사업자 정보로 리턴함 > 사업자의 detail 컨트롤러 필요 X
    @GetMapping("/detail/{username}")
    public ResponseEntity<ResponseDTO> userDetail(@PathVariable String username){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message",
                userService.findByusername(username)
        ));
    }

    @PostMapping("/update/user")
    public ResponseEntity<ResponseDTO> updateDetail(@RequestBody UserDTO user){

        System.out.println("수정요청");
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message",
                userService.updateDetail(user)));
    }

    @PostMapping("/search/user/{page}")
    public ResponseEntity<ResponseDTO> userSearch(@PathVariable Integer page, @RequestBody ManageSearchDTO crit){
        System.out.println("crit 확인"+ crit);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,
                userService.searchUserPage(page,SIZE , crit),
                userService.searchUser(page,SIZE , crit)
        ));
    }

    @GetMapping("/withdraw/user/{username}")
    public ResponseEntity<ResponseDTO> withdrawUser(@PathVariable String username) {
        System.out.println("임의 유저 탈퇴 요청");
        userService.withdrawUser(username);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.ACCEPTED, "message", null));
    }

//----------------------------

    @GetMapping("/allproducer/{page}")
    public ResponseEntity<ResponseDTO> allProducerList(@PathVariable Integer page){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message",
                userService.getAllProducerPage(page,SIZE)
        ));
    }

    //판매자 회원만 검색
    @PostMapping("/search/producer/{page}")
    public ResponseEntity<ResponseDTO> producerSearch(@PathVariable Integer page, @RequestBody ManageSearchDTO crit){
        System.out.println("crit 확인"+ crit);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, userService.searchProducerPage(page,SIZE , crit),
                userService.searchProducer(page,SIZE , crit)
        ));
    }

    //사업장 정지 요청
    @GetMapping("/withdraw/producer/{username}")
    public ResponseEntity<ResponseDTO> withdrawProducer(@PathVariable String username) {
        System.out.println("임의 판매자 탈퇴 요청");
        userService.withdrawProducer(username);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.ACCEPTED, "사업장 정지처리 완료되었습니다.", null));
    }

    @PostMapping("/update/producer/{username}")
    public ResponseEntity<ResponseDTO> updateProducerDetail(@RequestBody ProducerDTO producer, @PathVariable String username){

        System.out.println("판매자 정보 수정");
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message",
                userService.updateProducerDetail(producer, username)));
    }

}
