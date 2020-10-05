package com.example.shoponline1.validation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.shoponline1.dao.IColorDao;
import com.example.shoponline1.dao.IConfigDao;
import com.example.shoponline1.dto.ProductDetailAdminDto;
import com.example.shoponline1.entity.Product;
import com.example.shoponline1.entity.ProductDetail;
import com.example.shoponline1.service.IProductService;





@Component
public class productAdminValidations implements Validator {
	
	
	@Autowired
	private IConfigDao configDao;
	
	@Autowired
	private IColorDao colorDao;
	
	@Autowired IProductService productService;

	@Override
	public boolean supports(Class<?> aClass) {

		return ProductDetail.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ProductDetailAdminDto prd = (ProductDetailAdminDto) o;
		Product prds = productService.findById(prd.getProductId());
		List<ProductDetail> prdDt = prds.getProductDetail();
	

		
		
		if (!String.valueOf(prd.getAmount()).matches("-?\\d+(\\.\\d+)?")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amouts", "erros", "chỉ được nhập số");
		}
		if (!String.valueOf(prd.getPrice()).matches("-?\\d+(\\.\\d+)?")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prices", "erros", "chỉ được nhập số");
		}
		

		if (prd.getPrice() != null && prd.getAmount()!=null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "NotEmpty", "NotEmpty");
		}
		
		
		
		else {
			errors.rejectValue("price", "Null","Null");
		}
		
	}

}
