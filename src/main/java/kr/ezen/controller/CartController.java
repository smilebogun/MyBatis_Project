package kr.ezen.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.ezen.service.CartService;
import kr.ezen.service.CategoryService;
import kr.ezen.service.MemberService;
import kr.ezen.winia.domain.CartDTO;
import kr.ezen.winia.domain.CategoryDTO;
import kr.ezen.winia.domain.MemberDTO;


@Controller
public class CartController {
	
	// 자동연결
	@Autowired // DI(Dependency Injection : 의존성 주입)
	// private MemberDAO dao;
	// DAO 대신 Mapper대신 Service 사용
	private CartService service;
	
	@Autowired
	private MemberService memberService;
	
	// 카테고리 리스트 띄우기
	@RequestMapping("/cartList.do")
	public String cartList(HttpSession session) {
//		HttpSession session = request.getSession();
//		String id = (String)session.getAttribute("loginDTO");
//		System.out.println(id);
		
		MemberDTO dto = (MemberDTO)session.getAttribute("loginDTO");
		String id = dto.getId();

		ArrayList<CartDTO> cartList = service.cartList(id);
		
		for(CartDTO cdto : cartList) {
			cdto.setTotal();
		}
		// checkout.jsp - 구매하기 페이지에서도 사용가능
		session.setAttribute("dtos", cartList); 
		//model.addAttribute("dtos", cartList);
		return "/product/cart_list";
	}
	
	// 카트 등록 OK - Post방식
	@RequestMapping("/addCart.do")
	// 인터셉터 발생시, 무브url의 마지막 주소를 넘겨줌 ==> 로그인폼, 로그인폼OK로 넘겨줌
	public String addCart(CartDTO dto, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
//		HttpSession session = request.getSession();
//		String id = (String)session.getAttribute("loginDTO");
//		System.out.println(id);
//		dto.setId(id);
		
		MemberDTO mdto = (MemberDTO)session.getAttribute("loginDTO");
//		System.out.println(mdto.getId());
		dto.setId(mdto.getId());
//			System.out.println(dto.getId());
//			System.out.println(dto);
		CartDTO ckDTO = service.checkCart(dto);
//		System.out.println(ckDTO);

		if(ckDTO == null) {
			service.addCart(dto);
			redirectAttributes.addFlashAttribute("msg", "장바구니 등록 완료");
		} else {
			dto.setPqty(ckDTO.getPqty() + 1);
//			System.out.println(dto.getPqty());
			dto.setCart_num(ckDTO.getCart_num());
//			System.out.println(dto.getCart_num());
			service.modifyCart(dto);
//				System.out.println(ckDTO.getPqty());
//				System.out.println(ckDTO);
			redirectAttributes.addFlashAttribute("msg", "장바구니 수량추가");
		}

	    return "redirect:cartList.do";
	}
	
	// 카트 리스트에서 삭제OK
	@RequestMapping("/deleteCart.do")
	public String deleteCart(int cart_num, RedirectAttributes redirectAttributes) {
//		int cnt = dao.memberDelete(no);  // cnt는 값을가지고 추가로 어떠한 작업을 하고 싶을때 추가(유효성검사 등)
		service.deleteCart(cart_num);  // cnt는 값을가지고 추가로 어떠한 작업을 하고 싶을때 추가(유효성검사 등)
		redirectAttributes.addFlashAttribute("msg", "장바구니 삭제 완료");
		return "redirect:cartList.do";
	}
	
	// 카트 정보(인포)폼에서 수정OK 이동
	@RequestMapping("/modifyCart.do")
	public String modifyCart(CartDTO dto, RedirectAttributes redirectAttributes) {
		service.modifyCart(dto);
		redirectAttributes.addFlashAttribute("msg", "장바구니 수정완료");
		return "redirect:cartList.do";
	}
	
	// 구매하기 폼으로 가기(info)
	@RequestMapping("/checkout.do") // Model model은 바인딩
	public String checkout(Model model, HttpSession session, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		//HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("loginDTO");
		int no = mdto.getNo();
//		System.out.println(no);
		
		MemberDTO mDto = memberService.memberInfo(no);
		model.addAttribute("mDto", mDto);
		request.setAttribute("msg", "구매 페이지로 이동합니다.");
		return "/product/checkout";
	}
	
	// 구매폼 리스트에서 삭제OK
	@RequestMapping("/deleteCheckout.do")
	public String deleteCheckout(String pnum, String id, HttpSession session, 
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		MemberDTO mdto = (MemberDTO)session.getAttribute("loginDTO");
		id = mdto.getId();
		
		   // 장바구니에서 넘어오는 값을 담는 변수 설정
		   pnum = null;
		
		   // '구매하기'에서 삭제할 상품번호 확인 "5/7/6...
		   String pnums = request.getParameter("delPnums");
		   System.out.println("pnums : "+pnums);
		   
		   ////////////////// '구매하기' 페이지에서 '삭제버튼'시
		   if(pnums == null) {
			   if(request.getMethod().equals("POST")) {		// 보안
				   pnum = request.getParameter("pnum");
			   }
			   if(pnum == null || pnum.trim().equals("")) {		// 보안
				   request.setAttribute("msg", "잘못된 경로 입니다.");
				   return "redirect:userMainForm.do";
			   }
			      // DB에서 리스트 삭제 
			   service.deleteCheckout(Integer.valueOf(pnum), id);	// cartDAO로 리턴
			   // id를 세션으로 묵으면 결제하기페이지에서 소환가능
			   ArrayList<CartDTO> cartList = service.cartList(id);
			   // 토탈 setTotal() 함수 호출하기 (합계구하는 함수)
			   for(CartDTO cDto : cartList) {
				   cDto.setTotal(); // 수량만큼 totPrice, totPoint 계산
			   }
			   //바인딩
			   session.setAttribute("dtos", cartList);
			   redirectAttributes.addFlashAttribute("msg", "장바구니1 삭제 완료");
			   return "redirect:checkout.do"; // '장바구니리스트'로 리턴
		   }
		   
		////////////////// '구매하기'페이지에서 선택? 삭제시
		   
		String[] numArr = pnums.split("/"); // 구분자로 값을 꺼내서 배열에 넣기 [5,7,6...]
		
		for(int i=0; i<numArr.length; i++) {
			service.deleteCheckout(Integer.valueOf(numArr[i]), id); // cartDAO로 리턴
		}
		
		// id를 세션으로 묵으면 결제하기페이지에서 소환가능
		ArrayList<CartDTO> cartList = service.cartList(id);
		
		// 토탈 setTotal() 함수 호출하기 (합계구하는 함수)
		for(CartDTO cDto : cartList) {
		   cDto.setTotal(); // 수량만큼 totPrice, totPoint 계산
		}
		
		//바인딩
		session.setAttribute("dtos", cartList);   
		redirectAttributes.addFlashAttribute("msg", "장바구니2 삭제 완료");
		return "redirect:checkout.do";
	}
	
}
