package pl.edu.agh.db2.northwind.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Component
public class LoggerPostProcessor implements BeanPostProcessor {
	// do NOT even try injecting logger here ↓↓↓
	private static final org.apache.log4j.Logger logger = Logger.getLogger(LoggerPostProcessor.class);

	public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				if (field.getAnnotation(javax.inject.Inject.class) != null && field.getType().equals(org.apache.log4j.Logger.class)) {
					logger.debug(String.format("Setting log4j logger for class: %s", bean.getClass().getCanonicalName()));
					ReflectionUtils.makeAccessible(field);
					field.set(bean, org.apache.log4j.Logger.getLogger(bean.getClass()));
				}
			}
		});

		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
}