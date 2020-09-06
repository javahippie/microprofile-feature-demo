/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javahippie.jax2020.checkout;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;

/**
 *
 * @author zoeller
 */
public class CheckoutResponse {
   
    @JsonbProperty("checked_out_amount") 
    private Integer checkedOutAmount;
    @JsonbProperty("price_per_item")
    private BigDecimal pricePerItem;
    @JsonbProperty("total_price")
    private BigDecimal totalPrice;
    @JsonbDateFormat("dd.MM.YYYY HH:mm")
    private LocalDateTime checkoutTime;

    public CheckoutResponse(Integer checkedOutAmount, BigDecimal pricePerItem) {
        this.checkedOutAmount = checkedOutAmount;
        this.pricePerItem = pricePerItem;
        this.totalPrice = pricePerItem.multiply(new BigDecimal(checkedOutAmount));
        this.checkoutTime = LocalDateTime.now();
    }
    
    public Integer getCheckedOutAmount() {
        return checkedOutAmount;
    }

    public void setCheckedOutAmount(Integer checkedOutAmount) {
        this.checkedOutAmount = checkedOutAmount;
    }

    public BigDecimal getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(BigDecimal pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(LocalDateTime checkoutTime) {
        this.checkoutTime = checkoutTime;
    }
    
}
