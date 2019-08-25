package cn.dhbin.aop.aopcontext.service;

/**
 * @author donghaibin
 * @date 2019/8/25
 */
public interface IUserService {

	/**
	 * 模拟登陆
	 */
	void login();

	/**
	 * 模拟获取用户
	 *
	 * @return 获取User
	 */
	String getUser();


	/**
	 * 组合login和getUser
	 */
	void combination();
}
