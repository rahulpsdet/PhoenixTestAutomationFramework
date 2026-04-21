package com.api.utils;

import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAdress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.dataproviders.api.bean.CreateJobBean;

public class CreateJobBeanMapper {

	
	private CreateJobBeanMapper() {
		
	}
	public static CreateJobPayload mapper(CreateJobBean bean) {
		
		int mstServiceLocationID = Integer.parseInt(bean.getMst_service_location_id());
		int mstPlateformID = Integer.parseInt(bean.getMst_platform_id());
		int mstOemID = Integer.parseInt(bean.getMst_oem_id());
		int mstWarrentyStatusID = Integer.parseInt(bean.getMst_warrenty_status_id());
		
		Customer customer = new Customer(bean.getCustomer__first_name(), bean.getCustomer__last_name(), 
				bean.getCustomer__mobile_number(), bean.getCustomer__mobile_number_alt(), 
				bean.getCustomer__email_id(), bean.getCustomer__email_id_alt());
		
		CustomerAdress customerAdress = new CustomerAdress(bean.getCustomer_address__flat_number(), bean.getCustomer_address__apartment_name(),
				bean.getCustomer_address__street_name(), 
				bean.getCustomer_address__landmark(),
				bean.getCustomer_address__area(), bean.getCustomer_address__pincode(),
				bean.getCustomer_address__country(),bean.getCustomer_address__state());
		
		int productid = Integer.parseInt(bean.getCustomer_product__product_id());
		int modelid = Integer.parseInt(bean.getCustomer_product__mst_model_id());
		CustomerProduct customerProduct = new CustomerProduct(bean.getCustomer_product__dop(),
				bean.getCustomer_product__serial_number(), 
				bean.getCustomer_product__imei1(), 
				bean.getCustomer_product__imei2(),
				bean.getCustomer_product__popurl(), 
				productid,
				modelid);
		
		List<Problems> problemList = new ArrayList<Problems>();
		int problemsId = Integer.parseInt(bean.getProblems__id());
		Problems problem = new Problems(problemsId,bean.getProblems__remark());
		
		problemList.add(problem);
		
		CreateJobPayload payload= new CreateJobPayload(mstServiceLocationID, mstPlateformID, 
				mstWarrentyStatusID, mstOemID,customer, customerAdress, customerProduct, problemList);
		return payload;
	}
	
}
