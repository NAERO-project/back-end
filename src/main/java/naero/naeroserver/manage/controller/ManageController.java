package naero.naeroserver.manage.controller;

import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.member.dto.ManageSearchDTO;
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

    @GetMapping("/detail/{username}")
    public ResponseEntity<ResponseDTO> userDetail(@PathVariable String username){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message",
                userService.findByusername(username)
        ));
    }
    //판매자 등록이 되어있는지 확인
    @GetMapping("/check/producer/{username}")
    public ResponseEntity<ResponseDTO> check(@PathVariable String username){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message",
                userService.findByusername(username)
        ));
    }
    
    @PostMapping("/update")
    public ResponseEntity<ResponseDTO> updateDetail(@RequestBody UserDTO user){

        System.out.println("수정요청");
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message",
                userService.updateDetail(user)));
    }

    @GetMapping("/search/user/{page}")
    public ResponseEntity<ResponseDTO> userSearch(@PathVariable Integer page, @RequestBody ManageSearchDTO crit){
        System.out.println("crit 확인"+ crit);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message",
                userService.searchUser(page,SIZE , crit)
        ));
    }

    @GetMapping("/withdraw/{username}")
    public ResponseEntity<ResponseDTO> withdraw(@PathVariable String username) {
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

    //판매자 회원 정보
    @GetMapping("/detail/producer/{username}")
    public ResponseEntity<ResponseDTO> producerDetail(@PathVariable String username) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "message",
                userService.findByusername(username)
        ));
    }

    //판매자 회원만 검색
    @GetMapping("/search/producer/{page}")
    public ResponseEntity<ResponseDTO> producerSearch(@PathVariable Integer page, @RequestBody ManageSearchDTO crit){
        System.out.println("crit 확인"+ crit);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message",
                userService.searchProducer(page,SIZE , crit)
        ));
    }


    /* //producer 에 따로 컬럼 추가할 수도 있음
    @GetMapping("/withdraw/{username}")
    public ResponseEntity<ResponseDTO> withdraw(@PathVariable String username) {
        System.out.println("임의 유저 탈퇴 요청");
        userService.withdrawUser(username);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.ACCEPTED, "message", null));
    }
*/

}
