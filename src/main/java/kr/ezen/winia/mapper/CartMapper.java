package kr.ezen.winia.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ezen.winia.domain.CartDTO;
import kr.ezen.winia.domain.CategoryDTO;

// @Mapper 
// DAO대신 Mapper 인터페이스로 사용하기 @Mapper 생략가능
public interface CartMapper {
	
	// 카트 리스트 (조회하는게 dto랑 resultType="CategoryDTO" 이 쓰임)
	ArrayList<CartDTO> cartList(String id);
	
	// 카트 등록
	void addCart(CartDTO dto);
	
	// 카트 리스트 체크(상품이 있는지 없는지)
	CartDTO checkCart(CartDTO dto);

	// 카트 삭제
	void deleteCart(int cart_num);
	
	// 카트 수정OK
	void modifyCart(CartDTO dto);
	
	// 구매페이지에서 카트 삭제
	void deleteCheckout(@Param("pnum") int pnum, @Param("id") String id);

}
