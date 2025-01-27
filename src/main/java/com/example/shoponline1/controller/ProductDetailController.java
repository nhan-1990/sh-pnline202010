package com.example.shoponline1.controller;

import com.example.shoponline1.dao.IProductDetailDao;
import com.example.shoponline1.dao.IReviewDao;
import com.example.shoponline1.dto.CartInfo;
import com.example.shoponline1.dto.ColorDto;
import com.example.shoponline1.dto.ProductDto;
import com.example.shoponline1.dto.ReviewDto;
import com.example.shoponline1.entity.Product;
import com.example.shoponline1.entity.ProductDetail;
import com.example.shoponline1.entity.Review;
import com.example.shoponline1.entity.User;
import com.example.shoponline1.service.IProductDetailService;
import com.example.shoponline1.service.IProductService;
import com.example.shoponline1.service.IReviewDtoService;
import com.example.shoponline1.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductDetailController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IProductDetailDao iProductDetailDao;

    @Autowired
    private IProductDetailService iProductDetailService;

    @Autowired
    private IReviewDtoService iReviewDtoService;

    @Autowired
    private IReviewDao iReviewDao;

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @SuppressWarnings("unlikely-arg-type")
    @RequestMapping("/productdetail")
    public String productDetail(Model model, HttpSession session,
            @RequestParam("id") int id) {

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
        }

        ProductDto productDto = iProductDetailService.findProductDtoById(id);
        model.addAttribute("productDto", productDto);

        List<ReviewDto> review = iReviewDtoService.findAllReviewDto(productDto.getProductId());
        model.addAttribute("review", review);

        List<ProductDto> listProductDto = iProductDetailService.findListProductById(productDto.getProductId());
        model.addAttribute("listProductDto", listProductDto);

        List<ProductDto> pr = (listProductDto).stream().filter(distinctByKey(p -> p.getRom()))
                .collect(Collectors.toList());
        model.addAttribute("pr", pr);

        List<ColorDto> color = new ArrayList<>();
        for (ProductDto productDto2 : listProductDto) {
            ColorDto cl = new ColorDto();
            cl.setId(productDto2.getProductDetailId());
            cl.setColor(productDto2.getColor());
            color.add(cl);
        }
        model.addAttribute("color", color);

        ProductDetail prDt = iProductDetailDao.findById(id).get();
        model.addAttribute("prDt", prDt);

        List<ProductDto> relatedProduct = iProductDetailService.findRelated(productDto.getProductName(), productDto.getRom(), id);
        model.addAttribute("relatedProduct", relatedProduct);

        CartInfo cartInfo = Utils.getCartInSession(session);
        model.addAttribute("cartInfo", cartInfo);

        return "business/product/productDetail";
    }

    @RequestMapping("/review")
    public String review(Model model, HttpSession session,
            @RequestParam("prddtid") int prdDetailId,
            @RequestParam("prdid") int prdId,
            @RequestParam("content") String content) {

        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            Product prd = iProductService.findById(prdId);
            User user = (User) session.getAttribute("user");
            Review rv = new Review();
            rv.setContent(content);
            rv.setProduct(prd);
            rv.setUser(user);
            iReviewDao.save(rv);

        }
        return "redirect:/productdetail?id=" + prdDetailId;
    }
}
