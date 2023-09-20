package com.jewelleryBasic.jwBasic.frontEndModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductInfoExpose {
	private Long pid;
	private String pname;
	 private String description;
    private String thumbnail;
    private String imageList;
    private Double basePrice;
    private Double discount;
    private Double sellPrice;
    private Integer stockQty;
   
  
}
