/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author SHD
 */
public class CartItem {
    private int cartDetailId;
    private int cartId;
    private Motor product;
    private int quantity;
    
    public CartItem() {
    }

    public CartItem(int cartDetailId, int cartId, Motor product, int quantity) {
        this.cartDetailId = cartDetailId;
        this.cartId = cartId;
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem(Motor product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(int cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Motor getProduct() {
        return product;
    }

    public void setProduct(Motor product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
