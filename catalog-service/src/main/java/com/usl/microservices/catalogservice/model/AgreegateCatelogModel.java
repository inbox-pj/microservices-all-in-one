package com.usl.microservices.catalogservice.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AgreegateCatelogModel {

	private List<ProductCatelogModel> agreegateModel;

	public List<ProductCatelogModel> getAgreegateModel() {
		if (agreegateModel == null) {
			return Collections.emptyList();
		}
		return agreegateModel;
	}

	public void setAgreegateModel(List<ProductCatelogModel> agreegateModel) {
		this.agreegateModel = agreegateModel;
	}

	public void addProductCatelog(ProductCatelogModel productCatelog) {
		if (agreegateModel == null) {
			agreegateModel = new ArrayList<ProductCatelogModel>(1);
		}

		agreegateModel.add(productCatelog);
	}
}
