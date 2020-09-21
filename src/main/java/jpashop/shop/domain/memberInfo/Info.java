package jpashop.shop.domain.memberInfo;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Info {

    private String phone;
    private String email;

    protected Info(){}

    public Info(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }
}
