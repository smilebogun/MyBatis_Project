package kr.ezen.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.ezen.service.BrandService;
import kr.ezen.service.CategoryService;
import kr.ezen.service.ProductService;
import kr.ezen.winia.domain.BrandDTO;
import kr.ezen.winia.domain.CategoryDTO;
import kr.ezen.winia.domain.ProductDTO;
import kr.ezen.winia.util.ProdSpec;


@Controller
public class ProductController {
	
	// 자동연결
	@Autowired // DI(Dependency Injection : 의존성 주입)
	// private MemberDAO dao;
	// DAO 대신 Mapper대신 Service 사용
	private ProductService service;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BrandService brandService;
	
	// 상품등록 폼으로 이동 - Get방식
	@GetMapping("/prodInput.do")
	public String ProductInputForm(Model model) {
		
		// 카테고리 리스트 뷰에 넘겨주기
		ArrayList<CategoryDTO> categoryList = categoryService.categoryList();			
		model.addAttribute("categorylist", categoryList);
		// 브랜드 리스트 뷰에 넘겨주기
		ArrayList<BrandDTO> brandList = brandService.brandList();
		model.addAttribute("brandlist", brandList);
		// 상품스펙(열거형 상수 - enum) 가져오기
		ProdSpec[] pdSpecs = ProdSpec.values();
		model.addAttribute("pdSpecs", pdSpecs); // 바인딩
		
		return "product/prod_input";
	}

