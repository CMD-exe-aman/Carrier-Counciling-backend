package com.careercounsel.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    // These pull your secret keys from application.properties securely
    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> data) throws Exception {

        // 1. Get the amount from your React frontend (e.g., 999)
        int amount = (int) data.get("amount");

        // 2. Initialize the Razorpay Client securely
        RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);

        // 3. Create the Order request
        JSONObject orderRequest = new JSONObject();
        // Razorpay expects the amount in PAISE (multiply by 100). ₹999 = 99900 paise.
        orderRequest.put("amount", amount * 100);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "txn_123456");

        // 4. Tell Razorpay to create the order
        Order order = razorpay.orders.create(orderRequest);

        // 5. Send the official Order Details back to your React app
        return ResponseEntity.ok(order.toString());
    }
}