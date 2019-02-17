package cn.dhbin.proxy.dao;

/**
 * @author DHB
 *
 * 模拟查询用户Dao
 */
public interface UserDao {

	/**
	 * 获取名字
	 * @return 名字
	 */
	String getName();

	/**
	 * 模拟删除
	 */
	void delete();

	/**
	 * 模拟插入一个用户
	 * @param user user
	 */
	void insert(String user);
}
