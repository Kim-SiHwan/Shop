package jpashop.shop.service;

import jpashop.shop.domain.Product;
import jpashop.shop.dto.pagination.PageMaker;
import jpashop.shop.dto.pagination.PageVo;
import jpashop.shop.dto.requestDto.ProductRequestDto;
import jpashop.shop.dto.responseDto.ProductResponseDto;
import jpashop.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;


    public PageMaker findAll(PageVo vo){
        Pageable pageable = vo.makePageable(0,"id");
        Page<Product> pageResult = productRepository.findAll(productRepository.makePredicate(vo.getType(),vo.getKeyword()),pageable);
        PageMaker pageMaker = new PageMaker(pageResult);
        return pageMaker;
    }


    public List<ProductResponseDto> findTop20(){
        List<Product> productList = productRepository.findTop20ByOrderByIdDesc();
        List<ProductResponseDto> list =
                productList.stream().map( l -> new ProductResponseDto(l))
                .collect(Collectors.toList());
        return list;
    }

    public List<List<ProductResponseDto>> findAllByAllType(){

        List<Product> chairList = productRepository.findTop10ByType("의자");
        List<Product> deskList = productRepository.findTop10ByType("책상");
        List<Product> sofaList = productRepository.findTop10ByType("소파");
        List<Product> bedList = productRepository.findTop10ByType("침대");

        List<List<Product>> productResult = new ArrayList<>();
        productResult.add(chairList);
        productResult.add(deskList);
        productResult.add(sofaList);
        productResult.add(bedList);

        List<List<ProductResponseDto>> result = new ArrayList<>();

        for(int i=0; i< productResult.size(); i++){
            List<ProductResponseDto> list =
                    productResult.get(i).stream().map( l -> new ProductResponseDto(l))
                            .collect(Collectors.toList());
            result.add(list);
        }
        return result;
    }

    public PageMaker findAllByPage(PageVo vo){
        Pageable pageable = vo.makePageable(0,"id");

        Page<Product> pageResult = productRepository.findAll(pageable);
        PageMaker pageMaker = new PageMaker(pageResult);
        return pageMaker;
    }

    @Transactional
    public Long uploadProduct(ProductRequestDto productRequestDto){
        Product product = productRequestDto.toEntity(productRequestDto);
        productRepository.save(product);
        return product.getId();
    }

    public ProductResponseDto viewProduct(Long productId){
        Product product = productRepository.findById(productId).get();
        ProductResponseDto productResponseDto = new ProductResponseDto(product);
        return productResponseDto;
    }

    @Transactional
    public void updateProduct(ProductRequestDto requestDto){
        Product product = productRepository.findById(requestDto.getProductId()).get();
        product.changeProductText(requestDto.getTitle(), requestDto.getContent());
    }

    @Transactional
    public void removeProduct(Long productId){
        Product product = productRepository.findById(productId).get();
        productRepository.delete(product);
    }




}
