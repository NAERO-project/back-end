package naero.naeroserver.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.member.dto.UserDTO;
import naero.naeroserver.member.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "로그인 인증", tags = {"AuthController"})

    @PostMapping("/signin")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO user){
        System.out.println(user);
        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "로그인 성공",  authService.signin(user)));
    }

    @Operation(summary = "아이디 중복 확인", tags = {"AuthController"})
    @GetMapping("/dupul/id/{id}")
    public ResponseEntity<ResponseDTO> dupulicateIdCheck(@PathVariable String id){
        System.out.println("아이디"+id);
        authService.dupulicateIdCheck(id);
        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "아이디 중복 체크 성공", null));
    }

    //이거 안 쓸 듯 ?
    @Operation(summary = "이메일 중복 확인", tags = {"AuthController"})
    @GetMapping("/dupul/email/{email}")
    public ResponseEntity<ResponseDTO> dupulicateEmailCheck(@PathVariable String email){
        authService.dupulicateEmailCheck(email);
        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "이메일 중복 체크 성공", null));
    }

    @Operation(summary = "회원 가입 요청", tags = {"AuthController"})
    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> singnup(@RequestBody UserDTO user){
        //여기서 이메일 인증 ID 확인해도 될 듯
        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "회원 가입 성공", authService.signup(user)));
    }


}
