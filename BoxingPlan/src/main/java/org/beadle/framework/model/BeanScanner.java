package org.beadle.framework.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.beadle.framework.annotation.Column;
import org.beadle.framework.annotation.Entity;
import org.beadle.util.ClassPathResolver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class BeanScanner extends ClassLoader implements InitializingBean,ApplicationContextAware {

	private String packagesToScan;
	private ApplicationContext applicationContext;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		List<String> classpaths = ClassPathResolver.getClassName(getPackagesToScan(),true);
		
		for(String classpath : classpaths){
			Class clazz = Class.forName(classpath);
			Annotation a = clazz.getAnnotation(Entity.class);
			if(a != null){
				Method annotationValueMethod = a.annotationType().getMethod("value");
				String tableName = (String)annotationValueMethod.invoke(a);
				System.out.println(tableName);
			}
		}
		//ResourcePatternUtils.getResourcePatternResolver()
	}

	public String getPackagesToScan() {
		return packagesToScan;
	}

	public void setPackagesToScan(String packagesToScan) {
		this.packagesToScan = packagesToScan;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public ApplicationContext getApplicationContext(){
		return this.applicationContext;
	}
	
}
