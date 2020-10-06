/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.shoponline1.controller;

import com.example.shoponline1.dao.IProductDetailDao;
import com.example.shoponline1.dto.CartInfo;
import com.example.shoponline1.dto.CartLineInfo;
import com.example.shoponline1.dto.ProductCartDto;
import com.example.shoponline1.dto.ProductDto;
import com.example.shoponline1.entity.Product;
import com.example.shoponline1.entity.ProductDetail;
import com.example.shoponline1.entity.User;
import com.example.shoponline1.service.OrderServiceImpl;
import com.example.shoponline1.service.ProductDetailServiceImpl;
import com.example.shoponline1.service.ProductServiceImpl;
import com.example.shoponline1.utils.Utils;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author thanh
 */
@Controller
@Transactional
public class CartController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private IProductDetailDao iProductDetailDao;

    @Autowired
    private ProductDetailServiceImpl productDetailServiceImpl;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        // Trường hợp update SL trên giỏ hàng.
        // (@ModelAttribute("cartForm") @Validated CartInfo cartForm)
        if (target.getClass() == CartInfo.class) {

        } // Trường hợp save thông tin khách hàng.
        // (@ModelAttribute @Validated CustomerInfo customerForm)
        //    else if (target.getClass() == CustomerForm.class) {
        //        dataBinder.setValidator(customerFormValidator);
        //    }

    }

    @RequestMapping({"/buyProduct"})
    public String listProductHandler(HttpSession session, Model model,
            @RequestParam(value = "code", defaultValue = "") int code,
            @RequestParam("quantity") int quantity) {

        ProductDetail productDetail = iProductDetailDao.findById(code).get();

        if (productDetail != null) {
            CartInfo cartInfo = Utils.getCartInSession(session);
            ProductCartDto productCartDto = new ProductCartDto(productDetail);
            cartInfo.addProduct(productCartDto, quantity);
        }

        return "redirect:/cart";
    }

    // GET: Hiển thị giỏ hàng.
    @RequestMapping(value = {"/cart"}, method = RequestMethod.GET)
    public String shoppingCart(Model model, HttpSession session) {

        CartInfo cartInfo = Utils.getCartInSession(session);
        model.addAttribute("cartInfo", cartInfo);

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
        }

        return "business/cart/cart";
    }

    @RequestMapping({"/cartRemoveProduct"})
    public String removeProductHandler(HttpSession session, Model model,
            @RequestParam(value = "code", defaultValue = "") Integer code) {

        ProductDetail productDetail = null;

        if (code != null) {
            productDetail = iProductDetailDao.findById(code).get();
        }

        if (productDetail != null) {
            CartInfo cartInfo = Utils.getCartInSession(session);
            ProductCartDto productInfo = new ProductCartDto(productDetail);
            cartInfo.removeProduct(productInfo);
        }

        return "redirect:/cart";
    }

    // POST: Giảm số lượng cho các sản phẩm đã mua.
    @RequestMapping(value = {"/decreaseProduct"}, method = RequestMethod.GET)
    public String shoppingCartDecreaseQty(HttpSession session, Model model,
            @RequestParam("code") int code) {

        ProductDetail productDetail = null;
        if (code != 0) {
            productDetail = iProductDetailDao.findById(code).get();
        }

        if (productDetail != null) {
            CartInfo cartInfo = Utils.getCartInSession(session);
            List<CartLineInfo> lines = cartInfo.getCartLines();
            for (CartLineInfo line : lines) {
                if ((line.getProductCartDto().getProductDetailId() == (code)) && (line.getQuantity() > 1)) {
                    int newQuantity = line.getQuantity() - 1;
                    line.setQuantity(newQuantity);
                }
            }
        }

        return "redirect:/cart";
    }

    // POST: Tăng số lượng cho các sản phẩm đã mua.
    @RequestMapping(value = {"/increaseProduct"}, method = RequestMethod.GET)
    public String shoppingCartIncreaseQty(HttpSession session, Model model,
            @RequestParam("code") int code) {

        ProductDetail productDetail = null;
        if (code != 0) {
            productDetail = iProductDetailDao.findById(code).get();
        }

        if (productDetail != null) {
            CartInfo cartInfo = Utils.getCartInSession(session);
            List<CartLineInfo> lines = cartInfo.getCartLines();
            for (CartLineInfo line : lines) {
                if ((line.getProductCartDto().getProductDetailId() == (code)) && (line.getQuantity() < line.getProductCartDto().getAmount())) {
                    int newQuantity = line.getQuantity() + 1;
                    line.setQuantity(newQuantity);
                }
            }
        }

        return "redirect:/cart";
    }

    // GET: Xem lại thông tin để xác nhận.
    @RequestMapping(value = {"/shoppingCartConfirmation"}, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(Model model, HttpSession session) {

        CartInfo cartInfo = Utils.getCartInSession(session);
        if (cartInfo == null || cartInfo.isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("myCart", cartInfo);

        if (session.getAttribute("user") == null) {
            return "business/user/login";
        } else {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
        }

        return "business/cart/infoConfirm";
    }

    // POST: Gửi đơn hàng (Save).
    @RequestMapping(value = {"/confirmed"}, method = RequestMethod.GET)
    public String shoppingCartConfirmationSave(Model model, HttpSession session) {

        CartInfo cartInfo = Utils.getCartInSession(session);
        User user = Utils.getUserInSession(session);

        if (cartInfo.isEmpty()) {
            return "redirect:/cart";
        } else if (user == null) {
            return "business/user/login";
        }

        model.addAttribute("user", user);

        try {
            orderServiceImpl.saveOrder(cartInfo, user);
        } catch (Exception e) {
            return "shoppingCartConfirmation";
        }

        // Xóa giỏ hàng khỏi session.
        Utils.removeCartInSession(session);

        // Lưu thông tin đơn hàng cuối đã xác nhận mua.
        Utils.storeLastOrderedCartInSession(session, cartInfo);

        return "redirect:/shoppingCartFinalize";
    }

    @RequestMapping(value = {"/shoppingCartFinalize"}, method = RequestMethod.GET)
    public String shoppingCartFinalize(Model model, HttpSession session) {

        CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(session);
        if (lastOrderedCart == null) {
            return "redirect:/cart";
        }
        model.addAttribute("lastOrderedCart", lastOrderedCart);

        User user = Utils.getUserInSession(session);
        model.addAttribute("user", user);

        return "business/cart/finish";
    }

    @RequestMapping("/productImg")
    public void productImage(HttpServletResponse response,
            HttpSession session,
            Model model, @RequestParam("code") int code) throws IOException {

        CartInfo cartInfo = Utils.getCartInSession(session);
        List<CartLineInfo> lines = cartInfo.getCartLines();
        ProductCartDto productCartDto = new ProductCartDto();
        for (CartLineInfo line : lines) {
            if (line.getProductCartDto().getProductDetailId() == code) {
                productCartDto.setImg(line.getProductCartDto().getImg());
            }
        }        

        if (productCartDto != null && productCartDto.getImg() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(productCartDto.getImg());            
        }
        response.getOutputStream().close();
    }
}
