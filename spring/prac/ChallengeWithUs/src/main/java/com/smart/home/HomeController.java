package com.smart.home;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Block;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageContext;
import com.google.cloud.vision.v1.Page;
import com.google.cloud.vision.v1.Paragraph;
import com.google.cloud.vision.v1.Symbol;
import com.google.cloud.vision.v1.TextAnnotation;
import com.google.cloud.vision.v1.Word;
import com.google.protobuf.ByteString;
import com.smart.home.dto.ChallengesDTO;
import com.smart.home.dto.ChallengesPagingDTO;
import com.smart.home.service.ChallengesService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	ChallengesService service;

	// 챌린지 리스트 조회
	@GetMapping("")
	public ModelAndView ChallengesList(ChallengesPagingDTO pDTO) {

		pDTO.setTotalRecord(service.ChallengesTotalRecord(pDTO));
		List<ChallengesDTO> list = service.ChallengesList(pDTO);

		ModelAndView mav = new ModelAndView();
		System.out.println(list);
		mav.addObject("list", list);
		mav.addObject("pDTO", pDTO);
		mav.setViewName("home");
		return mav;
	}

	// 챌린지 더보기
	@GetMapping("/ChallengesListMore")
	@ResponseBody
	public List<ChallengesDTO> ChallengesListMore(ChallengesPagingDTO pDTO) {
		// pDTO에 nowPage 설정
		/*
		 * ChallengesPagingDTO pDTO = new ChallengesPagingDTO();
		 * pDTO.setNowPage(nowPage); pDTO.setLastNo(lastNo);
		 */
		// 다음 페이지 데이터를 가져와서 리스트로 반환
		if (pDTO.getSearchKey() == "") {
			pDTO.setSearchKey(null);
		}
		if (pDTO.getSearchWord() == "") {
			pDTO.setSearchWord(null);
		}

		List<ChallengesDTO> list = service.ChallengesListMore(pDTO);

		return list;
	}

	// 챌린지 글쓰기 작성
	@GetMapping("/ChallengeWrite")
	public String ChallengeWrite() {
		return "challenge/ChallengeWriteForm";
	}

	@PostMapping("/ChallengeWriteOk")
	@ResponseBody
	public String ChallengeWriteOk(HttpServletRequest request,
	        @RequestParam(value = "challengeFilename", required = false) MultipartFile file, HttpSession session,
	        ChallengesDTO dto) {
	    dto.setMemberId((String) session.getAttribute("logId"));
	    String path = request.getSession().getServletContext().getRealPath("/upload");
	    String orgFileName = file.getOriginalFilename();

	    // 파일이 업로드되지 않았을 경우
	    if (file == null || file.isEmpty()) {
	        dto.setChalFilename(""); // 파일명을 null로 설정하거나 기본값으로 설정 (DB에 null 허용 시)
	    } else {
	        File f = new File(path, orgFileName);
	        if (f.exists()) {
	            int point = orgFileName.lastIndexOf(".");
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
		mav.setViewName("/challenge/challengeViewTemp");
		return mav;

	}

	@PostMapping("/challengePart")
	@ResponseBody
	public Map<String, Integer> challengePart(HttpSession session, int chalNo, int chalFee) {
		Map<String, Integer> response = new HashMap<>();
		String logId = (String) session.getAttribute("logId");
		Integer participantsCnt = null;
		// 1차 로그인 확인
		if (logId == null | logId == "") {
			response.put("result", 2);
			return response;
		}
		
		 //2차 참가여부 확인
		try {
			int checkPart = service.ChallengePartCheck(chalNo, logId);
			if (checkPart > 0) {
				response.put("result", 0);
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		 //3차 예치금 확인 및 차감
		try {
			int memDeposit = service.GetDeposit(logId);
			if (memDeposit < chalFee) {
				response.put("result", 3);
				return response;
			} else {
				memDeposit -= chalFee;
				service.UpdateDeposit(logId, memDeposit);
				service.DepositTransactions(logId, -chalFee, memDeposit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 4차 참가 신청 완료
		try {
			int result = service.ChallengePart(chalNo, logId);
			response.put("result", result);
			service.UpdateFeePartCnt(chalNo);
			participantsCnt = service.GetParticipantsCnt(chalNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.put("participantsCnt", participantsCnt);
		return response;
	}

	@PostMapping("/imgCertify")
	@ResponseBody
	public String imgCertify(HttpSession session, @RequestParam("image") MultipartFile image, int chalNo, int randomCode) {
		String result = null;
		int cnt = 0;
		System.out.println(randomCode);
		String logId = (String)session.getAttribute("logId");
		// 1차 로그인 여부 확인
		if (logId==null || logId=="") {
			return "noId";
		}
		if (image == null || image.isEmpty()) {
		    return "noImage";
		}
		
		// 챌린지 참가여부 확인
		cnt = service.ChallengePartCheck(chalNo, logId);
		if (cnt == 0) {
			return "needPart";
		}
		// 오늘 인증 여부 확인
		cnt = service.findLog(logId);
		if (cnt > 0) {
			return "already";
		}
		
		try {
			String path = session.getServletContext().getRealPath("/photo");

			// MultipartFile을 파일로 저장
			String filename = image.getOriginalFilename();
			String imagePath = path + "/" + filename;
			image.transferTo(new File(imagePath));

			// detectHandwrittenOcr 메서드 호출하여 결과 반환
			result = detectHandwrittenOcr(imagePath, System.out);
			result = result.replaceAll(" ", "");

		} catch (Exception e) {
			System.out.println("fail");
			e.printStackTrace();
		}
		
		try {
			if (Integer.parseInt(result) == randomCode) {
				service.addLog(logId, chalNo);
				return "success";
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
		
	}

	public static String detectHandwrittenOcr(String filePath, PrintStream out) throws Exception {
		List<AnnotateImageRequest> requests = new ArrayList<>();

		byte[] imgBytes = Files.readAllBytes(Paths.get(filePath));

		ByteString imgData = ByteString.copyFrom(imgBytes);

		Image img = Image.newBuilder().setContent(imgData).build();
		Feature feat = Feature.newBuilder().setType(Type.DOCUMENT_TEXT_DETECTION).build();
		// Set the Language Hint codes for handwritten OCR
		ImageContext imageContext = ImageContext.newBuilder().addLanguageHints("en-t-i0-handwrit").build();

		AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img)
				.setImageContext(imageContext).build();
		requests.add(request);

		try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
			BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();

			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					out.printf("Error: %s\n", res.getError().getMessage());
					return "none1";
				}

				// For full list of available annotations, see http://g.co/cloud/vision/docs
				TextAnnotation annotation = res.getFullTextAnnotation();
				for (Page page : annotation.getPagesList()) {
					String pageText = "";
					for (Block block : page.getBlocksList()) {
						String blockText = "";
						for (Paragraph para : block.getParagraphsList()) {
							String paraText = "";
							for (Word word : para.getWordsList()) {
								String wordText = "";
								for (Symbol symbol : word.getSymbolsList()) {
									wordText = wordText + symbol.getText();
									out.format("Symbol text: %s (confidence: %f)\n", symbol.getText(),
											symbol.getConfidence());
								}
								out.format("Word text: %s (confidence: %f)\n\n", wordText, word.getConfidence());
								paraText = String.format("%s %s", paraText, wordText);
							}
							// Output Example using Paragraph:
							out.println("\nParagraph: \n" + paraText);
							out.format("Paragraph Confidence: %f\n", para.getConfidence());
							blockText = blockText + paraText;
						}
						pageText = pageText + blockText;
					}
				}
				out.println("\nComplete annotation:");
				out.println(annotation.getText());
				String result = annotation.getText();
				return result;
			}
		}
		return "none2";

	}

	@GetMapping("/ChallengeEdit")
	public ModelAndView boardEdit(int chalNo, ChallengesPagingDTO pDTO) { 
		// 수정페이지에서 수정하지 않고 이전 페이지로 돌아가고
		// 이전페이지에서 목록돌아가기 눌렀을때 검색어 유지 기능 시도
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pDTO", pDTO);
		mav.addObject("dto", service.ChallengeSelect(chalNo));
		mav.setViewName("/challenge/challengeEdit");
		return mav;
	}
	
	@PostMapping("/ChallengeEditOk")
	@ResponseBody
	public String challengeEditOk(HttpServletRequest request, @RequestParam(value="challengeFilename", required=false) MultipartFile file, HttpSession session, ChallengesDTO dto) {
	    int result = 0;
	    dto.setMemberId((String) session.getAttribute("logId"));
	    String path = request.getSession().getServletContext().getRealPath("/upload");
	    String orgFileName = (file != null) ? file.getOriginalFilename() : null;
	    String delFilename = service.ChallengeFileSelect(dto.getChalNo());
	    System.out.println(orgFileName+"hi");
	    // 기존 파일이 있는 경우
	    if (delFilename != null && !delFilename.isEmpty()) {
	        // 새 파일이 있는 경우, 기존 파일 삭제 후 새 파일 저장
	        if (file != null && !file.isEmpty() && !delFilename.equals(orgFileName)) {
	            fileDelete(path, delFilename);

	            File f = new File(path, orgFileName);
	            if (f.exists()) {
	                int point = orgFileName.lastIndexOf(".");
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
	        }
	        // 새 파일이 없는 경우, 기존 파일 유지
	        else {
	        	fileDelete(path, delFilename);
	            dto.setChalFilename("");
	        }
	    }
	 // 기존 파일이 없는 경우
	    else {
	        // 새 파일이 있는 경우, 새 파일 저장
	        if (file != null && !file.isEmpty()) {
	            File f = new File(path, orgFileName);
	            if (f.exists()) {
	                int point = orgFileName.lastIndexOf(".");
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
	        }
	        // 새 파일이 없는 경우, 아무 동작 없음
	        else {
	            dto.setChalFilename(""); // 또는 dto.setChalFilename("");
	        }
	    }
	    System.out.println(dto.toString());
	    try {
	        result = service.ChallengeUpdate(dto);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result > 0 ? "success" : "failure";
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
	
	
	  @GetMapping("/mypage") 
	  public ModelAndView mypage() {
	  
	  ModelAndView mav = new ModelAndView();
	  mav.setViewName("mypage/mypage");
	  
	  return mav; 
	  }
	 
	@GetMapping("/challengeWrite")
	public ModelAndView challengeWrite() {

		// ModelAndView
		ModelAndView mav = new ModelAndView();
		mav.setViewName("challenge/challengeWrite");

		return mav;
	}

}
