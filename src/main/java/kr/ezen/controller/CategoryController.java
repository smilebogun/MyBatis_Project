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

import kr.ezen.service.CategoryService;
import kr.ezen.winia.domain.CategoryDTO;


@Controller
public class CategoryController {
	
	// 자동연결
	@Autowired // DI(Dependency Injection : 의존성 주입)
	// private MemberDAO dao;
	// DAO 대신 Mapper대신 Service 사용
	private CategoryService service;
	
	// 카테고리 등록 폼으로 이동 - Get방식
	@GetMapping("/catInput.do")
	public String catInputForm() {
		return "category/category_input";
	}
	
	// 카테고리 등록 OK - Post방식
	@PostMapping("/categoryOk.do")
	// 인터셉터 발생시, 무브url의 마지막 주소를 넘겨줌 ==> 로그인폼, 로그인폼OK로 넘겨줌
	public String insertCategory(CategoryDTO dto, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {	// moveURL 넘길때 코드
	      // 유효성 검사
	      if(dto.getCode() == null || dto.getCat_name() == null || 
	    		  dto.getCode().trim().equals("") || dto.getCat_name().trim().equals("")) {
	    	 request.setAttribute("msg", "카테고리 등록 실패");
	         return "category/category_input";
	      }
	      
	      redirectAttributes.addFlashAttribute("msg", "카테고리 등록 완료"); // addFlash만 넘어감 requestscope.msg랑 msg만 받아짐 // // param.msg는 안받아짐
	      // redirectAttributes.addAttribute("msg", "카테고리 등록 완료"); // redirect: 라서 request가 안넘어감, requestscope.msg랑 msg는 안받아짐 // param.msg 로만 받아짐
	      // model.addAttribute("msg", "카테고리 등록 완료"); // redirect: 라서 request가 안넘어감, requestscope.msg랑 msg는 안받아짐 // param.msg 로만 받아짐
	      // request.setAttribute("msg", "카테고리 등록 완료"); // redirect: 라서 request가 안넘어감 // 세종류다 안넘어감
	      service.insertCategory(dto);
	      return "redirect:catList.do";
	}

	// 카테고리 리스트 띄우기
	@RequestMapping("/catList.do")
	public String categoryList(Model model) {
		ArrayList<CategoryDTO> categoryList = service.categoryList();
		
		model.addAttribute("list", categoryList);
		return "/category/category_list";
	}
	
	// 카테고리 리스트에서 삭제OK
	@RequestMapping("/catDelete.do")
	public String categoryDelete(int cat_num) {
//		int cnt = dao.memberDelete(no);  // cnt는 값을가지고 추가로 어떠한 작업을 하고 싶을때 추가(유효성검사 등)
		service.categoryDelete(cat_num);  // cnt는 값을가지고 추가로 어떠한 작업을 하고 싶을때 추가(유효성검사 등)
		return "redirect:catList.do";
	}
	
	// 카테고리 정보 폼으로 가기 (info)
	@RequestMapping("/catUpdate.do") // Model model은 바인딩
	public String catUpdate(int cat_num, Model model) {
		CategoryDTO dto = service.catUpdate(cat_num);
		model.addAttribute("dto", dto);
		return "/category/category_update";
	}
	
	// 카테고리 정보(인포)폼에서 수정OK 이동
	@RequestMapping("/catUpdateOk.do")
	public String catUpdateOk(CategoryDTO dto, RedirectAttributes redirectAttributes) {
		if(dto.getCode() == null || dto.getCat_name() == null || 
	    		  dto.getCode().trim().equals("") || dto.getCat_name().trim().equals("")) {
			redirectAttributes.addFlashAttribute("msg", "카테고리 수정실패!!");
	        return "redirect:catList.do";
	    }
		
		redirectAttributes.addFlashAttribute("msg", "카테고리 수정완료!!");
		service.catUpdateOk(dto);
		return "redirect:catList.do";
	}
	
}
