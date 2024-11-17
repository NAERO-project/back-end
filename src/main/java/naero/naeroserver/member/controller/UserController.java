package naero.naeroserver.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.member.dto.UserDTO;
import naero.naeroserver.member.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static naero.naeroserver.jwt.JwtFilter.AUTHORIZATION_HEADER;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    /*("")
        public ResponseEntity<ResponseDTO> name(@RequestBody Object var){
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message", "data"));
        }*/

    //
    @GetMapping("/detail/{username}")
    public ResponseEntity<ResponseDTO> userDetail(@PathVariable String username){
        //프런트와 연동 후, 회원 탈퇴와 같은 방식을 취할 예정
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message",
                userService.findByusername(username)
                ));
    }
    
    //탈퇴
    @GetMapping("/withdraw/{username}")
    public ResponseEntity<ResponseDTO> withdraw(@PathVariable String username) {
        System.out.println("유저의 회원 탈퇴 요청");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//         인증된 사용자명?
        String authenticatedUsername = authentication.getName();

        // 요청한 사용자 이름과 JWT의 sub 비교
        if (!authenticatedUsername.equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseDTO(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.", null));
        }userService.withdrawUser(username);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.ACCEPTED, "message", null));
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDTO> updateDetail(@RequestBody UserDTO user){

        System.out.println("수정요청");
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message",
                userService.updateDetail(user)));
    }

  /*  @GetMapping("/detail")
    public ResponseEntity<ResponseDTO> name(@RequestBody Object var){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"message", "data"));
    }*/
}
