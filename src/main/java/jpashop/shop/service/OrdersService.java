package jpashop.shop.service;

import jpashop.shop.domain.*;
import jpashop.shop.domain.status.DeliveryStatus;
import jpashop.shop.dto.requestDto.OrdersRequestDto;
import jpashop.shop.dto.requestDto.ProductRequestDto;
import jpashop.shop.dto.responseDto.OrdersResponseDto;
import jpashop.shop.repository.MemberRepository;
import jpashop.shop.repository.OrdersRepository;
import jpashop.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jdo.annotations.Order;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final CartService cartService;

    @Transactional
    public Long addOrders(OrdersRequestDto requestDto){

        Member member = memberRepository.findByUserName(requestDto.getUserName());
        List<Product> productList = requestDto.getProductIds().stream()
                .map( pid -> productRepository.findById(pid).get())
                .collect(Collectors.toList());
        Orders orders = requestDto.toEntity(requestDto);
        orders.addMember(member);
        List<OrderLine> orderLineList = new ArrayList<>();
        for(int i=0; i< productList.size(); i++){
            OrderLine orderLine = OrderLine.createOrderLine()
                    .orderPrice(requestDto.getPrice().get(i))
                    .orderQuantity(requestDto.getCount().get(i))
                    .product(productList.get(i))
                    .build();
            orders.addOrdersLine(orderLine);
        }

        Delivery delivery = Delivery.createDelivery()
                .deliveryStatus(DeliveryStatus.READY)
                .createDate(LocalDateTime.now())
                .address(member.getAddress())
                .build();


        orders.addDelivery(delivery);
        ordersRepository.save(orders);
        cartService.removeCart(requestDto.getProductIds(), requestDto.getUserName());
        return orders.getId();
    }

    @Transactional
    public void cancelOrders(Long ordersId){
        Orders orders = ordersRepository.findById(ordersId).get();
        orders.cancel();
    }

    public List<OrdersResponseDto> getOrders(String userName){
        Member member = memberRepository.findByUserName(userName);
        List<Orders> ordersList = ordersRepository.findAllByMember_Id(member.getId());
        List<OrdersResponseDto> list = ordersList.stream()
                .map(m-> new OrdersResponseDto(m))
                .collect(Collectors.toList());

        return list;
    }

    public OrdersResponseDto getOrdersView(Long ordersId){
        Orders orders = ordersRepository.findById(ordersId).get();
        OrdersResponseDto ordersResponseDto = new OrdersResponseDto(orders);
        return ordersResponseDto;
    }

}
