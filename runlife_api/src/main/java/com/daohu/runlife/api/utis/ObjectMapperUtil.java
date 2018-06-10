package com.daohu.runlife.api.utis;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapperUtil {
	private static DozerBeanMapper dozer = new DozerBeanMapper();
	
	public static <T,F> T map(F source, Class<T> clazz){
		if(source!=null){
			return dozer.map(source, clazz);
		}
		return null;
	}
	
	public static <T extends Object,F extends Object> T map(F source, T destination){
		dozer.map(source, destination);
		return destination;
	}
	
	public static <T, F> List<T> mapList(final List<F> source, final Class<T> destType) {
		if(source!=null){

			final List<T> dest = new ArrayList<>();

			for (F element : source) {
				dest.add(map(element, destType));
			}

			return dest;
		}
		return null;
	}
}
