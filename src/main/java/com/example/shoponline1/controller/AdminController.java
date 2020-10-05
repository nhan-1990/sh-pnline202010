package com.example.shoponline1.controller;

import com.example.shoponline1.dao.IColorDao;
import com.example.shoponline1.dao.IConfigDao;
import com.example.shoponline1.dao.IOrderDao;
import com.example.shoponline1.dao.IOrderDetailDao;
import com.example.shoponline1.dao.IProductDao;
import com.example.shoponline1.dao.IProductDetailDao;
import com.example.shoponline1.dao.IPromotionDao;
import com.example.shoponline1.dao.ITrademarkDao;
import com.example.shoponline1.dto.ProductAdminDto;
import com.example.shoponline1.dto.ProductDetailAdminDto;
import com.example.shoponline1.entity.Color;
import com.example.shoponline1.entity.Configurator;
import com.example.shoponline1.entity.Images;
import com.example.shoponline1.entity.Orders;
import com.example.shoponline1.entity.OrderDetail;
import com.example.shoponline1.entity.Product;
import com.example.shoponline1.entity.ProductDetail;
import com.example.shoponline1.entity.Promotion;
import com.example.shoponline1.entity.Trademark;
import com.example.shoponline1.entity.User;
import com.example.shoponline1.service.IProductService;
import com.example.shoponline1.service.ImageService;
import com.example.shoponline1.service.OrderServiceImpl;
import com.example.shoponline1.validation.productAdminValidation;
import com.example.shoponline1.validation.productAdminValidations;
import com.example.shoponline1.validation.saveProductAdminValidation;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.shoponline1.dao.IImagesDao;
import com.example.shoponline1.dto.ProductDto;
import com.example.shoponline1.service.ProductDetailServiceImpl;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.PageImpl;
import org.springframework.transaction.annotation.Transactional;

