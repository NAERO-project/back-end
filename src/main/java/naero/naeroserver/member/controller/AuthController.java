package naero.naeroserver.member.controller;

import naero.naeroserver.common.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/signin")
    public ResponseEntity<ResponseDTO> login(){
        ResponseEntity<ResponseDTO> res = null;

        return res;
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> singnup(){
        ResponseEntity<ResponseDTO> res = null;

        return res;
    }
}
