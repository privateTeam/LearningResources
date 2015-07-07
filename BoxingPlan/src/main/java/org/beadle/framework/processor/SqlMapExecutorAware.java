package org.beadle.framework.processor;

import org.springframework.beans.factory.Aware;
import org.springframework.orm.ibatis.SqlMapClientOperations;

public interface SqlMapExecutorAware extends Aware{
	public abstract void setSqlMapExecutor(SqlMapClientOperations sqlMapExecutor);
}