@Controller
public class AdminController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ProductDetailServiceImpl productDetailService;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private IProductDao productDao;

    @Autowired
    private IConfigDao configDao;

    @Autowired
    private IColorDao colorDao;

    @Autowired
    private productAdminValidation prdValidation;

    @Autowired
    private productAdminValidations prdValidations;

    @Autowired
    private saveProductAdminValidation savePrdValidation;

    @Autowired
    private IProductDetailDao productDetaiDao;

    @Autowired
    private IImagesDao imageDao;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ITrademarkDao iTrademarDao;

    @Autowired
    private IPromotionDao promotionDao;

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IOrderDetailDao orderDetailDao;

    // admin home
    // list product
    @RequestMapping("/admin")
    public String product(HttpSession session, Model model, HttpServletRequest request,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {

        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(2);

        /*int page = 0;
		int size = 3;
		if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
			page = Integer.parseInt(request.getParameter("page")) - 1;
		}
		if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
			size = Integer.parseInt(request.getParameter("size"));
		}*/
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        Page<Product> prd = productDao.findAll(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("product", prd);

        return "system/products/product/product";
    }

    @RequestMapping("/admin/product")
    public String producttt() {
        return "redirect:/admin";
    }
    // delete product

    @GetMapping("/admin/delete")
    public String deleteProduct(@RequestParam("id") int id) {

        Product prd = productService.findById(id);
        List<ProductDetail> prdDt = prd.getProductDetail();
        for (ProductDetail productDetail : prdDt) {
            productDetaiDao.deleteById(productDetail.getProductDetailId());
        }
        productDao.deleteById(id);
        return "redirect:/admin";
    }

    // add product
    @RequestMapping("/admin/addproduct")
    public String addProduct(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<Trademark> td = iTrademarDao.findAll();
        model.addAttribute("td", td);
        List<Promotion> pr = promotionDao.findAll();
        model.addAttribute("pr", pr);
        ProductAdminDto prdto = new ProductAdminDto();
        model.addAttribute("prdto", prdto);

        return "system/products/product/addProduct";
    }

    // edit product
    @RequestMapping("/admin/editproduct")
    public String edtProduct(HttpSession session, Model model, @RequestParam("id") int id) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<Trademark> td = iTrademarDao.findAll();
        model.addAttribute("td", td);
        List<Promotion> pr = promotionDao.findAll();
        model.addAttribute("pr", pr);

        Product prd = productDao.findById(id).get();
        ProductAdminDto prdto = new ProductAdminDto();
        prdto.setId(id);
        prdto.setName(prd.getProductName());
        prdto.setNote(prd.getNotes());
        model.addAttribute("prdto", prdto);
        return "system/products/product/addProduct";
    }

    // save product
    @PostMapping("admin/saveprd")
    public String saveProduct(@ModelAttribute("prdto") ProductAdminDto prdDto,
            Model model, BindingResult bindingResult,
            HttpSession session) {
        savePrdValidation.validate(prdDto, bindingResult);
        if (bindingResult.hasErrors()) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
            List<Trademark> td = iTrademarDao.findAll();
            model.addAttribute("td", td);
            List<Promotion> pr = promotionDao.findAll();
            model.addAttribute("pr", pr);
            return "system/products/product/addProduct";
        }

        Product prd = new Product();
        prd.setProductName(prdDto.getName());
        prd.setNotes(prdDto.getNote());
        prd.setPromotion(promotionDao.findById(prdDto.getPromotionId()).get());
        prd.setTrademark(iTrademarDao.findById(prdDto.getTrademarkId()).get());
        if (prdDto.getId() != 0) {
            prd.setProductId(prdDto.getId());
        }
        productDao.save(prd);

        return "redirect:/admin";
    }

    // product detail -------------------------//
    // list product detail
    @RequestMapping("/admin/productdetail")
    public String productDetail(HttpSession session, Model model, @RequestParam("id") int id) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        Product prd = productService.findById(id);
        model.addAttribute("prd", prd);
        List<ProductDetail> prdDt = prd.getProductDetail();
        model.addAttribute("prdDt", prdDt);
        return "system/products/productDetail/productDetail";
    }

    // add product detail
    @RequestMapping("/admin/addproductDt")
    public String addProductDetail(HttpSession session, Model model, @RequestParam("id") int id) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        List<Configurator> config = configDao.findAll();
        model.addAttribute("config", config);

        List<Color> color = colorDao.findAll();
        model.addAttribute("color", color);

        ProductDetailAdminDto prdDT = new ProductDetailAdminDto();
        prdDT.setProductId(id);
        model.addAttribute("prdDT", prdDT);
        return "system/products/productDetail/addProductDetail";
    }

    // edit product detail
    @RequestMapping("/admin/editdt")
    public String editProductDetail(HttpSession session, Model model, @RequestParam("id") int id) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        List<Configurator> config = configDao.findAll();
        model.addAttribute("config", config);

        List<Color> color = colorDao.findAll();
        model.addAttribute("color", color);

        ProductDetail prdDetail = productDetaiDao.findById(id).get();

        ProductDetailAdminDto prdDT = new ProductDetailAdminDto();
        prdDT.setAmount(String.valueOf(prdDetail.getAmount()));
        prdDT.setPrice(String.valueOf(prdDetail.getPrice()));
        prdDT.setProductDetailId(prdDetail.getProductDetailId());
        prdDT.setProductId(prdDetail.getProduct().getProductId());
        prdDT.setColorId(prdDetail.getColor().getColorId());
        prdDT.setConfigId(prdDetail.getConfigurator().getConfiguratorId());
        prdDT.setImage1(prdDetail.getImages().getImage1());
        model.addAttribute("prdDT", prdDT);
        return "system/products/productDetail/editProductDetail";
    }

    // save prodduct detail
    @PostMapping("admin/save")
    public String savePrd(@ModelAttribute("prdDT") ProductDetailAdminDto prdDto, Model model,
            BindingResult bindingResult, HttpSession session, @RequestParam("file1") MultipartFile file,
            @RequestParam("file2") MultipartFile file2) {
        prdValidation.validate(prdDto, bindingResult);
        if (bindingResult.hasErrors()) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
            List<Configurator> config = configDao.findAll();
            model.addAttribute("config", config);
            List<Color> color = colorDao.findAll();
            model.addAttribute("color", color);
            return "system/products/productDetail/addPrd";
        }

        Images image2 = new Images();
        image2.setImage6(imageService.uploadByteImage(file2));
        imageDao.save(image2);

        ProductDetail prdDt = new ProductDetail();
        prdDt.setAmount(Integer.parseInt(prdDto.getAmount()));
        prdDt.setPrice(Double.parseDouble(prdDto.getPrice()));
        prdDt.setColor(colorDao.findById(prdDto.getColorId()).get());
        prdDt.setConfigurator(configDao.findById(prdDto.getConfigId()).get());
        prdDt.setProduct(productService.findById(prdDto.getProductId()));
        prdDt.setImages(image2);

        if (prdDto.getProductDetailId() != 0) {
            prdDt.setProductDetailId(prdDto.getProductDetailId());
        }
        prdDt = productDetaiDao.save(prdDt);
        return "redirect:/admin/productdetail?id=" + prdDto.getProductId();
    }
    // save

    @PostMapping("admin/saves")
    public String savePrds(@ModelAttribute("prdDT") ProductDetailAdminDto prdDto,
            Model model, BindingResult bindingResult, HttpSession session,
            @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2) {

        prdValidations.validate(prdDto, bindingResult);
        if (bindingResult.hasErrors()) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
            List<Configurator> config = configDao.findAll();
            model.addAttribute("config", config);
            List<Color> color = colorDao.findAll();
            model.addAttribute("color", color);
            return "system/products/productDetail/addProductDetail";
        }

        ProductDetail prdDt = new ProductDetail();
        prdDt.setAmount(Integer.parseInt(prdDto.getAmount()));
        prdDt.setPrice(Double.parseDouble(prdDto.getPrice()));
        prdDt.setColor(colorDao.findById(prdDto.getColorId()).get());
        prdDt.setConfigurator(configDao.findById(prdDto.getConfigId()).get());
        prdDt.setProduct(productService.findById(prdDto.getProductId()));

        Images image;
        if (prdDto.getProductDetailId() != 0) {
            System.out.println("prdID: " +prdDto.getProductDetailId());
            image = imageDao.findById((productDetaiDao.findById(prdDto.getProductDetailId())).get().getImages().getImageId()).get();
        } else {
            image = new Images();
        }
        System.out.println("imageId " + image.getImageId());
        if (file1 != null) {
            byte[] image1 = imageService.uploadByteImage(file1);
            if (image1 != null && image1.length > 0) {
                image.setImage1(image1);
                imageDao.save(image);
                prdDt.setImages(image);
            }

        }
        if (file2 != null) {
            byte[] image2 = imageService.uploadByteImage(file2);
            if (image2 != null && image2.length > 0) {
                image.setImage2(image2);
                prdDt.setImages(image);
            }
        }

        if (prdDto.getProductDetailId() != 0) {
            prdDt.setProductDetailId(prdDto.getProductDetailId());
        }
        prdDt = productDetaiDao.save(prdDt);
        return "redirect:/admin/productdetail?id=" + prdDto.getProductId();
    }

    // delete detail product
    @GetMapping("/admin/deletedt")
    public String deleteDetailProduct(@RequestParam("id") int id) {

        ProductDetail prds = productDetaiDao.findById(id).get();
        int ids = prds.getProduct().getProductId();
        productDetaiDao.deleteById(id);

        return "redirect:/admin/productdetail?id=" + ids;

    }

    // add promotion
    @RequestMapping("/admin/product/addpromotion")
    public String addPromotion(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        Promotion promotion = new Promotion();
        model.addAttribute("promotion", promotion);

        return "system/products/product/addPromotion";
    }

    @RequestMapping("/admin/savepromotion")
    public String savePromotion(@ModelAttribute("promotion") Promotion promotion) {

        promotionDao.save(promotion);
        return "redirect:/admin/product";
    }

    // add trademerk
    @RequestMapping("/admin/trademark")
    public String trade(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<Trademark> trademark = iTrademarDao.findAll();
        model.addAttribute("trademark", trademark);

        return "system/products/product/trademark";
    }
    
    @RequestMapping("/admin/addTrademark")
    public String addTrade(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        Trademark trademark = new Trademark();
        model.addAttribute("trademark", trademark);

        return "system/products/product/addEditTrademark";
    }
    
    @RequestMapping("/admin/editTrademark")
    public String editTrade(HttpSession session, Model model, int id) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        Trademark trademark = iTrademarDao.findById(id).get();
        model.addAttribute("trademark", trademark);

        return "system/products/product/addEditTrademark";
    }

    @RequestMapping("/admin/saveTrademark")
    public String saveTrade(@ModelAttribute("trademark") Trademark trademark) {

        iTrademarDao.save(trademark);
        return "redirect:/admin/trademark";
    }
    
    @RequestMapping("/admin/deleteTrademark")
    @Transactional
    public String deleteTrade(int id) {

        Trademark trademark = iTrademarDao.findById(id).get();
        
        for(Product product : trademark.getProduct()){
            productDao.delete(product);
        }
        
        iTrademarDao.deleteById(trademark.getTrademarkId());
        return "redirect:/admin/trademark";
    }

    // ---------------------order---------------------//
    @RequestMapping("/admin/orders")
    public String order(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        model.addAttribute("user", user);

        return "system/order/list_order";
    }

    @RequestMapping("/admin/order")
    public String orders(Model model, HttpSession session, HttpServletRequest request) {
        int page = 0;
        int size = 7;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
        }
        User user = (User) session.getAttribute("user");
        Page<Orders> order = orderDao.findAll(PageRequest.of(page, size));
