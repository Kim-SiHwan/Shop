package jpashop.shop.domain;


import jpashop.shop.domain.memberInfo.Address;
import jpashop.shop.domain.memberInfo.Info;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String userName;

    private LocalDateTime createDate;

    private String role;

    private String password;

    @Embedded
    private Address address;

    @Embedded
    private Info info;

    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Builder(builderClassName = "createMember", builderMethodName = "createMember")
    public Member(String userName, LocalDateTime createDate, String role, String password, Address address, Info info) {
        this.userName = userName;
        this.createDate = createDate;
        this.role = role;
        this.password = password;
        this.address = address;
        this.info = info;
    }
}
