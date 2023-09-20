package com.jewelleryBasic.jwBasic.frontEndModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//not used for now
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseRequest {
	Long pid;
	int requestedStock;
}
