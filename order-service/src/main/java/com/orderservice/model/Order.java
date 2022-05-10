package com.orderservice.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private  id;
    //Need to implement User Id here to make an order
    private Long userId;
    //Need to implement the discount coupon
    //For coupon will implement a Hashmap with couponName as key and discount% as value.
    private String coupons;
    private String orderNumber;
    //Need to think about it- will send total amount post discount based on coupons and quantity if same item
    //and order id to the payment service and expects a fake response.
    //Will calculate the total amount by apllying
    // [price*quantity of a product] minus (sameItemMoreQuantityDiscount ) minus coupons on order service only
    //After successfully payment will deliver a message to the customer
    // that your order has been placed with order number
    //[Order Service --> Payment Service-- will create a credit balance of 1 lakh in Payment service,
    // based on the unsufficient balance we can give failed response otherwise if the amount is under credit
    // we will give the success response, and after each success response will decrement the amount balance]
    //Order service to payment service- attributes required
    // 1- Total Amount after all the discount[quantity discount]+coupons
    // 2- Mode Of Payment [will create a list of payment mode in Product service like google Pay,
    // PhonePe, CC, DC]
    // 3- In case of credit card/Debit card will take the fake card Number, Expiry Date, CVV,
    // 4- In case of Googlepe and Phone Pay will take mobile number.
    // 5- In Payment service will create the hardcorded data to validate these upcoming data from order service
    // and based on that will return the response and save inside the DB.
    //5.	If the Item is purchased / If the payment fails, the available quantity of the products
    // and items must be updated accordingly in the database
    private String paymentStatus;
    //Need to think about order status after successful payment.
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemsList;
}