package com.familia.api.core.domain;

import java.io.Serializable;

public class ProductDTO implements Serializable {

	/** Serializes */
	private static final long serialVersionUID = -4911762629965132125L;

	private Long id;
	private String name;
	private String code;
	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductDTO [name=" + name + ", code=" + code + ", price=" + price + "]";
	}

}
