package com.paytm.paytm.controllers;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Controller
public class PageController {
 
    @RequestMapping("/")
    public String homePage() {
        System.out.println("this is home page");
        return "home"; 
    }
 
    // Create order
    @PostMapping("user/create_order")
    @ResponseBody
    public String createOrder(@RequestBody Map<String, Object> data) throws Exception {

        int amount = Integer.parseInt(data.get("amount").toString());

        RazorpayClient client = new RazorpayClient("rzp_test_blrInSYebeBqDW", "91Z4vxBKyo7poed2PfP0uArg");
        JSONObject obj = new JSONObject();
        obj.put("amount", amount * 100);
        obj.put("currency", "INR");
        obj.put("receipt", "txn_123456");
        // Creating order
        Order order = client.Orders.create(obj);
        System.out.println(order);
        System.out.println(obj.toString());

        return order.toString();
    }
}