/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.shoponline1.utils;

import com.example.shoponline1.dto.CartInfo;
import com.example.shoponline1.entity.User;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thanh
 */
public class Utils {

    public static User getUserInSession(HttpSession session) {
        
        User user = (User) session.getAttribute("user");
        
        if (user != null) {
            return user;
        }
        return null;
    }

    // Thông tin các sản phẩm trong giỏ hàng, được lưu trữ trong Session.
    public static CartInfo getCartInSession(HttpSession session) {

        CartInfo cartInfo = (CartInfo) session.getAttribute("myCart");

        if (cartInfo == null) {
            cartInfo = new CartInfo();
            session.setAttribute("myCart", cartInfo);
        }

        return cartInfo;
    }

    public static void removeCartInSession(HttpSession session) {
        session.removeAttribute("myCart");
    }

    public static void storeLastOrderedCartInSession(HttpSession session, CartInfo cartInfo) {
        session.setAttribute("lastOrderedCart", cartInfo);
    }

    public static CartInfo getLastOrderedCartInSession(HttpSession session) {
        return (CartInfo) session.getAttribute("lastOrderedCart");
    }
}
