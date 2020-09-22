package jpashop.shop.dto.responseDto;

import jpashop.shop.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private String userName;
    private String phone;
    private String email;
    private String city;
    private String street;
    private String zipcode;

    public MemberResponseDto (Member member){
        userName = member.getUserName();
        phone = member.getInfo().getPhone();
        email = member.getInfo().getEmail();
        city = member.getAddress().getCity();
        street = member.getAddress().getStreet();
        zipcode = member.getAddress().getZipcode();
    }
}
