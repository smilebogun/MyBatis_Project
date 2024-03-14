package kr.ezen.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import kr.ezen.winia.domain.CartDTO;
import kr.ezen.winia.domain.CategoryDTO;

@Service
public interface CartService {
	
	// 카트 전체 리스트 가져오기
	ArrayList<CartDTO> cartList(String id);
	
	// 카트 등록OK
	void addCart(CartDTO dto);
	
	// 카트 리스트 체크(상품이 있는지 없는지)
	CartDTO checkCart(CartDTO dto);
	
	// 카트 삭제하기
	void deleteCart(int cart_num);
	
	// 카트 수정OK
	void modifyCart(CartDTO dto);
	
	// 구매페이지에서 카트 삭제
	void deleteCheckout(int pnum, String id);
	
}
