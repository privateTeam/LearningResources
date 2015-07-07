package org.beadle.framework.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.orm.ibatis.SqlMapClientOperations;

public class JdbcPostProcessor implements BeanPostProcessor{

	private SqlMapClientOperations sqlMapExecutor;
	
	@Override
	public Object postProcessAfterInitialization(Object obj, String s)
			throws BeansException {
		return obj;
	}

	@Override
	public Object postProcessBeforeInitialization(Object obj, String s)
			throws BeansException {
		if(obj instanceof SqlMapExecutorAware){
			((SqlMapExecutorAware) obj).setSqlMapExecutor(sqlMapExecutor);
		}
		return obj;
	}

	public void setSqlMapExecutor(SqlMapClientOperations sqlMapExecutor){
		this.sqlMapExecutor = sqlMapExecutor;
	}
}
