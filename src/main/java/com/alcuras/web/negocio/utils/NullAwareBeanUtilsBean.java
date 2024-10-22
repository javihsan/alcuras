package com.alcuras.web.negocio.utils;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtilsBean;

public class NullAwareBeanUtilsBean extends BeanUtilsBean{

    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if(value==null)return;
        try {
			if(super.getProperty(dest, name)!=null) return;
		} catch (NoSuchMethodException e) {
			return;
		}
        super.copyProperty(dest, name, value);
    }
 
}

