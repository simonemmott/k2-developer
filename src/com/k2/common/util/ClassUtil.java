package com.k2.common.util;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.Entity;

public class ClassUtil {

    public static Class<?>[] getClasses(String packageName) throws ClassNotFoundException, IOException {
    	return getClasses(packageName, null);
    }

	
	/**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Class<?>[] getClasses(String packageName, Class<? extends Annotation> annotationClass)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageNameToPath(packageName);
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {

            URL resource = resources.nextElement();
         	dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName, annotationClass));
        }
        return classes.toArray(new Class[classes.size()]);
    }
    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class<?>> findClasses(File directory, String packageName, Class<? extends Annotation> annotationClass) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName(), annotationClass));
            } else if (file.getName().endsWith(".class")) {
            	Class<?> clazz = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
            	if (annotationClass != null) {
            		if (clazz.isAnnotationPresent(annotationClass)) {
            			classes.add(clazz);
            		}
            	} else {
            		classes.add(clazz);
            	}
            }
        }
        return classes;
    }
    
    public static String packageNameToPath(String packageName) {
        return packageName.replace('.', File.separatorChar);
    }
    
    public static String getEntityName(Class<?> entity) {
		if (entity.isAnnotationPresent(Entity.class)) {
			return entity.getAnnotation(Entity.class).name();
		} else {
			return entity.getSimpleName();
		}

    }
        
}
