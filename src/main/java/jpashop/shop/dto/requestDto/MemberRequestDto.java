package jpashop.shop.dto.requestDto;

import jpashop.shop.domain.Member;
import jpashop.shop.domain.memberInfo.Address;
import jpashop.shop.domain.memberInfo.Info;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberRequestDto {
    @NotEmpty(message = "이름을 입력하세요.")
    private String userName;
    @NotEmpty(message = "비밀번호를 입력하세요")
    @Size(min = 4, message = "4자이상 입력해주세요.")
    private String password;
    @NotEmpty(message = "전화번호를 입력하세요")
    @Size(min = 10 , message = "-빼고 입력해주세요.")
    private String phone;
    @NotEmpty(message = "이메일을 입력하세요.")
    @Email(message = "이메일 형식을 맞춰주세요.")
    private String email;
    @NotEmpty(message = "주소를 입력하세요")
    @Size(min = 1 , message = "1자이상 입력해주세요.")
    private String city;
    @Size(min = 1 , message = "1자이상 입력해주세요.")
    private String street;
    @Size(min = 1 , message = "1자이상 입력해주세요.")
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
