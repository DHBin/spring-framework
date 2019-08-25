package cn.dhbin.aop.aopcontext.service.impl;

import cn.dhbin.aop.aopcontext.service.IUserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * @author donghaibin
 * @date 2019/8/25
 */
@Service
public class UserServiceImpl implements IUserService {

	@Override
	public void login() {
		System.out.println("login");
	}

	@Override
	public String getUser() {
		System.out.println("getUser");
		return "user";
	}

	private void privateMethod() {
		System.out.println("xxxxxx");
	}

	@Override
	public void combination() {
		IUserService currentProxy = (IUserService) AopContext.currentProxy();
		currentProxy.login();
		System.out.println(currentProxy.getUser());
	}
}
