package jpashop.shop;

import jpashop.shop.domain.Product;
import jpashop.shop.repository.ProductRepository;
import jpashop.shop.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Test
    public void 상품등록 (){
    //given

        Product product = Product.createProduct()
                .title("SampleTitle")
                .content("SampleContent")
                .createDate(LocalDateTime.now())
                .price(10000)
                .quantity(100)
                .type("Tee")
                .url("path")
                .build();
        productRepository.save(product);
    //when
        Long productId = product.getId();
    //then
        assertEquals(product,productRepository.findById(productId).get());

    }

    @Test
    public void 상품목록 (){
    //given
        for(int i=1; i<=3; i++){
            Product product = Product.createProduct()
                    .title("SampleTitle"+i)
                    .content("SampleContent"+i)
                    .createDate(LocalDateTime.now())
                    .price(i*10000)
                    .quantity(i*100)
                    .type("Tee")
                    .url("path")
                    .build();
            productRepository.save(product);
        }
    //when
        List<Product> list = productRepository.findAll();
    //then
        assertEquals(3,list.size());

        list.stream().forEach(l -> System.out.println(l.getTitle()));

    }

    @Test(expected = NoSuchElementException.class)
    public void 상품삭제 (){
    //given
        Product product = Product.createProduct()
                .title("SampleTitle")
                .content("SampleContent")
                .createDate(LocalDateTime.now())
                .price(10000)
                .quantity(100)
                .type("Tee")
                .url("path")
                .build();
        productRepository.save(product);
    //when
        Long productId = product.getId();
        productRepository.delete(product);
        Product getProduct =productRepository.findById(productId).get();
    //then
        fail("NoSuchElementException이 발생해야함.");


    }

}
