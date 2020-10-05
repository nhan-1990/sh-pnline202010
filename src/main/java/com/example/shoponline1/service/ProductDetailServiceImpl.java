package com.example.shoponline1.service;

import com.example.shoponline1.dao.IProductDao;
import com.example.shoponline1.dao.IProductDetailDao;
import com.example.shoponline1.dao.IProductDtoDao;
import com.example.shoponline1.dto.ProductDto;
import com.example.shoponline1.entity.ProductDetail;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailServiceImpl implements IProductDetailService {

    @Autowired
    private IProductDetailDao iProductDetailDao;

    @Autowired
    private IProductDao productDao;

    @Autowired
    private IProductDtoDao iProductDtoDao;

    public ProductDto getProductDto(ProductDto productDto, ProductDetail prdDetail) {
        double price = prdDetail.getPrice() - (prdDetail.getPrice() * prdDetail.getProduct().getPromotion().getDiscountvalue() / 100);
        productDto.setProductId(prdDetail.getProduct().getProductId());
        productDto.setProductDetailId(prdDetail.getProductDetailId());
        productDto.setConfiguratorId(prdDetail.getConfigurator().getConfiguratorId());
        productDto.setProductName(prdDetail.getProduct().getProductName());
        productDto.setTrademarkName(prdDetail.getProduct().getTrademark().getTrademarkName());
        productDto.setRom(prdDetail.getConfigurator().getRom());
        productDto.setPriceBefore(prdDetail.getPrice());
        productDto.setPriceAfter(price);
        productDto.setReducedPrice(prdDetail.getPrice() - price);
        productDto.setImage1(prdDetail.getImages().getImage1());
        productDto.setImage2(prdDetail.getImages().getImage2());
        productDto.setImage3(prdDetail.getImages().getImage3());
        productDto.setColor(prdDetail.getColor().getColorName());
        return productDto;
    }
    
    public Sort getSort(String sort){
        
        Sort sortable = null;
        switch (sort) {
            case "price: ASC":
                sortable = Sort.by("price").ascending();
                break;
            case "price: DESC":
                sortable = Sort.by("price").descending();
                break;
            case "productDetailId: DESC":
                sortable = Sort.by("productDetailId").descending();
                break;
        }
        return sortable;
    }

    @Override
    public List<ProductDto> findAllPrd() {
        List<ProductDetail> prdDetails = iProductDetailDao.findAll();
        List<ProductDto> prdDto = new ArrayList<ProductDto>();
        for (ProductDetail prdDetail : prdDetails) {
            ProductDto productDto = new ProductDto();
            this.getProductDto(productDto, prdDetail);
            prdDto.add(productDto);
        }
        return prdDto;
    }

    @Override
    public ProductDto findProductDtoById(int id) {
        List<ProductDetail> prdDetails = iProductDetailDao.findAll();
        ProductDto productDto = new ProductDto();
        for (ProductDetail prdDetail : prdDetails) {
            if (prdDetail.getProductDetailId() == id) {
                this.getProductDto(productDto, prdDetail);
            }
        }
        return productDto;
    }

    @Override
    public List<ProductDto> findListProductById(int id) {
        List<ProductDetail> prdDetails = iProductDetailDao.findAll();
        List<ProductDto> prdDto = new ArrayList<ProductDto>();
        for (ProductDetail prdDetail : prdDetails) {
            if (prdDetail.getProduct().getProductId() == id) {
                ProductDto productDto = new ProductDto();
                this.getProductDto(productDto, prdDetail);
                prdDto.add(productDto);
            }

        }
        return prdDto;
    }

    @Override
    public Page<ProductDto> findPaginated(int page, int size, String sort) {

        Sort sortable = this.getSort(sort);
        Page<ProductDto> productPage = iProductDtoDao.findAllDtoPage(PageRequest.of(page - 1, size, sortable));
        
        return productPage;
    }
    
    @Override
    public Page<ProductDto> bestSold(int page, int size) {
        
        Sort sortable = Sort.by("sold").descending();
        Page<ProductDto> productPage = iProductDtoDao.findAllDtoPage(PageRequest.of(page - 1, size, sortable));
        //Page<ProductDto> productPage = iProductDtoDao.bestSell(PageRequest.of(page - 1, size, sortable));
        
        return productPage;
    }

    @Override
    public Page<ProductDto> filter(int page, int size, String sort, String brandName1, String brandName2, double minPrice, double maxPrice) {

        Sort sortable = this.getSort(sort);
        
        Page<ProductDto> productPage;
        if (brandName1.isEmpty() && brandName2.isEmpty()) {
            productPage = iProductDtoDao.filterbyPrice(PageRequest.of(page - 1, size, sortable), minPrice, maxPrice);
        } else {
            productPage = iProductDtoDao.filter(PageRequest.of(page - 1, size, sortable), brandName1, brandName2, minPrice, maxPrice);
        }           
        
        return productPage;
    }

    @Override
    public Page<ProductDto> findByProductName(int page, int size, String sort, String name1, String name2) {
        
        Sort sortable = this.getSort(sort);
        Page<ProductDto> productPage = iProductDtoDao.findByName(PageRequest.of(page - 1, size, sortable), name1, name2);
        
        return productPage;
    }
}
