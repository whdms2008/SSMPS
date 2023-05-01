package capstone_design_1.ssmps_backend.service;

import capstone_design_1.ssmps_backend.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LoginService {
    private final LoginRepository managerRepository;

    @Transactional
    public void join() {

    }
//
    @Transactional
    public void login(){

    }
}
