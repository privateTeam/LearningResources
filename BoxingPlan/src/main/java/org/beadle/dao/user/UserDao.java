package org.beadle.dao.user;

import org.beadle.dao.BaseDao;
import org.beadle.pojo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientOperations;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;

@Repository
public class UserDao extends BaseDao<User>{
	
	public User validateUser(User user){
		try {
			User u = (User)getSqlMapExecutor().queryForObject("user.validateUser", user);
			if(u != null){
				return u;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean insertUser(final User user){
		try{
			getSqlMapExecutor().insert("user.insertUser", user);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
