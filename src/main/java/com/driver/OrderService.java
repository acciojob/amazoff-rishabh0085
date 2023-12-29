package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {


    OrderRepository orderRepository = new OrderRepository();
    public static String addOrder(Order order) {

        return OrderRepository.addOrder(order);
    }

    public static String addPartner(String partnerId) {

        return OrderRepository.addPartner(partnerId);
    }

    public static String addOrderPartnerPair(String orderId, String partnerId) {

        return OrderRepository.addOrderPartnerPair(orderId,partnerId);

    }

    public static Object getOrderById(String orderId){
        return OrderRepository.getOrderById(orderId);
    }

    public static DeliveryPartner getPartnerById(String partnerId) {
        return OrderRepository.getPartnerById(partnerId);
    }


    public static Integer getOrderCountByPartnerId(String partnerId) {
        return OrderRepository.getOrderCountByPartnerId(partnerId);
    }

    public static List<String> getOrdersByPartnerId(String partnerId) {

        return OrderRepository.getOrdersByPartnerId(partnerId);
    }

    public static List<String> getAllOrders() {

        return OrderRepository.getAllOrders();
    }

    public static Integer getCountOfUnassignedOrders() {
        return OrderRepository.getCountOfUnassignedOrders();
    }

    public static String deletePartnerById(String partnerId) {

        return OrderRepository.deletePartnerById(partnerId);
    }

    public static String deleteOrderById(String orderId) {

        return OrderRepository.deleteOrderById(orderId);
    }

    public static String getLastDeliveryTimeByPartnerId(String partnerId) {
        return OrderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public static Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {

        return OrderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }
}