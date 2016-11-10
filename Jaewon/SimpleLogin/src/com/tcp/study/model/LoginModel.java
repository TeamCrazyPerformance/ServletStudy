package com.tcp.study.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import com.tcp.study.util.LogManager;
import com.tcp.study.util.UserData;
import com.tcp.study.util.UserInfo;

public class LoginModel {
	//

	public int login(UserInfo paramInfo) {
		List<UserInfo> list = UserData.getData().getList();
		UserInfo temp;
		int flag;

		paramInfo = checkNull(paramInfo);

		Iterator<UserInfo> i = list.iterator();
		while (i.hasNext()) {
			temp = i.next();
			// 0 = 성공, 1 = 아이디 공백, 2 = 틀린 아이디(아이디 존재안함), 3 = 비밀번호 공백, 4 = 틀린
			// 비밀번호
			if (paramInfo.getId() == null) { // 공백 아이디
				flag = 1;
				LogManager.setLog(flag);
				return flag;
			} else { // 아이디 공백 아님
				if (!paramInfo.getId().equals(temp.getId())) { // 아이디 존재안함
					flag = 2;
					LogManager.setLog(flag);
					return flag;
				} else { // 아이디 존재
					if (paramInfo.getPassword() == null) { // 공백 비밀번호
						flag = 3;
						LogManager.setLog(flag);
						return flag;
					} else { // 비밀번호 공백아님
						if (!paramInfo.getPassword().equals(temp.getPassword())) { // 비밀번호
																					// 틀림
							flag = 4;
							LogManager.setLog(flag);
							return flag;
						}
					}
				}
			}
		}
		flag = 0;
		return flag;
	}

	private UserInfo checkNull(UserInfo info) {
		if (info.getId().trim().length() == 0)
			info.setId(null);
		if (info.getPassword().trim().length() == 0)
			info.setPassword(null);
		return info;
	}
}
