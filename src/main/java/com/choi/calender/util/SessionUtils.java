package com.choi.calender.util;

import com.choi.calender.application.dto.calender.CalenderDto;
import com.choi.calender.domain.api.CalenderBean;
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

	public void sessionSetting(HttpSession session, CalenderBean calenderBean) {
		session.setMaxInactiveInterval(3 * 60 * 60);

		session.setAttribute("test1", calenderBean.getTest1());
		session.setAttribute("test2", calenderBean.getTest2());
		session.setAttribute("test3", calenderBean.getTest3());
		session.setAttribute("test4", calenderBean.getTest4());
		session.setAttribute("test5", calenderBean.getTest5());
		session.setAttribute("test6", calenderBean.getTest6());

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

	public CalenderDto getUserDataFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<String> attributeNames = session.getAttributeNames();
		CalenderDto calenderDto = new CalenderDto();
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			Object attributeValue = session.getAttribute(attributeName);
			setCalenderDto(calenderDto, attributeName, attributeValue);
		}

		return calenderDto;
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

	public void setCalenderDto(CalenderDto userDto, String sessionName, Object sessionData) {
		switch (sessionName) {
			case "test1" :
				userDto.setTest1(String.valueOf(sessionData));
				break;
			case "test2" :
				userDto.setTest2(String.valueOf(sessionData));
				break;
			case "test3" :
				userDto.setTest3(String.valueOf(sessionData));
				break;
			case "test4" :
				userDto.setTest4(String.valueOf(sessionData));
				break;
			case "test5" :
				userDto.setTest5(String.valueOf(sessionData));
				break;
			case "test6" :
				userDto.setTest6(String.valueOf(sessionData));
				break;
		}
	}

}