	// 상품 등록 OK - Post방식
	@PostMapping("/prodRegisterOk.do")
	// 인터셉터 발생시, 무브url의 마지막 주소를 넘겨줌 ==> 로그인폼, 로그인폼OK로 넘겨줌
	public String insertProduct(MultipartHttpServletRequest mhr, 
			HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {	
		
		// 파일 저장할 경로
		String repository = "resources/fileRepository";
		
		// 파일을 저장할 실제 서버의 물리경로 얻어오기
		// OS에 따라 \\(윈도우), /(리눅스)
		String savePath = request.getServletContext().getRealPath("")+File.separator+repository;
		System.out.println("savePath : "+ savePath);
		
		////// 불러온 파일들을 모델로 바인딩해서 맵에 저장 이후 fileUploadResult에 출력
		Map map = new HashMap();
		
		// 1. ***************** 넘길 정보들을 얻어오기 
		// MultipartHttpServletRequest mhr은 일반 텍스트, 바이너리 파일 정보를 모두 얻어 올 수 있는 객체.
		// 뷰딴 인풋의 name 속성의 값인 id와 name = 파라미터값을 가져옴
		Enumeration<String> enu = mhr.getParameterNames();
		// Enumeration 객체 사용
		
		while(enu.hasMoreElements()) {
			// 뷰딴 인풋의 name 속성의 값인 id와 name을 가져오기
			String paramName = enu.nextElement();
			// 해당 파라미터명의 값, input의 value값 (사용자가 입력한 값)
			String paramValue = mhr.getParameter(paramName);
								// id, name : test, 홍길동
			System.out.println(paramName + " : " + paramValue);
		////// 불러온 파일들을 모델로 바인딩해서 맵에 저장 이후 fileUploadResult에 출력			
		map.put(paramName, paramValue);
		}
		//  *** enu 여기까지가 아래 값들 넘겨줌 ***
		//		pnum : auto_increment
		//		pqty : 2
		//		pcontent : 123
		//		pname : 자바
		//		price : 20000
		//		pcategory_fk : a002
		//		pcompany : 1234
		//		pspec : RECOMMEND
		//		point : 200
		
//		fileParamName : pimage
//		originName : dTree.png
		
		// 2. ***************** 첨부 파일정보 얻어오기 Iterator 객체사용
		Iterator<String> iter = mhr.getFileNames();
////// 불러온 이미지 어레이리스트로 바인딩해서 어레이리스트로 저장 이후 fileUploadResult에 출력
// List<String> fileList = new ArrayList<String>();		// 사진리스트로 안가져오고 pimage만 가져오니까 위 구문 주석

		while(iter.hasNext()) {
			// input의 name값을 가져오기
			String fileParamName = iter.next();
			System.out.println("fileParamName : "+ fileParamName);
			
			// MultipartFile : 파일정보를 갖고 있는 객체
			MultipartFile mFile = mhr.getFile(fileParamName);
			// 첨부된 파일명
			String originName = mFile.getOriginalFilename();
			System.out.println("originName : "+ originName);
			
			// ~ \day05_fileUpDown\\resources/fileRepository/file1
			// 물리경로\\해당파일이름 을 file 주소로 넣어놓기
			File file = new File(savePath+"\\"+fileParamName);
			
			if(mFile.getSize() != 0) { // 사이즈가 0이 아니면 업로드 된경우 (폴더가 존재한 경우)
				if(!file.exists()) { // 폴더가 존재하지만 파일이 존재하지 않으면
					//file.createNewFile();
					if(file.getParentFile().mkdir()) {	// file1의 부모경로로 가서 폴더를 만들고
						file.createNewFile(); // throws 예외처리 진행, 임시파일 생성 // 파일을 만든다
					} // if3
				}	// if2
				
				// 파일이 존재하면
				File uploadFile = new File(savePath+"\\"+originName);
				
				// 중복시 파일명 대체
				if(uploadFile.exists()) {	// 시간을 기준으로 다시 재저장
					originName = System.currentTimeMillis()+"_"+originName;
					uploadFile = new File(savePath+"\\"+originName);
				}
				// 실제 파일 업로드
				mFile.transferTo(uploadFile);
				
				// 파일명을 list에 추가
				//////불러온 이미지 어레이리스트로 바인딩해서 어레이리스트로 저장 이후 fileUploadResult에 출력
				// fileList.add(originName);
			
			}	// if1
			
			// 파일명을 저장한 리스트를 map에 추가
			////// 불러온 파일들을 모델로 바인딩해서 맵에 저장 이후 fileUploadResult에 출력	
			// map.put("fileList", fileList);
			map.put("pimage", originName);
		} // while
		
		// 서비스로 보냄
		service.insertProduct(map);
		redirectAttributes.addFlashAttribute("msg", "상품 등록 완료!!");
		
		return "redirect:/prodList.do";
	} // fileUploadOk

	
	// 상품 리스트 띄우기
	@RequestMapping("/prodList.do")
	public String productList(Model model) {
		ArrayList<ProductDTO> productList = service.productList();
		model.addAttribute("list", productList);
		return "product/prod_list";
	}
	
	// 상품 리스트에서 삭제OK
	@RequestMapping("/deleteProduct.do")
	public String deleteProduct(int pnum, String pimage, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		// 파일 저장할 경로
		String repository = "resources/fileRepository";
		
		// 파일을 저장할 실제 서버의 물리경로 얻어오기
		// OS에 따라 \\(윈도우), /(리눅스)
		String savePath = request.getServletContext().getRealPath("")+File.separator+repository;
		File delFile = null;
		
		 if(pimage != null) {
			  delFile = new File(savePath + "\\" + pimage); //(savePath + "/" + pimage); 경로를 "/" 써줘도 됌
			  if(delFile.exists()) {
				  if(delFile.delete()) {
					  System.out.println("이미지 파일 삭제완료!!");
				  }
			  }
		  }
		
		service.deleteProduct(pnum);  // cnt는 값을가지고 추가로 어떠한 작업을 하고 싶을때 추가(유효성검사 등)
		redirectAttributes.addFlashAttribute("msg", "상품리스트 삭제완료!!");
		return "redirect:/prodList.do";
	}
	
	// 상품 정보 폼으로 가기 (info)
	@RequestMapping("/prodUpdate.do") // Model model은 바인딩
	public String getProduct(int pnum, Model model) {
		
		// 카테고리 리스트 뷰에 넘겨주기
		ArrayList<CategoryDTO> categoryList = categoryService.categoryList();
		model.addAttribute("categorylist", categoryList);
		
		// 브랜드 리스트 뷰에 넘겨주기
		ArrayList<BrandDTO> brandList = brandService.brandList();
		model.addAttribute("brandlist", brandList);
		
		// 상품스펙(열거형 상수 - enum) 가져오기
		ProdSpec[] pdSpecs = ProdSpec.values();
		model.addAttribute("pdSpecs", pdSpecs); // 바인딩
		
		ProductDTO dto = service.getProduct(pnum);
		model.addAttribute("pDto", dto);
		
		return "product/prod_update";
	}
	
	// 상품 정보(인포)폼에서 수정OK 이동
	@RequestMapping("/prodUpdateOk.do")
	public String updateProduct(MultipartHttpServletRequest mhr,
			HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {

		// 파일 저장할 경로
		String repository = "resources/fileRepository";
		
		// 파일을 저장할 실제 서버의 물리경로 얻어오기
		// OS에 따라 \\(윈도우), /(리눅스)
		String savePath = request.getServletContext().getRealPath("")+File.separator+repository;
		System.out.println("savePath : "+ savePath);
		
		////// 불러온 파일들을 모델로 바인딩해서 맵에 저장 이후 fileUploadResult에 출력
		Map map = new HashMap();
		
		// 1. ***************** 넘길 정보들을 얻어오기 
		// MultipartHttpServletRequest mhr은 일반 텍스트, 바이너리 파일 정보를 모두 얻어 올 수 있는 객체.
		// 뷰딴 인풋의 name 속성의 값인 id와 name = 파라미터값을 가져옴
		Enumeration<String> enu = mhr.getParameterNames();
		// Enumeration 객체 사용
		
		while(enu.hasMoreElements()) {
			// 뷰딴 인풋의 name 속성의 값인 id와 name을 가져오기
			String paramName = enu.nextElement();
			// 해당 파라미터명의 값, input의 value값 (사용자가 입력한 값)
			String paramValue = mhr.getParameter(paramName);
								// id, name : test, 홍길동
			System.out.println(paramName + " : " + paramValue);
		////// 불러온 파일들을 모델로 바인딩해서 맵에 저장 이후 fileUploadResult에 출력			
		map.put(paramName, paramValue);
		}
		//  *** enu 여기까지가 아래 값들 넘겨줌 ***
		//		pnum : auto_increment
		//		pqty : 2
		//		pcontent : 123
		//		pname : 자바
		//		price : 20000
		//		pcategory_fk : a002
		//		pcompany : 1234
		//		pspec : RECOMMEND
		//		point : 200
		//		pimageOld : dTree.png
		
//		fileParamName : pimage
//		originName : dTree.png
		
		// 2. ***************** 첨부 파일정보 얻어오기 Iterator 객체사용
		Iterator<String> iter = mhr.getFileNames();
////// 불러온 이미지 어레이리스트로 바인딩해서 어레이리스트로 저장 이후 fileUploadResult에 출력
// List<String> fileList = new ArrayList<String>();		// 사진리스트로 안가져오고 pimage만 가져오니까 위 구문 주석

		while(iter.hasNext()) {
			// input의 name값을 가져오기
			String fileParamName = iter.next();
			System.out.println("fileParamName : "+ fileParamName);
			
			// MultipartFile : 파일정보를 갖고 있는 객체
			MultipartFile mFile = mhr.getFile(fileParamName);
			// 첨부된 파일명
			String originName = mFile.getOriginalFilename();
			System.out.println("originName : "+ originName);
			
			// 기존 pimage에 파일을 추가하지 않고 기존 그림을 그대로 쓴다면, (originName == null 값이면)
			// 즉, 기존 pimageOld 파일이 있다면, 그 파일을 originName 에 넣어준다.
			if(originName == null || originName.trim().equals("")) {
				originName = mhr.getParameter("pimageOld");
				System.out.println("originName2 : "+ originName);
			}
			
			// 기존에 이미지가 있다면 기존이미지는 삭제
//			File delFile = null;
//			 if(mhr.getParameter("pimageOld") != null) {
//				  delFile = new File(savePath + "\\" + mhr.getParameter("pimageOld")); //(savePath + "/" + pimage); 경로를 "/" 써줘도 됌
//				  System.out.println("delFile : "+ delFile);
//				  if(delFile.exists()) {
//					  if(delFile.delete()) {
//						  System.out.println("이미지 파일 삭제완료!!");
//					  }
//				  }
//			  }
			
			// ~ \day05_fileUpDown\\resources/fileRepository/file1
			// 물리경로\\해당파일이름 을 file 주소로 넣어놓기
			File file = new File(savePath+"\\"+fileParamName);
			
			if(mFile.getSize() != 0) { // 사이즈가 0이 아니면 업로드 된경우 (폴더가 존재한 경우)
				if(!file.exists()) { // 폴더가 존재하지만 파일이 존재하지 않으면
					//file.createNewFile();
					if(file.getParentFile().mkdir()) {	// file1의 부모경로로 가서 폴더를 만들고
						file.createNewFile(); // throws 예외처리 진행, 임시파일 생성 // 파일을 만든다
					} // if3
				}	// if2
				
				// 파일이 존재하면
				File uploadFile = new File(savePath+"\\"+originName);
				
				// 중복시 파일명 대체
				if(uploadFile.exists()) {	// 시간을 기준으로 다시 재저장
					originName = System.currentTimeMillis()+"_"+originName;
					uploadFile = new File(savePath+"\\"+originName);
				}
				// 실제 파일 업로드
				mFile.transferTo(uploadFile);
				
				// 파일명을 list에 추가
				//////불러온 이미지 어레이리스트로 바인딩해서 어레이리스트로 저장 이후 fileUploadResult에 출력
				// fileList.add(originName);
			}	// if1
			
			// 파일명을 저장한 리스트를 map에 추가
			////// 불러온 파일들을 모델로 바인딩해서 맵에 저장 이후 fileUploadResult에 출력	
			// map.put("fileList", fileList);
			System.out.println("originName3 : "+originName);
			map.put("pimage", originName);
		} // while
		
		// 서비스로 보냄
		service.updateProduct(map);
		redirectAttributes.addFlashAttribute("msg", "상품 수정완료!!");
		return "redirect:/prodList.do";
	}
	
////////User Product ////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// uleft 카테고리 리스트 띄우기
	@RequestMapping("/UcatList.do")
	public String categoryList(String cat_name, String code, Model model) {
		
		// 카테고리 리스트 뷰에 넘겨주기
		ArrayList<CategoryDTO> categoryList = categoryService.categoryList();			
		model.addAttribute("categorylist", categoryList);
		// 브랜드 리스트 뷰에 넘겨주기
		ArrayList<BrandDTO> brandList = brandService.brandList();
		model.addAttribute("brandlist", brandList);
		// 상품스펙(열거형 상수 - enum) 가져오기
		ProdSpec[] pdSpecs = ProdSpec.values();
		model.addAttribute("pdSpecs", pdSpecs); // 바인딩
		
		// 카테고리 클릭시 넘어감
		ArrayList<ProductDTO> cList = service.getProdByCategory(code);
		model.addAttribute("cList", cList);
		model.addAttribute("cat_name", cat_name);
		
		return "/product/UcategoryList";
	}
	
	// uleft 브랜드 리스트 띄우기
	@RequestMapping("/UbrandList.do")
	public String brandList(String brand_name, String brand_code, Model model) {

		// 카테고리 리스트 뷰에 넘겨주기
		ArrayList<CategoryDTO> categoryList = categoryService.categoryList();			
		model.addAttribute("categorylist", categoryList);
		// 브랜드 리스트 뷰에 넘겨주기
		ArrayList<BrandDTO> brandList = brandService.brandList();
		model.addAttribute("brandlist", brandList);
		// 상품스펙(열거형 상수 - enum) 가져오기
		ProdSpec[] pdSpecs = ProdSpec.values();
		model.addAttribute("pdSpecs", pdSpecs); // 바인딩
		
		// 브랜드 클릭시 넘어감
		ArrayList<ProductDTO> bList = service.getProdByBrand(brand_code);
		model.addAttribute("bList", bList);
		model.addAttribute("brand_name", brand_name);
		
		return "/product/UbrandList";
	}
	
	// uleft 브랜드 리스트 띄우기
	@RequestMapping("/UspecList.do")
	public String UspecList(String name, String pSpec, Model model) {
		
		// 카테고리 리스트 뷰에 넘겨주기
		ArrayList<CategoryDTO> categoryList = categoryService.categoryList();			
		model.addAttribute("categorylist", categoryList);
		// 브랜드 리스트 뷰에 넘겨주기
		ArrayList<BrandDTO> brandList = brandService.brandList();
		model.addAttribute("brandlist", brandList);
		// 상품스펙(열거형 상수 - enum) 가져오기
		ProdSpec[] pdSpecs = ProdSpec.values();
		model.addAttribute("pdSpecs", pdSpecs); // 바인딩
		
		
		// 스펙 클릭시 넘어감
		ArrayList<ProductDTO> sList = service.getProdBySpec(pSpec);
		model.addAttribute("sList", sList); // 스펙별 리스트
		model.addAttribute("pSpec", pSpec); // enum의 키
		// enum의 값 출력
		String pSpecName = ProdSpec.valueOf(pSpec).getValue();
		model.addAttribute("pSpecName", pSpecName);

		return "/product/UspecList";
	}

	// uleft 카테고리 리스트 띄우기
	@RequestMapping("/UprodView.do")
	public String UprodView(int pnum, String pSpec, Model model) {
		
		// 카테고리 리스트 뷰에 넘겨주기
		ArrayList<CategoryDTO> categoryList = categoryService.categoryList();			
		model.addAttribute("categorylist", categoryList);
		// 브랜드 리스트 뷰에 넘겨주기
		ArrayList<BrandDTO> brandList = brandService.brandList();
		model.addAttribute("brandlist", brandList);
		// 상품스펙(열거형 상수 - enum) 가져오기
		ProdSpec[] pdSpecs = ProdSpec.values();
		model.addAttribute("pdSpecs", pdSpecs); // 바인딩
		
		// 카테고리 클릭시 넘어감
		ProductDTO pDto = service.getProduct(pnum);
		model.addAttribute("pDto", pDto);
		// enum의 값 출력
		String pSpecName = ProdSpec.valueOf(pSpec).getValue();
		model.addAttribute("pSpecName", pSpecName);
		
		return "/product/UprodView";
	}
	
//	######### User Product ########
//	/prodView.do = command.ProdViewCommand

	
	
}
