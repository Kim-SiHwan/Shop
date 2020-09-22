package jpashop.shop.service;

import jpashop.shop.domain.*;
import jpashop.shop.domain.status.DeliveryStatus;
import jpashop.shop.dto.requestDto.OrdersRequestDto;
import jpashop.shop.dto.requestDto.ProductRequestDto;
import jpashop.shop.repository.MemberRepository;
import jpashop.shop.repository.OrdersRepository;
import jpashop.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Long addOrders(OrdersRequestDto requestDto){

        Member member = memberRepository.findByUserName(requestDto.getUserName());
        List<Product> productList = requestDto.getProductIds().stream()
                .map( pid -> productRepository.findById(pid).get())
                .collect(Collectors.toList());

        List<OrderLine> orderLineList = new ArrayList<>();
        for(int i=0; i< productList.size(); i++){
            OrderLine orderLine = OrderLine.createOrderLine()
                    .orderPrice(requestDto.getPrice().get(i))
                    .orderQuantity(requestDto.getCount().get(i))
                    .product(productList.get(i))
                    .build();
            orderLineList.add(orderLine);
        }

        Delivery delivery = Delivery.createDelivery()
                .deliveryStatus(DeliveryStatus.READY)
                .createDate(LocalDateTime.now())
                .address(member.getAddress())
                .build();

        Orders orders = requestDto.toEntity(requestDto);
        orders.addMember(member);
        orderLineList.stream().forEach( orderLine ->orders.addOrdersLine(orderLine));
        orders.addDelivery(delivery);
        ordersRepository.save(orders);

        return orders.getId();
    }

    @Transactional
    public void cancelOrders(Long ordersId){
        Orders orders = ordersRepository.findById(ordersId).get();
        orders.cancel();
    }

}
