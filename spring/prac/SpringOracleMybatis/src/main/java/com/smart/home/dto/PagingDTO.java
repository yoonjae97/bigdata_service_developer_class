package com.smart.home.dto;

// 페이지 관련 변수, 검색어 관련 변수
public class PagingDTO {
	private int nowPage = 1; // 현재 페이지 
	private int onePageRecord = 5; // 한 페이지당 레코드 수
	private int totalRecord;// 총 레코드 수
	private int totalPage; // 총 페이지 수
	
	private int onePageNumCount = 5; // 한 페이지에 표시되는 페이지 수
	private int startPageNum = 1;
	
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		
		// 총 페이지 수 계산하기    16->4페이지
		
		// ceil(), round(), floor()
		totalPage = (int)Math.ceil((double)totalRecord/onePageRecord);
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
		
		// 시작번호 = ((현재페이지-1)/한페이지에 표시할 페이지수)*한페이지에 표시할 페이지수 + 1
		startPageNum = ((nowPage-1)/onePageNumCount)*onePageNumCount+1; // 시작페이지 수식
	
	}
	public int getOnePageRecord() {
		return onePageRecord;
	}
	public void setOnePageRecord(int onePageRecord) {
		this.onePageRecord = onePageRecord;
	}
	
	public int getOnePageNumCount() {
		return onePageNumCount;
	}
	public void setOnePageNumCount(int onePageNumCount) {
		this.onePageNumCount = onePageNumCount;
	}
	public int getStartPageNum() {
		return startPageNum;
	}
	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}
	
	
	
	
}
