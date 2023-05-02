package capstone_design_1.ssmps_backend.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Manager {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_id")
    private String accountId;
    private String password;
    @OneToMany(fetch = FetchType.LAZY) // 원투매니 관계 ?
    @JoinColumn(name = "store_id")
    private List<Store> stores;
}

