package com.smart.home.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	// �۸��
	@GetMapping("/dataList")
	public String dataList(Model model) {
		// db���ڵ� �����Ͽ� model�� ����
		model.addAttribute("list", service.getDataList());

		return "data/dataList";
	}

	// �۵����
	@GetMapping("/dataWrite")
	public String dataWrite() {
		return "data/dataWrite";
	}

	// �۵�� DB���
	@PostMapping("/dataWriteOk")
	public ModelAndView dataWriteOk(HttpServletRequest request, DataDTO dto) {
		// ������ ���ε��� ��� /upload �����ּ�
		String path = request.getSession().getServletContext().getRealPath("/upload");
		System.out.println("path->" + path);

		// dto -> ����, �� ����, session���� �۾��� ���ؼ� dto�� ����
		// no -> ������, hit, writedate -> default ��
		dto.setUserid((String) request.getSession().getAttribute("logId"));

		// ------- ���Ͼ��ε�(�߿�) -----------------------
		// 1. ���Ͼ��ε带 ���ؼ� request��ü�� multipartRequest��ü�� ����ȯ�Ѵ�.
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;

		// 2. MultipartFile ��ü�� ������
		List<MultipartFile> fileList = mr.getFiles("filename");
		System.out.println("fileList.size()- >" + fileList.size());

		// ���ε��� ���ϵ��� ������ �÷���
		List<DataFileDTO> upFileList = new ArrayList<DataFileDTO>();

		if (fileList != null) {// ÷�������� ������ if 1

			for (int i = 0; i < fileList.size(); i++) {// ÷������ �� ��ŭ �ݺ� ���� for 1
				MultipartFile mf = fileList.get(i);// ÷�ε� MultipartFile ��ü ������
				String orgFileName = mf.getOriginalFilename(); // ���ϸ� ������
				System.out.println("orgFileName ->" + orgFileName);

				if (orgFileName != null && !orgFileName.equals("")) { // ���ε��� ������ ������ if 2

					File f = new File(path, orgFileName);
					if (f.exists()) { // true : ������ �ִ�, false : ������ ����
						// ���ϸ� ����
						// ���ϸ�� ������ �и� a.b.c.gif
						int point = orgFileName.lastIndexOf("."); // 5
						String orgFile = orgFileName.substring(0, point);
						String orgExt = orgFileName.substring(point + 1);

						for (int renameNum = 1;; renameNum++) { // 1,2,3,...
							String newFileName = orgFile + " (" + renameNum + ")." + orgExt;
							f = new File(path, newFileName);
							if (!f.exists()) {// ������ ���������� ����
								// ���� ������� ���ϸ��� ���ε��Ҷ� ����Ͽ��� �ϹǷ�
								orgFileName = newFileName;
								break;
							}
						} // for
							// ���ε� ����
					}
					try {
						mf.transferTo(new File(path, orgFileName));
					} catch (Exception e) {
						e.printStackTrace();
					}

					System.out.println("���ε�� ���ϸ�->" + orgFileName);

					DataFileDTO upFileDTO = new DataFileDTO();
					upFileDTO.setFilename(orgFileName);
					upFileList.add(upFileDTO);
				} // if 2
			} // for 1
		} // if 1
		ModelAndView mav = new ModelAndView();
		try {
			// --- DBó�� --------------
			// ���� insert -> ������ ������ ��ȣ�� ����� �޾ƾ� ���ϸ��� db�� �߰��Ҷ� ����ؾ� �Ѵ�.
			int result = service.dataInsert(dto);

			// ���ϸ��� �ִ� dto�� ���۹�ȣ�� �߰�
			for (int i = 0; i < upFileList.size(); i++) {
				upFileList.get(i).setNo(dto.getNo());
			}

			// ���ϸ�DB(÷������) insert -
			int resultFile = service.dataFileInsert(upFileList);

			// ������
			mav.setViewName("redirect:dataList");

		} catch (Exception e) {
			e.printStackTrace();
			// ���������(dto.no)
			service.dataDelete(dto.getNo(), dto.getUserid());
			// ���ϸ�(DB)�����(dto.no)
			service.dataFileDelete(dto.getNo());
			// ���ϻ���(upFileList)
			for (int i = 0; i < upFileList.size(); i++) {
				fileDelete(path, upFileList.get(i).getFilename());
			}

			// ����...
			mav.setViewName("data/dataResult");
		}
		return mav;
	}

	// ���� �����ϴ� �޼ҵ� ------------------------------
	public void fileDelete(String path, String filename) {
		try {
			File f = new File(path, filename);
			f.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

