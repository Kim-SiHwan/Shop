package jpashop.shop.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.jdo.annotations.Join;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    private int totalPrice;

    private int totalQuantity;

    @OneToMany
    @JoinColumn(name = "products")
    private List<Product> products = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void addProducts(Product product) {
        this.products.add(product);
    }

    public void removeProducts(List<Product> productList){
        productList.stream().forEach(p -> this.products.remove(p));
    }

    public void addMember(Member member) {
        this.member = member;
    }

    public void addTotalQuantity(int quantity){
        this.totalQuantity+=quantity;
    }

    public void removeTotalQuantity(int quantity){
        this.totalQuantity-=quantity;
    }

    @Builder(builderClassName = "createCart", builderMethodName = "createCart")
    public Cart(Long id, int totalPrice, int totalQuantity) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }
}
