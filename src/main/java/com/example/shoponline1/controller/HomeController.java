package com.example.shoponline1.controller;

import com.example.shoponline1.dao.IProductDtoDao;
import com.example.shoponline1.dao.ITrademarkDao;
import com.example.shoponline1.dto.CartInfo;
import com.example.shoponline1.dto.ProductDto;
import com.example.shoponline1.entity.Trademark;
import com.example.shoponline1.entity.User;
import com.example.shoponline1.service.IProductDetailService;
import com.example.shoponline1.utils.Utils;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private IProductDetailService iProductDetailService;

    @Autowired
    private ITrademarkDao iTrademarkDao;

    @GetMapping("/home")
    public String listProducts(Model model, HttpSession session,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "4") int size,
            @RequestParam(name = "sort", defaultValue = "productDetailId: DESC") String sort) {

        Page<ProductDto> productPage = iProductDetailService.findPaginated(page, size, sort);
        model.addAttribute("productPage", productPage);
        
        Page<ProductDto> bestSold = iProductDetailService.bestSold(page, 4);
        model.addAttribute("bestSold", bestSold);

        int totalPage = productPage.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
        }

        CartInfo myCart = Utils.getCartInSession(session);
        model.addAttribute("cartForm", myCart);
        List<Trademark> trademark = iTrademarkDao.findAll();
        model.addAttribute("trademark", trademark);

        return "business/home/shop";
    }

    @GetMapping("/search")
    public String search(Model model, HttpServletRequest request, HttpSession session,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "8") int size,
            @RequestParam(name = "sort", defaultValue = "productDetailId: DESC") String sort,
            @RequestParam("productName") String productName) {
        String name1 = null;
        String name2 = null;
        for (int i = 0; i < productName.length() - 1; i++) {
            if(productName.charAt(i) == ' '){
                System.out.println("i: " +i);
                name1 = productName.substring(0, i).trim();
                name2 = productName.substring(i+1).trim();
            }
        }

        Page<ProductDto> productPage = iProductDetailService.findByProductName(page, size, sort, name1, name2);
        model.addAttribute("productPage", productPage);
        int totalPage = productPage.getTotalPages();

        if (totalPage > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
        }
        CartInfo myCart = Utils.getCartInSession(session);
        model.addAttribute("cartForm", myCart);
        List<Trademark> trademark = iTrademarkDao.findAll();
        model.addAttribute("trademark", trademark);

        return "business/home/shop";
    }

    @GetMapping("/filter")
    public String filter(Model model, HttpSession session, HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "4") int size,
            @RequestParam(value = "minPrice", defaultValue = "0") double minPrice,
            @RequestParam(value = "maxPrice", defaultValue = "200000000") double maxPrice,
            @RequestParam(value = "brand", defaultValue = "0") int brand,
            @RequestParam(value = "brand1", defaultValue = "0") int brand1,
            @RequestParam(value = "brand2", defaultValue = "0") int brand2,
            @RequestParam(name = "sort", defaultValue = "productDetailId: DESC") String sort) {

        if (brand != 0) {
            if (brand1 == 0) {
                brand1 = brand;
            } else if (brand2 == 0) {
                brand2 = brand;
            }
        }

        model.addAttribute("brand1", brand1);
        model.addAttribute("brand2", brand2);

        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);

        String brandName1 = "";
        String brandName2 = "";
        if (brand1 != 0) {
            brandName1 = iTrademarkDao.findById(brand1).get().getTrademarkName();
            model.addAttribute("brandName1", brandName1);
        }
        if (brand2 != 0) {
            brandName2 = iTrademarkDao.findById(brand2).get().getTrademarkName();
            model.addAttribute("brandName2", brandName2);
        }

        Page<ProductDto> productPage = iProductDetailService.filter(page, size, sort, brandName1, brandName2, minPrice, maxPrice);
        model.addAttribute("productPage", productPage);
        
        Page<ProductDto> bestSold = iProductDetailService.bestSold(1, 4);
        model.addAttribute("bestSold", bestSold);

        int totalPage = productPage.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
        }
        CartInfo myCart = Utils.getCartInSession(session);
        model.addAttribute("cartForm", myCart);
        List<Trademark> trademark = iTrademarkDao.findAll();
        model.addAttribute("trademark", trademark);

        return "business/home/shop";
    }

    @RequestMapping("/productImage")
    public void productImage(HttpServletResponse response,
            Model model, @RequestParam("id") int id) throws IOException {

        ProductDto prDto = iProductDetailService.findProductDtoById(id);

        if (prDto != null && prDto.getImage1() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(prDto.getImage1());
        }
        response.getOutputStream().close();
    }
}
