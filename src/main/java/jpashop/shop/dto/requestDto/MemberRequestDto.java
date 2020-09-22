package jpashop.shop.dto.requestDto;

import jpashop.shop.domain.Member;
import jpashop.shop.domain.memberInfo.Address;
import jpashop.shop.domain.memberInfo.Info;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
public class MemberRequestDto {
    @NotEmpty(message = "이름을 입력하세요.")
    private String userName;
    private String password;
    private String phone;
    private String email;
    private String city;
    private String street;
    private String zipcode;

    public Member toEntity(MemberRequestDto memberRequestDto){
        return Member.createMember()
                .userName(memberRequestDto.getUserName())
                .password(memberRequestDto.getPassword())
                .createDate(LocalDateTime.now())
                .address(new Address(memberRequestDto.getCity(),memberRequestDto.getStreet(),memberRequestDto.getZipcode()))
                .info(new Info(memberRequestDto.getPhone(),memberRequestDto.getEmail()))
                .role("USER")
                .build();
    }

}
