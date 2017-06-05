package test.lx.mybatis.po;

import java.util.List;

/**
 * 包装类型，将来在使用时从页面传递到controller、service、mapper
 * @author liuxun
 *
 */
public class UserQueryVo {
	//用户信息
	private User user;
	
	//自定义User的扩展对象
	private UserCustom userCustom;
	
	//用户id集合
	private List<Integer> ids;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
	
}
