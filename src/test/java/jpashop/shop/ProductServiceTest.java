package jpashop.shop;

import jpashop.shop.domain.Product;
import jpashop.shop.dto.pagination.PageMaker;
import jpashop.shop.dto.pagination.PageVo;
import jpashop.shop.dto.responseDto.ProductResponseDto;
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
       createProduct();
    //when
        List<Product> list = productRepository.findAll();
        List<List<ProductResponseDto>> typeList = productService.findAllByAllType();
        List<Product> descList =productRepository.findTop20ByOrderByIdDesc();

    //then
        assertEquals(45,list.size());
        //책상타입의 개수
        assertEquals(10,typeList.get(1).size());

        descList.stream().forEach(l -> System.out.println(l.getTitle()));
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
    @Test
    public void 검색 (){
    //given
        createProduct();
    //when
        List<Product> findProducts = (List<Product>) productRepository.findAll(productRepository.makePredicate("소파","브라운"));
    //then
        findProducts.stream().forEach(l -> System.out.println(l.getTitle()));

    }

    @Test
    public void 페이징(){
    //given
        for(int i=1; i<=3; i++){
            Product product = Product.createProduct()
                    .title("SampleTitle"+i)
                    .content("SampleContent"+i)
                    .createDate(LocalDateTime.now())
                    .price(10000)
                    .quantity(100)
                    .type("Tee"+i)
                    .url("path"+i)
                    .build();
            productRepository.save(product);
        }

        PageVo vo = new PageVo();
    //when
        PageMaker pageMaker = productService.findAllByPage(vo);

    //then

        assertEquals(30,pageMaker.getResult().getTotalElements());

    }

    public void createProduct(){
        for(int i=1; i<=5; i++){
            Product product = Product.createProduct()
                    .title("SampleTitle의자"+i)
                    .content("SampleContent의자"+i)
                    .createDate(LocalDateTime.now())
                    .price(i*10000)
                    .quantity(i*100)
                    .type("의자")
                    .url("path")
                    .build();
            productRepository.save(product);
        }
        for(int i=1; i<=15; i++){
            Product product = Product.createProduct()
                    .title("SampleTitle책상"+i)
                    .content("SampleContent책상"+i)
                    .createDate(LocalDateTime.now())
                    .price(i*10000)
                    .quantity(i*100)
                    .type("책상")
                    .url("path")
                    .build();
            productRepository.save(product);
        }
        for(int i=1; i<=25; i++){
            Product product = Product.createProduct()
                    .title("SampleTitle소파"+i)
                    .content("SampleContent소파"+i)
                    .createDate(LocalDateTime.now())
                    .price(i*10000)
                    .quantity(i*100)
                    .type("소파")
                    .url("path")
                    .build();
            productRepository.save(product);
        }
    }

}
