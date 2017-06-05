package test.lx.mybatis.first;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import test.lx.mybatis.po.User;

/**
 * mybatis入门程序
 * 
 * @author lx
 * 
 */
public class MybatisFirst {
	    // 会话工厂
		private SqlSessionFactory sqlSessionFactory;
		// 创建工厂
		@Before
		public void init() throws IOException {

			// 配置文件（SqlMapConfig.xml）
			String resource = "SqlMapConfig.xml";

			// 加载配置文件到输入 流
			InputStream inputStream = Resources.getResourceAsStream(resource);

			// 创建会话工厂
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		}

		// 测试根据id查询用户(得到单条记录)
		@Test
		public void testFindUserById() {

			// 通过sqlSessionFactory创建sqlSession

			SqlSession sqlSession = sqlSessionFactory.openSession();

			// 通过sqlSession操作数据库
			// 第一个参数：statement的位置，等于namespace+statement的id
			// 第二个参数：传入的参数
			User user = null;
			try {
				user = sqlSession.selectOne("test.findUserById", 1);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 关闭sqlSession
				sqlSession.close();
			}

			System.out.println(user);

		}
		// 测试根据名称模糊查询用户(可能得到多条记录)
		@Test
		public void testFindUserByName() {
			
			// 通过sqlSessionFactory创建sqlSession
			
			SqlSession sqlSession = sqlSessionFactory.openSession();
			
			// 通过sqlSession操作数据库
			// 第一个参数：statement的位置，等于namespace+statement的id
			// 第二个参数：传入的参数
			List<User> list = null;
			try {
				//list = sqlSession.selectList("test.findUserByName", "小明");
				list = sqlSession.selectList("test.findUserByName2", "%小明%");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 关闭sqlSession
				sqlSession.close();
			}
			
			System.out.println(list.size());
		}
		// 测试插入用户
		@Test
		public void testInsertUser() {
			// 通过sqlSessionFactory创建sqlSession
			SqlSession sqlSession = sqlSessionFactory.openSession();
			
			//通过sqlSession操作数据库
			//创建插入数据对象
			User user = new User();
			user.setUsername("一蓑烟雨");
			user.setAddress("河南周口");
			user.setBirthday(new Date());
			user.setSex("1");
			try {
				sqlSession.insert("test.insertUser", user);
				//需要提交事务
				sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 关闭sqlSession
				sqlSession.close();
			}
			System.out.println(user.getId());
		}
		// 测试删除用户
		@Test
		public void testDeleteUser() {
			// 通过sqlSessionFactory创建sqlSession
			SqlSession sqlSession = sqlSessionFactory.openSession();
			
			//通过sqlSession操作数据库
			try {
				sqlSession.delete("test.deleteUser", 28);
				//需要提交事务
				sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 关闭sqlSession
				sqlSession.close();
			}
		}
		// 测试根据id更新用户(得到单条记录)
		@Test
		public void testUpdateUser() {
			// 通过sqlSessionFactory创建sqlSession
			SqlSession sqlSession = sqlSessionFactory.openSession();
			
			//通过sqlSession操作数据库
			//创建更新数据库对象，要求必须包括id
			User user= new User();
			user.setId(28);
			user.setUsername("任平生");
			//凡是没有设置的属性都被当成了NULL进行赋值
			//user.setBirthday(new Date());
			user.setSex("1");
			
			try {
				sqlSession.delete("test.updateUser", user);
				//需要提交事务
				sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 关闭sqlSession
				sqlSession.close();
			}
		}

}
