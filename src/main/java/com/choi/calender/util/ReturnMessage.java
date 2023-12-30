package com.choi.calender.util;

import com.choi.calender.domain.value.ReturnStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnMessage {
	
	/**
	 * @title	- API 결과코드
	 * @author	- jinyeon Jo
	 * @date	- 2021.12.30
	 * @param	- 0000	: 성공
	 * @param	- 9xxx	: 실패
	 * @param	- 9001	: 필수 값 유실
	 * @param	- 9001	: 중복 등록
	 * @param	- 9999	: 실패
	 */
	private String result;
	private String message;		// 결과메세지
	private Object value;
	
	
	public ReturnMessage() {
		this.result = ReturnStatus.SUCCESS.getValue();
		this.message = "성공";
		this.value = null;
	}
	
	public ReturnMessage(Object value) {
		this.result = ReturnStatus.SUCCESS.getValue();
		this.message = "성공";
		this.value = value;
	}
	
	public ReturnMessage(ReturnStatus result, String message, Object value) {
		this.result = result.getValue();
		this.message = message;
		this.value = value;
	}
	
	public ReturnMessage(ReturnStatus result, String message, Exception e) {
		e.printStackTrace();
		this.result = result.getValue();
		this.message = message;
		this.value = e.toString();
	}
	
	public ReturnMessage emptySaveData() {
		this.result = ReturnStatus.FAIL.getValue();
		this.message = "저장된 데이터가 없습니다";
		this.value = null;
		return this;
	}
}
