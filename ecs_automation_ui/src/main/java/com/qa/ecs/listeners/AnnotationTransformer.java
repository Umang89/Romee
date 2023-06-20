package com.qa.ecs.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * This Class is used to modifies the default TestNG tests behavior at run time
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(Retry.class);
    }
}