//		List<Order> order = orderDao.findAll();
        model.addAttribute("order", order);
        model.addAttribute("user", user);

        return "system/order/list_order";
    }

    @RequestMapping("/admin/searchs")
    public String searchs(HttpSession session, Model model, HttpServletRequest request,
            @RequestParam("status") String status,
            @RequestParam("begin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date begin,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {

        int page = 0;
        int size = 7;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
        }

        List<Orders> order = orderService.searchPrd(status, begin, end);
        long listSize = order.size();
        if (order == null) {
            listSize = 1;
        }
        Page<Orders> orde = new PageImpl<Orders>(order, PageRequest.of(page, size), listSize);

        model.addAttribute("order", orde);

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        return "system/order/list_order";
    }

    @RequestMapping("/admin/deleteorder")
    public String deleteOrder(@RequestParam("id") int id) {
        Orders or = orderDao.findById(id).get();

        if (or.getStatus().equals("delivered")) {
            List<OrderDetail> orDt = or.getOrderDetail();
            for (OrderDetail orderDetail : orDt) {
                orderDetailDao.deleteById(orderDetail.getOrderDetailId());
            }
            orderDao.deleteById(id);
        }

        return "redirect:/admin/order";
    }

    // update status
    @RequestMapping("/admin/statusorder")
    public String statusOrder(@RequestParam("id") int id) {
        Orders or = orderDao.findById(id).get();
        if (or.getStatus().equals("confirmed")) {
            or.setStatus("shipping");
            orderDao.save(or);
            return "redirect:/admin/order";
        }
        if (or.getStatus().equals("shipping")) {
            or.setStatus("delivered");
            orderDao.save(or);
            return "redirect:/admin/order";
        }
        if (or.getStatus().equals("delivered")) {
            or.setStatus("confirmed");
            orderDao.save(or);
            return "redirect:/admin/order";
        }
        return "redirect:/admin/order";

    }
    // order detail

    @RequestMapping("/admin/orderdetail")
    public String orderDetail(@RequestParam("id") int id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        Orders or = orderDao.findById(id).get();
        List<OrderDetail> orderDetail = or.getOrderDetail();
        model.addAttribute("orderDetail", orderDetail);
        return "system/order/list_order_detail";
    }

    @RequestMapping("/admin/productImage")
    public void productImage(HttpServletRequest request, HttpServletResponse response,
            Model model, @RequestParam("id") int id) throws IOException {
        ProductDto prDto = productDetailService.findProductDtoById(id);

        if (prDto != null && prDto.getImage1() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(prDto.getImage1());
        }

        if (prDto != null && prDto.getImage2() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(prDto.getImage2());
        }

        response.getOutputStream().close();
    }
}
