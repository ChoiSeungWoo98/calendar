package com.choi.calender.util;

import com.choi.calender.application.dto.DiaryDto;
import com.choi.calender.domain.api.DiaryBean;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Enumeration;

/**
 * @title	- Session Utils
 * @author	- Seung Woo Choi
 * @date	- 2023.08.08
 */

@Service
public class SessionUtils {

	public void sessionSetting(HttpSession session, DiaryBean diaryBean) {
		session.setMaxInactiveInterval(3 * 60 * 60);

//		session.setAttribute("test1", diaryBean.getTest1());
//		session.setAttribute("test2", diaryBean.getTest2());
//		session.setAttribute("test3", diaryBean.getTest3());
//		session.setAttribute("test4", diaryBean.getTest4());
//		session.setAttribute("test5", diaryBean.getTest5());
//		session.setAttribute("test6", diaryBean.getTest6());

	}

	public void printAllSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			Object attributeValue = session.getAttribute(attributeName);
			System.out.println(attributeName + " : " + attributeValue);
		}
	}

	public DiaryDto getUserDataFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<String> attributeNames = session.getAttributeNames();
		DiaryDto diaryDto = new DiaryDto();
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			Object attributeValue = session.getAttribute(attributeName);
			setCalenderDto(diaryDto, attributeName, attributeValue);
		}

		return diaryDto;
	}

	public boolean isSessionAttributeNotExists(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session.getAttribute("id") == null;
	}

	public void removeAllSessions(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			session.removeAttribute(attributeName);
		}
	}

	public void setCalenderDto(DiaryDto diaryDto, String sessionName, Object sessionData) {
		switch (sessionName) {
//			case "test1" :
//				diaryDto.setTest1(String.valueOf(sessionData));
//				break;
//			case "test2" :
//				diaryDto.setTest2(String.valueOf(sessionData));
//				break;
//			case "test3" :
//				diaryDto.setTest3(String.valueOf(sessionData));
//				break;
//			case "test4" :
//				diaryDto.setTest4(String.valueOf(sessionData));
//				break;
//			case "test5" :
//				diaryDto.setTest5(String.valueOf(sessionData));
//				break;
//			case "test6" :
//				diaryDto.setTest6(String.valueOf(sessionData));
//				break;
		}
	}

}
