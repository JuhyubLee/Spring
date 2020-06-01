package spring.jh.myapp;

import java.lang.reflect.Field;

public class MyContext {

	private <T> T runAnnotation(T obj)
			throws IllegalArgumentException, IllegalAccessException,
			InstantiationException {
	Field[] fields = obj.getClass().getDeclaredFields();
	for(Field field : fields) {
		MyAnnotation anno = field.getAnnotation(MyAnnotation.class);
		if(anno != null) {
			field.setAccessible(true);
			field.set(obj, field.getType().newInstance());
		}
	}
	return obj;
	}
	
	public <T> T getInstance(Class c) throws InstantiationException, IllegalAccessException{
		T obj = (T) c.newInstance();
		obj = runAnnotation(obj);
		return obj;
	}
}
