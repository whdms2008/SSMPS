package capstone_design_1.ssmps_backend.service;

import capstone_design_1.ssmps_backend.domain.Manager;
import capstone_design_1.ssmps_backend.repository.LoginRepository;
import capstone_design_1.ssmps_backend.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LoginService {
    private final LoginRepository managerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public Manager join(Manager manager) {
        String encodedPassword = passwordEncoder.encode(manager.getPassword());
        Manager encodedManager = new Manager(null, manager.getAccountId(), encodedPassword, null);
        return managerRepository.join(encodedManager);
    }
//
    public String login(Manager manager){
        Manager findManager = managerRepository.findManagerByAccountId(manager.getAccountId())
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        if(passwordEncoder.matches(manager.getPassword(), findManager.getPassword())){
            return jwtTokenProvider.createToken(manager.getAccountId());
        }else{
            throw new BadCredentialsException("로그인 정보가 일치하지 않습니다.");
        }
    }
}