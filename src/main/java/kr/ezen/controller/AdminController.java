package kr.ezen.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.ezen.service.AdminService;
import kr.ezen.winia.domain.AdminDTO;
import kr.ezen.winia.domain.MemberDTO;


@Controller
public class AdminController {
	
	// 자동연결
	@Autowired // DI(Dependency Injection : 의존성 주입)
	// private MemberDAO dao;
	// DAO 대신 Mapper대신 Service 사용
	private AdminService service;
	
	@Autowired // servlet-context에서 생성된 Bean class 를 주입받기
	JavaMailSender mailSender;
	
	@Autowired	// 암호화를위해서 root 추가한 pwEncoder에서 주입받음
	private BCryptPasswordEncoder pwEncoder;
	
	// 어드민 메인 폼으로 이동 - Get방식
	@GetMapping("/adminMain.do")
	// 인터셉터 발생시, 무브url의 마지막 주소를 넘겨줌 ==> 로그인폼, 로그인폼OK로 넘겨줌
	public String adminMainForm() {
		return "/admin/ad_main";
	}
	
	// 어드민 로그인 폼으로 이동 - Get방식
	@GetMapping("/adminLogin.do")
	// 인터셉터 발생시, 무브url의 마지막 주소를 넘겨줌 ==> 로그인폼, 로그인폼OK로 넘겨줌
	public String adminLoginForm() {
		return "/admin/ad_login";
	}
	
	// 어드민 로그인 OK - Post방식
	@PostMapping("/adminLoginOk.do")
	// 인터셉터 발생시, 무브url의 마지막 주소를 넘겨줌 ==> 로그인폼, 로그인폼OK로 넘겨줌
	public String adminLogin(AdminDTO dto, HttpServletRequest request) {	// moveURL 넘길때 코드
		boolean result = service.adminLogin(dto, request);
		
		if(!result) { 		// result 값이 false이면, (로그인이 실패하면)
			return "admin/ad_login";
		}
		return "/admin/ad_main";
	}
	
	// 어드민 로그아웃 - Get방식
	@GetMapping("/adminLogout.do")
	public String adminLogout(HttpSession session) {
		session.invalidate();	// 세션초기화
		return "/admin/ad_main";
	}
	
/////////////////////////////////////////////////////////////////////////	
	// 어드민-멤버리스트 이동 및 띄우기
	@RequestMapping("/adminMemberList.do")
	public String adMemberList(Model model) {
//		List<MemberDTO> memberList = dao.memberList();
		ArrayList<MemberDTO> adMemberList = service.adMemberList();
		model.addAttribute("list", adMemberList);
		return "/admin/admember_list";
	}

	// 어드민-멤버 리스트에서 삭제OK
	@RequestMapping("/adminMemberDelete.do")
	public String adMemberDelete(int no, RedirectAttributes redirectAttributes) {
//		int cnt = dao.memberDelete(no);  // cnt는 값을가지고 추가로 어떠한 작업을 하고 싶을때 추가(유효성검사 등)
		service.adMemberDelete(no);  // cnt는 값을가지고 추가로 어떠한 작업을 하고 싶을때 추가(유효성검사 등)
		redirectAttributes.addFlashAttribute("msg", "멤버 삭제완료!!");
		return "redirect:adminMemberList.do";
	}
	
	// 어드민-멤버 정보 폼으로 가기 (info)
	@RequestMapping("/adminMemberUpdate.do") // Model model은 바인딩
	public String adminMemberInfo(int no, String find, Model model) {		
		MemberDTO dto = service.adminMemberInfo(no);
		//System.out.println("멤버넘버 : "+no);
		model.addAttribute("dto", dto);
		model.addAttribute("find", find); // find는 회원정보 수정인지 비밀번호 변경인지 구분해주는 것을 바인딩해서 뷰에서 뷰로 넘겨줌
		return "/admin/admember_update";
	}
	
	// 어드민-멤버 정보(인포)폼에서 수정OK 이동
	@RequestMapping("/adminMemberUpdateOk.do")
	public String adMemberUpdate(MemberDTO dto, RedirectAttributes redirectAttributes) {
		if(dto.getId() == null || dto.getPw() == null || 
	    		  dto.getId().trim().equals("") || dto.getPw().trim().equals("")) {
			redirectAttributes.addFlashAttribute("msg", "멤버정보 수정실패!!");
	        return "redirect:adminMemberList.do";
	    }

		service.adMemberUpdate(dto);
		redirectAttributes.addFlashAttribute("msg", "멤버정보 수정완료!!");
		return "redirect:adminMemberList.do";
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// 마이프로필폼 -> 현재 비밀번호 옵션들 확인
	@PostMapping("adpwCheck.do")
	@ResponseBody			// 서비스 단에서 session.setAttribute("loginDTO", loginDTO); 에 DTO가 저장되어 있음 getAttribute로 가져옴
	//public String pwCheck(String pw, HttpSession session) {
	public String adpwCheck(String dbPw, String pw, HttpSession session) {
		// DB에서 확인할 필요없이 세션에 바인딩된 값을 가져와서 확인함 --> DB까지 갈필요가 없어 시간 절약
		// 세션에서 바인딩 된 값을 가져올때는 (Object)타입이라 (MemberDTO)로 형변환을 해준다.
		//MemberDTO dto = (MemberDTO)session.getAttribute("loginDTO");
		//String dbPw = dto.getPw();	// dbPw : DB에서 가져온 비번, pw : 사용자가 직접 입력한 비번(ajax)
		String chkResult = "";
		//System.out.println(pw);
		//System.out.println(dbPw);
	
		if(pwEncoder.matches(pw, dbPw) || pw.equals(dbPw)) {	// 비번일치 // 기존비번 로그인 || pw.equals(dbPw)
			chkResult = "ok";
		} else {
			chkResult = "no";
		}
		return chkResult;
	}
	
	// 마이프로필 폼 -> 비밀번호 변경 OK
	@PostMapping("adpwChange.do")
	@ResponseBody // 자바객체를 클라이언트에 전송할 데이터를 http 메세지 바디에 사용하여 보냄 // 네트워크 전송을 할 수 있도록 Json으로 변환(jackson API)
	// @RequestBody는 Http 메세지의 바디에 있는 Json 데이터를 읽어오는데 자바객체로 변환까지 함께 함 (jackson lib)
	public int adpwChange(@RequestBody MemberDTO dto) {
		String inputPw = dto.getPw();		// 회원가입시 입력된 비번
		String chiperPw = pwEncoder.encode(inputPw);	// 암호화된 비번으로 다시 dto에 셋팅 후 전달
		dto.setPw(chiperPw);
		int n = service.admodifyPw(dto);
		
		return n;
	}
	
}
