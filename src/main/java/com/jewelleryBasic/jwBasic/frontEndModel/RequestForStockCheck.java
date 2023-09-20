package com.jewelleryBasic.jwBasic.frontEndModel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//not used for now
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestForStockCheck {
	List<ProductPurchaseRequest> productPurchaseReqests;
}
