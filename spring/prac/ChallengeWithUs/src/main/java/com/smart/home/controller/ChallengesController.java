package com.smart.home.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.smart.home.dto.ChallengesDTO;
import com.smart.home.dto.ChallengesPagingDTO;
import com.smart.home.service.ChallengesService;

@Controller
@RequestMapping("/yj")
public class ChallengesController {
	
	@Autowired
	ChallengesService service;
	
	// ç���� ����Ʈ ��ȸ
	@GetMapping("/ChallengesList")
	public ModelAndView ChallengesList(ChallengesPagingDTO pDTO) {

		pDTO.setTotalRecord(service.ChallengesTotalRecord(pDTO));
		List<ChallengesDTO> list = service.ChallengesList(pDTO);
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("list", list);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("yj/ChallengesList");
		return mav;
	}
	
	// ç���� ������
	@GetMapping("/ChallengesListMore")
    @ResponseBody
    public List<ChallengesDTO> ChallengesListMore(ChallengesPagingDTO pDTO) {
        // pDTO�� nowPage ����
		/*
		 * ChallengesPagingDTO pDTO = new ChallengesPagingDTO();
		 * pDTO.setNowPage(nowPage); pDTO.setLastNo(lastNo);
		 */
        // ���� ������ �����͸� �����ͼ� ����Ʈ�� ��ȯ
		if (pDTO.getSearchKey()=="") {
			pDTO.setSearchKey(null);
		}
		if (pDTO.getSearchWord()=="") {
			pDTO.setSearchWord(null);
		}

        List<ChallengesDTO> list = service.ChallengesListMore(pDTO);

        return list;
    }
	
	// ç���� �۾��� �ۼ�
	@GetMapping("/ChallengeWrite")
	public String ChallengeWrite() {
		return "yj/ChallengeWriteForm";
	}
	
	@PostMapping("/ChallengeWriteOk")
	@ResponseBody
	public String ChallengeWriteOk(HttpServletRequest request, @RequestParam(value="challengeFilename", required=false) MultipartFile file, HttpSession session, ChallengesDTO dto) {
		dto.setMemberId((String)session.getAttribute("logId"));
		String path = request.getSession().getServletContext().getRealPath("/upload");

		String orgFileName = file.getOriginalFilename();

		File f = new File(path, orgFileName);
		if (f.exists()) {
			int point = orgFileName.lastIndexOf("."); // 5
			String orgFile = orgFileName.substring(0, point);
			String orgExt = orgFileName.substring(point + 1);
			
			for (int renameNum = 1;; renameNum++) { // 1,2,3,...
				String newFileName = orgFile + " (" + renameNum + ")." + orgExt;
				f = new File(path, newFileName);
				if (!f.exists()) {
					orgFileName = newFileName;
					break;
				}
			} 
		}
		dto.setChalFilename(orgFileName);

		try {
			file.transferTo(new File(path, orgFileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int result = 0;
		try {
			result = service.ChallengeInsert(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
	        return "success";
	    } else {
	        return "failure";
	    }
	}
	
	@GetMapping("/ChallengeView")
	public ModelAndView ChallengeView(int chalNo, ChallengesPagingDTO pDTO) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dto", service.ChallengeSelect(chalNo));
		mav.addObject("pDTO", pDTO);
		mav.setViewName("/yj/ChallengeView");
		return mav;
		
	}
	
	@GetMapping("/ChallengeEdit")
	public ModelAndView boardEdit(int chalNo, ChallengesPagingDTO pDTO) { 
		// �������������� �������� �ʰ� ���� �������� ���ư���
		// �������������� ��ϵ��ư��� �������� �˻��� ���� ��� �õ�
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pDTO", pDTO);
		mav.addObject("dto", service.ChallengeSelect(chalNo));
		mav.setViewName("yj/ChallengeEdit");
		return mav;
	}
	
	@PostMapping("/ChallengeEditOk")
	@ResponseBody
	public String challengeEditOk(HttpServletRequest request, @RequestParam(value="challengeFilename", required=false) MultipartFile file, HttpSession session, ChallengesDTO dto) {
		int result = 0;
		dto.setMemberId((String)session.getAttribute("logId"));
		String path = request.getSession().getServletContext().getRealPath("/upload");

		String delFilename = service.ChallengeFileSelect(dto.getChalNo());
		if (delFilename != null) {
			File delFile = new File(path, delFilename);
			
			if (delFile.exists()) {
				fileDelete(path, delFilename);
			}
		}
		
		String orgFileName = file.getOriginalFilename();

		File f = new File(path, orgFileName);
		if (f.exists()) {
			int point = orgFileName.lastIndexOf("."); // 5
			String orgFile = orgFileName.substring(0, point);
			String orgExt = orgFileName.substring(point + 1);
			
			for (int renameNum = 1;; renameNum++) { // 1,2,3,...
				String newFileName = orgFile + " (" + renameNum + ")." + orgExt;
				f = new File(path, newFileName);
				if (!f.exists()) {
					orgFileName = newFileName;
					break;
				}
			} 
		}
		dto.setChalFilename(orgFileName);

		try {
			file.transferTo(new File(path, orgFileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			result = service.ChallengeUpdate(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
	        return "success";
	    } else {
	        return "failure";
	    }
		
	}
	
	public void fileDelete(String path, String filename) {
		try {
			File f = new File(path, filename);
			f.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/ChallengeDelete")
	@ResponseBody
	public String ChallengeDelete(HttpServletRequest request,  HttpSession session, int chalNo) {
		int result = 0;
		String path = request.getSession().getServletContext().getRealPath("/upload");

		String delFilename = service.ChallengeFileSelect(chalNo);
		if (delFilename != null) {
			File delFile = new File(path, delFilename);
			
			if (delFile.exists()) {
				fileDelete(path, delFilename);
			}
		}
		
		try {
			result = service.ChallengeDelete(chalNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result > 0) {
	        return "success";
	    } else {
	        return "failure";
	    }
	}
}











