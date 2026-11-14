package com.campusfood.express.service;

import com.campusfood.express.dto.CartItemRequest;
import com.campusfood.express.dto.CreateOrderRequest;
import com.campusfood.express.entity.Coupon;
import com.campusfood.express.entity.Order;
import com.campusfood.express.entity.Product;
import com.campusfood.express.repository.CouponRepository;
import com.campusfood.express.repository.OrderRepository;
import com.campusfood.express.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock OrderRepository orderRepository;
    @Mock ProductRepository productRepository;
    @Mock CouponRepository couponRepository;
    @InjectMocks OrderService orderService;

    @BeforeEach
    void setUp() {
        lenient().when(orderRepository.save(any(Order.class))).thenAnswer(inv -> inv.getArgument(0));
    }

    private Product product(long id, String price) {
        Product p = new Product();
        p.setId(id);
        p.setPrice(new BigDecimal(price));
        return p;
    }

    private CreateOrderRequest request(String coupon, long productId, int qty) {
        CartItemRequest item = new CartItemRequest();
        item.setProductId(productId);
        item.setQuantity(qty);
        CreateOrderRequest r = new CreateOrderRequest();
        r.setUserId(1L);
        r.setDeliveryLocation("KTX A");
        r.setPaymentMethod("COD");
        r.setCouponCode(coupon);
        r.setItems(List.of(item));
        return r;
    }

    @Test
    void createOrder_calculatesTotalWithoutCoupon() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product(1, "30000")));
        Order o = orderService.createOrder(request(null, 1, 2));
        assertEquals(0, new BigDecimal("60000").compareTo(o.getTotalAmount()));
        assertEquals("Đang xử lý", o.getStatus());
    }

    @Test
    void createOrder_appliesPercentCoupon() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product(1, "50000")));
        Coupon c = new Coupon(1L, "SV2026", 10, true);
        when(couponRepository.findByCodeAndIsActiveTrue("SV2026")).thenReturn(Optional.of(c));
        Order o = orderService.createOrder(request("SV2026", 1, 2));
        assertEquals(0, new BigDecimal("10000").compareTo(o.getDiscountAmount()));
        assertEquals(0, new BigDecimal("90000").compareTo(o.getTotalAmount()));
    }

    @Test
    void createOrder_invalidCouponThrows() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product(1, "50000")));
        when(couponRepository.findByCodeAndIsActiveTrue("BAD")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> orderService.createOrder(request("BAD", 1, 1)));
    }

    @Test
    void createOrder_unknownProductThrows() {
        when(productRepository.findById(9L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> orderService.createOrder(request(null, 9, 1)));
    }

    @Test
    void cancelOrder_succeedsWhenProcessing() {
        Order o = Order.builder().userId(1L).status("Đang xử lý").build();
        when(orderRepository.findById(5L)).thenReturn(Optional.of(o));
        assertEquals("Đã hủy", orderService.cancelOrder(5L, 1L).getStatus());
    }

    @Test
    void cancelOrder_rejectedForOtherUser() {
        Order o = Order.builder().userId(1L).status("Đang xử lý").build();
        when(orderRepository.findById(5L)).thenReturn(Optional.of(o));
        assertThrows(RuntimeException.class, () -> orderService.cancelOrder(5L, 2L));
    }

    @Test
    void cancelOrder_rejectedWhenNotProcessing() {
        Order o = Order.builder().userId(1L).status("Đã giao").build();
        when(orderRepository.findById(5L)).thenReturn(Optional.of(o));
        assertThrows(RuntimeException.class, () -> orderService.cancelOrder(5L, 1L));
    }
}
