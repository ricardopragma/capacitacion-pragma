package com.familia.api.core.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.familia.api.core.business.ProductBusiness;
import com.familia.api.core.domain.ProductDTO;
import com.familia.api.core.domain.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/api/v1.0")
@CrossOrigin("**")
@Api(value = "/api/v1.0", tags = { "general" })
@SwaggerDefinition(tags = { @Tag(name = "general"
		+ "general", description = "RestController para todas las operaciones de este dominio") })
public class CoreRest {

	@Autowired
	private ProductBusiness productBusiness;

	@PostMapping(value = "/product", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(nickname = "create-product", value = "Permite crear un producto en el sistema", notes = "Crea un nuevo registro en la base de datos"
			+ " sobre la tabla PRODUCT, con base en el JSON recibido", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiParam(name = "product", value = "JSON con la información del producto", required = true, type = "ProductDTO")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "El producto fue creado exitosamente", response = Response.class),
			@ApiResponse(code = 400, message = "Error en la creación del producto, debido a un error en los datos de la petición recibida", response = Response.class),
			@ApiResponse(code = 500, message = "Error en la creación del producto, generado por un error en el servidor", response = Response.class) })
	public Response<Void> createProduct(@RequestBody ProductDTO product) {

		return productBusiness.createProduct(product);
	}
}
