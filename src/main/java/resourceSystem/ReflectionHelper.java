package resourceSystem;

import gameMech.Plane;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ReflectionHelper {
    public static Object createInstance(String className) {
        return createInstance(className, null);
    }
	public static Object createInstance(String className, Map<String, String> constructorArgs){
		try {
            if (className.equals(Plane.class.getName()) && constructorArgs != null) {
                Class plane = Plane.class;
                Constructor constructor = plane.getConstructor(new Class[] {Boolean.class});
                return constructor.newInstance(Boolean.parseBoolean(constructorArgs.get("left")));
            }
            Object object = Class.forName(className).newInstance();
            if (!constructorArgs.isEmpty()) {
                for (Map.Entry<String, String> entry : constructorArgs.entrySet())
                {
                    setFieldValue(object, entry.getKey(), entry.getValue());
                }
            }
			return object;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	public static void setFieldValue(Object object, 
			String fieldName, 
			String value){
		
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			
			if(field.getType().equals(String.class)){
				field.set(object, value);
			} else if (field.getType().equals(int.class)){
				field.set(object, Integer.decode(value));
			}			
			
			field.setAccessible(false);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
