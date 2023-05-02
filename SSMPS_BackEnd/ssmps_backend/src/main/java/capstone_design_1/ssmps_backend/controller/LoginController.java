package capstone_design_1.ssmps_backend.controller;

import capstone_design_1.ssmps_backend.dto.ManagerResponse;
import capstone_design_1.ssmps_backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class LoginController {
    private final LoginService managerService;

    @PostMapping("/api/join")
    public ManagerResponse join(){
        // 회원가입
        managerService.join();
        return null;
    }
    @GetMapping("/api/login")
    public ManagerResponse login(){
        // 로그인
        managerService.login();
        return null;
    }
}
