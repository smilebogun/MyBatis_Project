package kr.ezen.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ezen.winia.domain.CartDTO;
import kr.ezen.winia.domain.CategoryDTO;
import kr.ezen.winia.mapper.CartMapper;
import kr.ezen.winia.mapper.CategoryMapper;


@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper mapper;
	
	@Autowired // root-context에서 생성된 Bean class 를 주입받기 // servlet에서 생성된건 받지 못함
	JavaMailSender mailSender;
	
	@Autowired	// 암호화를위해서 root 추가한 pwEncoder에서 주입받음
	private BCryptPasswordEncoder pwEncoder;
	
	// 카트 전체 리스트
	@Override // 페이징처리
	public ArrayList<CartDTO> cartList(String id) {
		return mapper.cartList(id); 
	}
	
	// 카트 등록OK
	@Override
	public void addCart(CartDTO dto) {
		mapper.addCart(dto);
	}
	
	// 카트 리스트 체크(상품이 있는지 없는지)
	@Override // public "BoardDTO" 뒤에는 리턴값 // 즉, 리턴은 BoardDTO 값
	public CartDTO checkCart(CartDTO dto) {
		return mapper.checkCart(dto);
	}

	// 카트 삭제
	@Override
	public void deleteCart(int cart_num) {
		mapper.deleteCart(cart_num);
	}
	
	// 카트 업데이트
	@Override
	public void modifyCart(CartDTO dto) {
		mapper.modifyCart(dto);
	}
	
	// 카트 삭제
	@Override
	public void deleteCheckout(int pnum, String id) {
		mapper.deleteCheckout(pnum, id);
	}

}
