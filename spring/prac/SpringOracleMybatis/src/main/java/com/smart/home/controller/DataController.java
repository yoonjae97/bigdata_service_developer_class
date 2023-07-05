package com.smart.home.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.smart.home.dto.DataDTO;
import com.smart.home.dto.DataFileDTO;
import com.smart.home.service.DataService;

@Controller
@RequestMapping("/data")
public class DataController {
	@Autowired
	DataService service;

	// 글목록
	@GetMapping("/dataList")
	public String dataList(Model model) {
		// db레코드 선택하여 model에 세팅
		model.addAttribute("list", service.getDataList());

		return "data/dataList";
	}

	// 글등록폼
	@GetMapping("/dataWrite")
	public String dataWrite() {
		return "data/dataWrite";
	}

	// 글등록 DB기록
	@PostMapping("/dataWriteOk")
	public ModelAndView dataWriteOk(HttpServletRequest request, DataDTO dto) {
		// 파일을 업로드할 경로 /upload 실제주소
		String path = request.getSession().getServletContext().getRealPath("/upload");
		System.out.println("path->" + path);

		// dto -> 제목, 글 내용, session에서 글쓴이 구해서 dto에 설정
		// no -> 시퀀스, hit, writedate -> default 값
		dto.setUserid((String) request.getSession().getAttribute("logId"));

		// ------- 파일업로드(중요) -----------------------
		// 1. 파일업로드를 위해서 request객체를 multipartRequest객체로 형변환한다.
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;

		// 2. MultipartFile 객체를 얻어오기
		List<MultipartFile> fileList = mr.getFiles("filename");
		System.out.println("fileList.size()- >" + fileList.size());

		// 업로드한 파일들을 보관할 컬렉션
		List<DataFileDTO> upFileList = new ArrayList<DataFileDTO>();

		if (fileList != null) {// 첨부파일이 있을때 if 1

			for (int i = 0; i < fileList.size(); i++) {// 첨부파일 수 만큼 반복 수행 for 1
				MultipartFile mf = fileList.get(i);// 첨부된 MultipartFile 객체 얻어오기
				String orgFileName = mf.getOriginalFilename(); // 파일명 얻어오기
				System.out.println("orgFileName ->" + orgFileName);

				if (orgFileName != null && !orgFileName.equals("")) { // 업로드한 파일이 있으면 if 2

					File f = new File(path, orgFileName);
					if (f.exists()) { // true : 파일이 있다, false : 파일이 없다
						// 파일명 변경
						// 파일명과 학장자 분리 a.b.c.gif
						int point = orgFileName.lastIndexOf("."); // 5
						String orgFile = orgFileName.substring(0, point);
						String orgExt = orgFileName.substring(point + 1);

						for (int renameNum = 1;; renameNum++) { // 1,2,3,...
							String newFileName = orgFile + " (" + renameNum + ")." + orgExt;
							f = new File(path, newFileName);
							if (!f.exists()) {// 파일이 없을때까지 생성
								// 새로 만들어진 파일명을 업로드할때 사용하여야 하므로
								orgFileName = newFileName;
								break;
							}
						} // for
							// 업로드 수행
					}
					try {
						mf.transferTo(new File(path, orgFileName));
					} catch (Exception e) {
						e.printStackTrace();
					}

					System.out.println("업로드된 파일명->" + orgFileName);

					DataFileDTO upFileDTO = new DataFileDTO();
					upFileDTO.setFilename(orgFileName);
					upFileList.add(upFileDTO);
				} // if 2
			} // for 1
		} // if 1
		ModelAndView mav = new ModelAndView();
		try {
			// --- DB처리 --------------
			// 원글 insert -> 생성된 시퀀스 번호를 결과로 받아야 파일명을 db에 추가할때 사용해야 한다.
			int result = service.dataInsert(dto);

			// 파일명이 있는 dto에 원글번호를 추가
			for (int i = 0; i < upFileList.size(); i++) {
				upFileList.get(i).setNo(dto.getNo());
			}

			// 파일명DB(첨부파일) insert -
			int resultFile = service.dataFileInsert(upFileList);

			// 정상구현
			mav.setViewName("redirect:dataList");

		} catch (Exception e) {
			e.printStackTrace();
			// 원글지우기(dto.no)
			service.dataDelete(dto.getNo(), dto.getUserid());
			// 파일명(DB)지우기(dto.no)
			service.dataFileDelete(dto.getNo());
			// 파일삭제(upFileList)
			for (int i = 0; i < upFileList.size(); i++) {
				fileDelete(path, upFileList.get(i).getFilename());
			}

			// 실패...
			mav.setViewName("data/dataResult");
		}
		return mav;
	}
	// 자료실 글 수정 폼
	@GetMapping("/dataEdit")
	public ModelAndView dataEdit(int no) {
		ModelAndView mav = new ModelAndView();
		// 현재글
		mav.addObject("dto", service.dataSelect(no));
		// 첨부파일
		mav.addObject("fileList", service.dataFileSelect(no));
		mav.setViewName("data/dataEdit");
		return mav;
	}
	
