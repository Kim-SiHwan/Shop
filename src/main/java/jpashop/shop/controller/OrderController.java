package jpashop.shop.controller;

import jpashop.shop.domain.Orders;
import jpashop.shop.dto.requestDto.OrdersRequestDto;
import jpashop.shop.dto.responseDto.OrdersResponseDto;
import jpashop.shop.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop/")
public class OrderController {

    private final OrdersService ordersService;

    @GetMapping("/order")
    public String goOrderForm(OrdersRequestDto ordersRequestDto, Model model){
        model.addAttribute("orderForm",ordersRequestDto);
        return "/shop/orderForm";
    }

    @PostMapping("/order")
    public String Order(OrdersRequestDto ordersRequestDto){
        Long id= ordersService.addOrders(ordersRequestDto);
        OrdersResponseDto ordersResponseDto = ordersService.getOrdersView(id);
        return "redirect:/shop/main";
    }

    @DeleteMapping("/order")
    public String removeOrder(Long ordersId){
        ordersService.cancelOrders(ordersId);
        return "redirect:/shop/orders";
    }

    @GetMapping("/orders")
    public String getOrders(String userName,Model model){
        userName = "오이";
        List<OrdersResponseDto> list = ordersService.getOrders(userName);
        model.addAttribute("list",list);
        return "/shop/orders";
    }

    @GetMapping("/get_orders")
    public String getOrdersView(Long ordersId , Model model){
        OrdersResponseDto ordersResponseDto = ordersService.getOrdersView(ordersId);
        model.addAttribute("orders",ordersResponseDto);
        return "/shop/ordersView";
    }
}
