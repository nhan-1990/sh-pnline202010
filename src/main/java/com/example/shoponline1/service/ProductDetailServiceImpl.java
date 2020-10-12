package com.example.shoponline1.service;

import com.example.shoponline1.dao.IProductDao;
import com.example.shoponline1.dao.IProductDetailDao;
import com.example.shoponline1.dao.IProductDtoDao;
import com.example.shoponline1.dto.ProductDto;
import com.example.shoponline1.entity.ProductDetail;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        productDto.setSold(prdDetail.getSold());
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
        
        ProductDetail productDetails = iProductDetailDao.findById(id).get();
        ProductDto productDto = new ProductDto();
        this.getProductDto(productDto, productDetails);
        
        return productDto;
    }

    @Override
    public List<ProductDto> findListProductById(int id) {
        
        List<ProductDetail> productDetail = iProductDetailDao.findAll();        
        List<ProductDto> productDtos = new ArrayList<>();
        for (ProductDetail prDe : productDetail) {
            if(prDe.getProduct().getProductId() == id){
                ProductDto productDto = new ProductDto();
                this.getProductDto(productDto, prDe);
                productDtos.add(productDto);
            }
        }
        return productDtos;
    }

    @Override
    public Page<ProductDto> findPaginated(int page, int size, String sort) {

        Sort sortable = this.getSort(sort);
        Page<ProductDto> productPage = iProductDtoDao.findAllDtoPage(PageRequest.of(page - 1, size, sortable));
        
        return productPage;
    }
        
    @Override
    public List<ProductDto> bestSell(){
        List<ProductDetail> productDetails = iProductDetailDao.findBestSeller();
        List<ProductDto> productDtos = new ArrayList<>();
        for (ProductDetail productDetail : productDetails) {
            ProductDto productDto = new ProductDto();
            this.getProductDto(productDto, productDetail);
            productDtos.add(productDto);
        }
        return productDtos;
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
    
    @Override
    public List<ProductDto> findRelated(String name1, String name2, int id){        
        
        List<ProductDto> productPage = iProductDtoDao.findRelated(name1, name2, id);
        
        return productPage;
    }
}
