package naero.naeroserver.member.controller;

import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.member.dto.ManageSearchDTO;
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
    public ResponseEntity<ResponseDTO> userDetail(@PathVariable Integer page){
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

    @GetMapping("/search/{page}")
    public ResponseEntity<ResponseDTO> userSearch(@PathVariable Integer page, @RequestBody ManageSearchDTO crit){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message",
                userService.getAllUserPage(page,SIZE)
        ));
    }
    @GetMapping("/withdraw/{username}")
    public ResponseEntity<ResponseDTO> withdraw(@PathVariable String username) {
        System.out.println("임의 유저 탈퇴 요청");
        userService.withdrawUser(username);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.ACCEPTED, "message", null));
    }

}
