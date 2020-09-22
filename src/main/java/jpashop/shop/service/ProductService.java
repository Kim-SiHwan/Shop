package jpashop.shop.service;

import jpashop.shop.domain.Product;
import jpashop.shop.dto.requestDto.ProductRequestDto;
import jpashop.shop.dto.responseDto.ProductResponseDto;
import jpashop.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long uploadProduct(ProductRequestDto productRequestDto){
        Product product = productRequestDto.toEntity(productRequestDto);
        productRepository.save(product);
        return product.getId();
    }

    public List<ProductResponseDto> findAll(){
        List<Product> productList = productRepository.findAll();
        List<ProductResponseDto> list =
                productList.stream().map( l -> new ProductResponseDto(l))
                .collect(Collectors.toList());
        return list;
    }

    @Transactional
    public void removeProduct(Long productId){
        Product product = productRepository.findById(productId).get();
        productRepository.delete(product);
    }

    @Transactional
    public void updateProduct(ProductRequestDto requestDto){
        Product product = productRepository.findById(requestDto.getProductId()).get();
        product.changeProductText(requestDto.getTitle(), requestDto.getContent());
    }




}
