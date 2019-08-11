package com.familia.api.core.business;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familia.api.core.domain.ProductDTO;
import com.familia.api.core.domain.Response;
import com.familia.api.core.exception.CoreException;
import com.familia.api.core.model.Product;
import com.familia.api.core.repository.ProductRepository;
import com.familia.api.core.util.ValidatorUtil;

@Service
public class ProductBusinessImpl implements ProductBusiness {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Response<Void> createProduct(ProductDTO productDto) {

		Response<Void> response = new Response<>();

		try {
			// Ejemplo para errores de tipo BadRequest
			verifyProductInfo(productDto);

			// Ejemplo para errores de tipo InternalServerError
			simulateInternalServerError(productDto);

			Product product = new Product();
			BeanUtils.copyProperties(productDto, product);

			productRepository.save(product);

			response.setState(200);
			response.setUserMessage("Producto creado");
			response.setDeveloperMessage("Producto creado");
			response.setMoreInfo("http://example.com/more-info/product");
		} catch (CoreException e) {
			response.setState(e.getState());
			response.setUserMessage(e.getUserMessage());
			response.setDeveloperMessage(e.getMessage());
			response.setMoreInfo("http://example.com/more-info/product");
		}

		return response;
	}

	private void verifyProductInfo(ProductDTO product) throws CoreException {
		if (ValidatorUtil.isObjectNull(product) || ValidatorUtil.isNullOrEmpty(product.getCode())
				|| ValidatorUtil.isNullOrEmpty(product.getName()) || ValidatorUtil.isObjectNull(product.getPrice())) {
			throw new CoreException("Los datos recibidos en el body de la peticion son incorrectos",
					"Los datos del producto son inválidos, por favor verifique", 400);
		}
	}

	private void simulateInternalServerError(ProductDTO product) throws CoreException {
		// Suponiendo que se tenga alguna validación de negocio donde puedan surgir
		// excepciones no chequeadas dentro de las operaciones realizadas por el
		// servidor
		if (product.getPrice() == 0) {
			throw new CoreException("Error durante un calculo en la transacción",
					"Error durante la transacción, por favor intente más tarde", 500);
		}
	}
}
