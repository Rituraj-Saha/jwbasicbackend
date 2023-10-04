package com.jewelleryBasic.jwBasic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jewelleryBasic.jwBasic.model.UserInfo;

import jakarta.transaction.Transactional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{
	 Optional<UserInfo> findByPhoneNumber(String phoneNumber);
//	 UPDATE `jw_basic`.`user-info` SET `address_line` = 'test address2', `name` = 'boniTest', `pin_code` = '700112' WHERE (`uid` = '302');

	 @Transactional
	 @Modifying
	 @Query(nativeQuery = true,value="UPDATE `jw_basic`.`user-info` SET `address_line` = :address_line, `email` = :email, `name` = :name, `pin_code` = :pin_code, `state` = :state WHERE (`phone_number` = :phone_number);")
	 void userUpdate(@Param("phone_number") String phn,
			 @Param("address_line") String address,
			 @Param("email")String email,
			 @Param("name")String name,
			 @Param("pin_code") String pin_code,
			 @Param("state")String state);

}
