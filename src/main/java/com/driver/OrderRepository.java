package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {


    public static HashMap<String,Order> orderDetails = new HashMap<>();
    public static HashMap<String,DeliveryPartner> deliveryPartner = new HashMap<>();
    public static HashMap<String,String> orderPartnerPair = new HashMap<>();

    public static HashMap<String, List<String> > partnerOrderdb = new HashMap<>();

    public static String addOrder(Order order) {
        orderDetails.put(order.getId(),order);
        String s = "Order created successfully";
        return s;

    }

    public static String addPartner(String partnerId) {

        DeliveryPartner dp = new DeliveryPartner(partnerId);

        deliveryPartner.put(partnerId,dp);

        return "New delivery partner added successfully";
    }

    public static String addOrderPartnerPair(String orderId, String partnerId) {

        if(orderDetails.containsKey(orderId) == true && deliveryPartner.containsKey(partnerId) == true) {
            orderPartnerPair.put(orderId,partnerId);
        }

        List<String> currentOrders = new ArrayList<>();

        if(partnerOrderdb.containsKey(partnerId))
            currentOrders = partnerOrderdb.get(partnerId);


        currentOrders.add(orderId);

        partnerOrderdb.put(partnerId,currentOrders);


        DeliveryPartner db = deliveryPartner.get(partnerId);
        db.setNumberOfOrders(currentOrders.size());
        return "New order-partner pair added successfully";
    }

    public static Object getOrderById(String orderId) {

        return orderDetails.get(orderId);

    }

    public static DeliveryPartner getPartnerById(String partnerId) {

        return deliveryPartner.get(partnerId);
    }

    public static Integer getOrderCountByPartnerId(String partnerId) {

        //DeliveryPartner db = deliveryPartner.get(partnerId);
        if(partnerOrderdb.containsKey(partnerId)==false)
            return 0;

        return partnerOrderdb.get(partnerId).size();

    }

    public static List<String> getOrdersByPartnerId(String partnerId) {

        List<String> orderss = partnerOrderdb.get(partnerId);

        return orderss;
    }

    public static List<String> getAllOrders() {

        List<String> allOrders = new ArrayList<>();
        for(String id:orderDetails.keySet()){
            allOrders.add(id);
        }

        return allOrders;

    }

    public static Integer getCountOfUnassignedOrders() {
        return (orderDetails.size() - orderPartnerPair.size());
    }

    public static String deletePartnerById(String partnerId) {
        if(deliveryPartner.containsKey(partnerId)==false)
            return "Delivery partner does not exists";

        deliveryPartner.remove(partnerId);

        List<String> orderAssignedToParticularPartner = partnerOrderdb.get(partnerId);
        partnerOrderdb.remove(partnerId);

        // for(String id : orderPartnerPair.keySet()){
        for(String order : orderAssignedToParticularPartner){
            if(orderPartnerPair.containsKey(order))
                orderPartnerPair.remove(order);
        }
        //}

        return partnerId + " removed Successfully";
    }

    public static String deleteOrderById(String orderId) {

        if(orderDetails.containsKey(orderId)==false)
            return "Order does not exists";

        orderDetails.remove(orderId);

        orderPartnerPair.remove(orderId);

        int flag = 0;

        for(String partner : partnerOrderdb.keySet()){
            List<String> listOfOrders = partnerOrderdb.get(partner);

            for(String s : listOfOrders)
            {
                if(s.equals(orderId)) {
                    listOfOrders.remove(orderId);

                    flag=1;
                    break;
                }
            }


            partnerOrderdb.put(partner,listOfOrders);

            if(flag==1)
                break;
        }

        return orderId + " removed successfully";



    }

    public static String getLastDeliveryTimeByPartnerId(String partnerId) {
        int maxDeliveryTime = Integer.MIN_VALUE;

        List<String> order = partnerOrderdb.get(partnerId);

        for(String s : order){
            Order or = orderDetails.get(s);
            maxDeliveryTime = Math.max(or.getDeliveryTime(),maxDeliveryTime);
        }

        return maxDeliveryTime + "";
    }

    public static Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        List<String> orders = partnerOrderdb.get(partnerId);
        String ti[]=time.split(":");
        int tim = Integer.parseInt(ti[0])*60 + Integer.parseInt(ti[1]);

        int count = 0;

        for(String s : orders){
            Order or = orderDetails.get(s);
            if(or.getDeliveryTime() > tim)
                count++;
        }
        return count;
    }
}