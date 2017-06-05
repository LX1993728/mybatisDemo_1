package test.lx.mybatis.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import test.lx.mybatis.po.User;

public class UserDaoImpl implements UserDao {
   private SqlSessionFactory sqlSessionFactory;
   
   //将SqlSessionFactory注入
   public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
	   this.sqlSessionFactory = sqlSessionFactory;
   }
	
	public User findUserById(int id) throws Exception {
		//创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//根据id查询用户信息
		User user = sqlSession.selectOne("test.findUserById", id);
		sqlSession.close();
		return user;
	}

	
	public List<User> findUserByUsername(String username) throws Exception {
		//创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<User> list = sqlSession.selectList("test.findUserByName", username);
		sqlSession.close();
		return list;
	}

	
	public void insertUser(User user) throws Exception {
		//创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("test.insertUser", user);
		sqlSession.commit();
		sqlSession.close();
	}

}