	// 자료실 글 수정하기
	@PostMapping("dataEditOk") 
	public ModelAndView dataEditOk(DataDTO dto, HttpSession session, HttpServletRequest request ) {
		// dto 안에는 no, subject, content, filename, delFile
		// 1. 기존에 업로드 된 파일 목록을 DB에서 가져오기
		List<DataFileDTO> orgFileList = service.dataFileSelect(dto.getNo());
		// 2. 새로 추가한 파일 업로드 하기
		String path = session.getServletContext().getRealPath("/upload");// 저장위치
		
		// 3. 새로 추가한 파일 업로드하기 -> MultipartServletRequest(request 객체)
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
		
		// 4. 업로드된 파일(MultipartFile)목록
		List<MultipartFile> fileList = mr.getFiles("filename");
		
		// 새로 업로드한 파일명들
		List<DataFileDTO> newFileList = new ArrayList<DataFileDTO>();
		
		// 5. 업로드된 파일이 있으면 업로드(rename)
		if(fileList != null) { // if 1
			for(int i=0; i<fileList.size(); i++) { // for 1
				MultipartFile mf = fileList.get(i);
				// 파일명구하기
				String orgFileName = mf.getOriginalFilename();
				if(orgFileName != null && !orgFileName.equals("")) { // 파일명이 있으면 if 2
					File f = new File(path);
					if(f.exists()) { // 같은 파일명을 가진 파일이 존재하면 if 3
					// 기존 파일명과 중복 검사
						int p = orgFileName.lastIndexOf(".");
						String fileNoExt = orgFileName.substring(0, p);
						String ext = orgFileName.substring(p+1);
						
						for(int fileNum=0;;fileNum++) { // for 2
							String newFile = fileNoExt+" ("+fileNum+")."+ext;
							f = new File(path, newFile);
							if(!f.exists()) { // if 4
								orgFileName = newFile;
								break;
							} // if 4
						} // for2
					} // if 3
					// 업로드
					try {
						mf.transferTo(new File(path, orgFileName));
						DataFileDTO fDTO = new DataFileDTO();
						fDTO.setNo(dto.getNo());
						fDTO.setFilename(orgFileName);
						newFileList.add(fDTO);
					} catch(Exception e) {
						e.printStackTrace();
					}
				} // if 2
			} // for 1
		} // if
		// DB등록
		/*
		 * 원래 DB파일 -> List<DataFileDTO> orgFileList		a, b, c (+d)
		 * 새로 업로드된파일 -> List<DataFileDTO> newFileList	d
		 * 삭제된 파일 -> List<String> delFile					b
		 */
		
		// orgFileList 새로 업로드된 파일목록을 추가하기
		
		orgFileList.addAll(newFileList);
		
		
		// orgFileList에서 삭제될 파일 객체를 지우기
		if(dto.getDelFile() != null) {
			for(int i=0; i<dto.getDelFile().size(); i++) {
				String del = dto.getDelFile().get(i);
				for(int idx=0; idx<orgFileList.size(); idx++) {
					DataFileDTO resetFile = orgFileList.get(idx);
					if(del.equals(resetFile.getFilename())) { // 삭제할 파일명과 orgFileList에 있는 파일명이 같으면
						orgFileList.remove(idx);
					}
				}
			}
		}
		// ----
		ModelAndView mav = new ModelAndView();
		try {
			// 원 레코드 업데이트
			int result = service.dataUpdate(dto);
			// 파일 목록 -> 삭제, 추가
			service.dataFileDelete(dto.getNo());
			
			service.dataFileInsert(orgFileList);
			// 삭제한 파일을 /upload 폴더에서 제거
			if(dto.getDelFile()!=null) {
				for(String delFilename:dto.getDelFile()) {
					fileDelete(path, delFilename);
				}
			}
			// 글내용보기로 이동
			mav.setViewName("redirect:dataView/"+dto.getNo());
		}catch(Exception e) {
			e.printStackTrace();
			
			// 새로 업로드된 파일 삭제
			for(DataFileDTO fDTO: newFileList) {
				fileDelete(path, fDTO.getFilename());
			}
			
			// 글 내용수정
			mav.setViewName("redirecct:dataEdit?no="+dto.getNo());
		}
		return mav;
	}
	
	// 파일 삭제하는 메소드 ------------------------------
	public void fileDelete(String path, String filename) {
		try {
			File f = new File(path, filename);
			f.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 자료실 글 내용 보기
	// no라는 변수에 url에 있는 값을 받음 (@PathVariable("no") int no)
	@GetMapping("/dataView/{no}")
	public ModelAndView dataView(@PathVariable("no") int no) {
		ModelAndView mav = new ModelAndView();
		// 조회수 증가
		service.hitCount(no);
		// 해당 글 선택
		mav.addObject("dto", service.dataSelect(no));
		// 해당 글의 첨부파일 선택
		mav.addObject("fileList", service.dataFileSelect(no));
		mav.setViewName("/data/dataView");
		return mav;
	}
}

