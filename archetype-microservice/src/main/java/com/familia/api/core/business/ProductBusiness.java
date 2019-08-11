package com.familia.api.core.business;

import com.familia.api.core.domain.ProductDTO;
import com.familia.api.core.domain.Response;

public interface ProductBusiness {

	Response<Void> createProduct(ProductDTO productDto);

}
