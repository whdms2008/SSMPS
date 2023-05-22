package capstone_design_1.ssmps_backend.controller;

import capstone_design_1.ssmps_backend.domain.Manager;
import capstone_design_1.ssmps_backend.dto.ManagerRequest;
import capstone_design_1.ssmps_backend.dto.ManagerResponse;
import capstone_design_1.ssmps_backend.dto.Token;
import capstone_design_1.ssmps_backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@Slf4j
public class LoginController {
    private final LoginService managerService;

    @PostMapping("/api/join")
    public ManagerResponse join(@RequestBody ManagerRequest manager){
        // 회원가입
        log.error(manager.getId());
//        List<Store> storeList = manager.getStoreList().stream()
//                .map(s -> new Store(null, s.getName(), s.getAddress(), null, null)).collect(Collectors.toList());
        Manager joinManager = new Manager(null, manager.getId(), manager.getPassword(), null);
        Manager joinedManager = managerService.join(joinManager);
//        List<StoreResponse> storeListRes = joinedManager.getStores().stream().map(s -> new StoreResponse(s.getName(), s.getAddress())).collect(Collectors.toList());
        ManagerResponse resultManager = new ManagerResponse(joinManager.getAccountId(), null);
        return resultManager;
    }
    @GetMapping("/api/manager/login")
    public String login(@RequestParam String id, @RequestParam String password){
        Manager loginManager = new Manager(null, id, password, null);
        // 로그인
//        return ResponseEntity.ok(managerService.login(loginManager));
        return managerService.login(loginManager);
    }

    @GetMapping("/api/loginFirst")
    public String loginFirst(@RequestParam String id, @RequestParam String password){
        Manager loginManager = new Manager(null, id, password, null);
        String token = managerService.login(loginManager);
        // 로그인
        return token;
    }

//    @GetMapping("/api/loginFirst")
//    public String loginFirst(){
////        Manager loginManager = new Manager(null, id, password, null);
////        // 로그인
//////        return ResponseEntity.ok(managerService.login(loginManager));
//        return "hello";
//    }

}
