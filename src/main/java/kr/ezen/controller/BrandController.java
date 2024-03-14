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

import kr.ezen.service.BrandService;
import kr.ezen.winia.domain.BrandDTO;

@Controller
public class BrandController {
	
	// 자동연결
	@Autowired // DI(Dependency Injection : 의존성 주입)
	// private MemberDAO dao;
	// DAO 대신 Mapper대신 Service 사용
	private BrandService service;
	
	// 브랜드 등록 폼으로 이동 - Get방식
	@GetMapping("/brandInput.do")
	public String brandInputForm() {
		return "brand/brand_input";
	}
	
	// 브랜드 등록 OK - Post방식
	@PostMapping("/brandOk.do")
	// 인터셉터 발생시, 무브url의 마지막 주소를 넘겨줌 ==> 로그인폼, 로그인폼OK로 넘겨줌
	public String insertBrand(BrandDTO dto, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {	// moveURL 넘길때 코드
	      // 유효성 검사
	      if(dto.getBrand_code() == null || dto.getBrand_name() == null || 
	    		  dto.getBrand_code().trim().equals("") || dto.getBrand_name().trim().equals("")) {
	    	 request.setAttribute("msg", "브랜드 등록 실패");
	         return "brand/brand_input";
	      }
	      
	      redirectAttributes.addFlashAttribute("msg", "브랜드 등록 완료"); // addFlash만 넘어감 requestscope.msg랑 msg만 받아짐 // // param.msg는 안받아짐
	      // redirectAttributes.addAttribute("msg", "브랜드 등록 완료"); // redirect: 라서 request가 안넘어감, requestscope.msg랑 msg는 안받아짐 // param.msg 로만 받아짐
	      // model.addAttribute("msg", "브랜드 등록 완료"); // redirect: 라서 request가 안넘어감, requestscope.msg랑 msg는 안받아짐 // param.msg 로만 받아짐
	      // request.setAttribute("msg", "브랜드 등록 완료"); // redirect: 라서 request가 안넘어감 // 세종류다 안넘어감
	      service.insertBrand(dto);
	      return "redirect:brandList.do";
	}

	// 브랜드 리스트 띄우기
	@RequestMapping("/brandList.do")
	public String brandList(Model model) {
		ArrayList<BrandDTO> brandList = service.brandList();
		
		model.addAttribute("list", brandList);
		return "/brand/brand_list";
	}
	
	// 브랜드 리스트에서 삭제OK
	@RequestMapping("/brandDelete.do")
	public String brandDelete(int brand_num) {
//		int cnt = dao.memberDelete(no);  // cnt는 값을가지고 추가로 어떠한 작업을 하고 싶을때 추가(유효성검사 등)
		service.brandDelete(brand_num);  // cnt는 값을가지고 추가로 어떠한 작업을 하고 싶을때 추가(유효성검사 등)
		return "redirect:brandList.do";
	}
	
	// 브랜드 정보 폼으로 가기 (info)
	@RequestMapping("/brandUpdate.do") // Model model은 바인딩
	public String brandUpdate(int brand_num, Model model) {
		BrandDTO dto = service.brandUpdate(brand_num);
		model.addAttribute("dto", dto);
		return "/brand/brand_update";
	}
	
	// 브랜드 정보(인포)폼에서 수정OK 이동
	@RequestMapping("/brandUpdateOk.do")
	public String brandUpdateOk(BrandDTO dto, RedirectAttributes redirectAttributes) {
		if(dto.getBrand_code() == null || dto.getBrand_name() == null || 
	    		  dto.getBrand_code().trim().equals("") || dto.getBrand_name().trim().equals("")) {
			redirectAttributes.addFlashAttribute("msg", "브랜드 수정실패!!");
	        return "redirect:brandList.do";
	    }
		
		redirectAttributes.addFlashAttribute("msg", "브랜드 수정완료!!");
		service.brandUpdateOk(dto);
		return "redirect:brandList.do";
	}
	
}
