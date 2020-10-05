package com.example.shoponline1.service;

import com.example.shoponline1.dao.IProductDao;
import com.example.shoponline1.dto.ProductAdminDto;
import com.example.shoponline1.entity.Product;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public Product findById(int id) {
        return productDao.findById(id).get();
    }

    @Override
    public List<Product> findAllProduct() {

        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(Product product) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<ProductAdminDto> findAllProductDto() {
        List<Product> prd = productDao.findAll();
        List<ProductAdminDto> prdDto = new ArrayList<ProductAdminDto>();
        for (Product product : prd) {
            ProductAdminDto prdAdmin = new ProductAdminDto();
            prdAdmin.setId(product.getProductId());
            prdAdmin.setName(product.getProductName());
            prdAdmin.setPromotion(product.getPromotion().getPromotionName());
            prdAdmin.setTrademark(product.getTrademark().getTrademarkName());
            prdDto.add(prdAdmin);
        }
        return prdDto;
    }

}
