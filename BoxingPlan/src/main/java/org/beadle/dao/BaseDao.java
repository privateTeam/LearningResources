package org.beadle.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.beadle.framework.annotation.Column;
import org.beadle.framework.annotation.Entity;
import org.beadle.framework.processor.SqlMapExecutorAware;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.ibatis.SqlMapClientOperations;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.event.RowHandler;

@SuppressWarnings("deprecation")
public class BaseDao<T> implements SqlMapExecutorAware{

	private SqlMapClientOperations sqlMapExecutor;
	private Class genericClass;//模板泛型的class
	

	private String tableName;
	{
		ParameterizedType type =  (ParameterizedType)this.getClass().getGenericSuperclass();
		Class genericType = (Class)type.getActualTypeArguments()[0];
		setGenericClass(genericType);
		Annotation annotation = genericType.getAnnotation(Entity.class);
		try {
			Method annotationValueMethod = annotation.annotationType().getMethod("value");
			tableName = (String)annotationValueMethod.invoke(annotation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertOne(T t){
		boolean result = false;
		try{
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("tableName", getTableName());
			Method[] methods = getGenericClass().getDeclaredMethods();
			List<String> columnList = new ArrayList<String>();
			List<Object> valueList = new ArrayList<Object>();
			for(Method method : methods){
				Column c = method.getAnnotation(Column.class);
				//获取所有get的方法
				if(method.getName().startsWith("get") && c != null){
					columnList.add(c.value());
					valueList.add(method.invoke(t));
				}
			}
			paramMap.put("columnList", columnList);
			paramMap.put("valueList", valueList);
			this.sqlMapExecutor.insert("common.insertOne", paramMap);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateOne(T t){
		boolean result = false;
		try{
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("tableName", getTableName());
			Method[] methods = getGenericClass().getDeclaredMethods();
			List<Map<String,Object>> paramList = new ArrayList<Map<String,Object>>();
			for(Method method : methods){
				Column c = method.getAnnotation(Column.class);
				//获取所有get的方法
				if(method.getName().startsWith("get") && c != null){
					Map<String,Object> param = new HashMap<String,Object>();
					param.put("name", c.value());
					param.put("value", method.invoke(t));
					paramList.add(param);
				}else if(method.getName().startsWith("get")){
					paramMap.put("id", method.invoke(t));
				}
			}
			paramMap.put("paramList", paramList);
			this.sqlMapExecutor.insert("common.updateOneById", paramMap);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public T getOneById(int id){
		try{
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("tableName", getTableName());
			paramMap.put("id", id);
			Method[] methods = getGenericClass().getDeclaredMethods();
			List<String> columnList = new ArrayList<String>();
			for(Method method : methods){
				Column c = method.getAnnotation(Column.class);
				//获取所有get的方法
				if(method.getName().startsWith("get") && c != null){
					columnList.add(c.value());
				}
			}
			paramMap.put("columnList", columnList);
			List<Map<String,Object>> list = this.sqlMapExecutor.queryForList("common.getOneById", paramMap);
			if(list.size() != 1){
				return null;
			}else{
				Map<String,Object> map = list.get(0);
				//通过columnList映射到T上
				T t = (T)getGenericClass().newInstance();//无参的构造方法
				for(Method method : methods){
					Column c = method.getAnnotation(Column.class);
					//获取所有get的方法
					if(method.getName().startsWith("get") && c != null){
						String getName = method.getName().substring(3);
						//寻找get方法对应的set方法
						for(Method setMethod : methods){
							if(setMethod.getName().startsWith("set")){
								String setName = setMethod.getName().substring(3);
								if(setName.equals(getName)){
									setMethod.invoke(t,map.get(c.value()));
								}else if(setName.equals("Id")){
									setMethod.invoke(t,id);
								}
							}
						}
					}
				}
				
				return t;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void setSqlMapExecutor(SqlMapClientOperations sqlMapExecutor) {
		this.sqlMapExecutor = sqlMapExecutor;
	}
	
	protected SqlMapClientOperations getSqlMapExecutor(){
		return this.sqlMapExecutor;
	}

	private String getTableName(){
		return this.tableName;
	}
	
	private Class getGenericClass() {
		return genericClass;
	}

	public void setGenericClass(Class genericClass) {
		this.genericClass = genericClass;
	}
}
