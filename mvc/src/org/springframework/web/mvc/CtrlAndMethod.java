package org.springframework.web.mvc;

import java.lang.reflect.Method;

public class CtrlAndMethod {
	private Object target;
	private Method method;
	
	public CtrlAndMethod( Object target, Method method ) {
		this.target = target;
		this.method = method;
	}

	public Object getTarget() {
		return target;
	}

	public Method getMethod() {
		return method;
	}
	
}
