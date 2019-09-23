package com.infobae.ibproducto.reports.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.infobae.ibproducto.reports.utils.StreamUtils;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.TypeFactory;

@Component
public class Mapper {

	@Inject
	MapperFactory mapperFactory;
	
	/**
	 * Mapea todos los elementos de una lista y las devuelve mapeadas en otra lista.
	 * 
	 * @param list
	 * @param b
	 * @return
	 */
	public <A,B> List<B> mapList(Iterable<A> iterable, Class<B> b) {
	
		if (iterable == null) {
			return null;
			
		}
			
		return StreamUtils.stream(iterable).map(item -> map(item, b)).collect(Collectors.toList());
	}
	
	
	
	/**
	 * Mapea todos los elementos de una lista y las devuelve mapeadas en otra lista.
	 * 
	 * @param list
	 * @param b
	 * @return
	 */
	public <A,B> List<B> mapList(List<A> list, Class<B> b) {
	
		if (list == null) {
			return null;
		}
			
		return list.stream().map(item -> map(item, b)).collect(Collectors.toList());
	}
	
	@SuppressWarnings("unchecked")
	public <A,B> B map(A a, Class<B> b) {
	
		if (a == null) {
			return null;

		}
			
		return mapperFactory.getMapperFacade(TypeFactory.valueOf((Class<A>) a.getClass()), TypeFactory.valueOf(b)).map(a);
		
	}
	
	@SuppressWarnings("unchecked")
	public <A,B> void map(A a, B b) {
		
		if (b == null) {
			return;
		}
		
		Class<A> classA =  (Class<A>) a.getClass();
		Class<B> classB =  (Class<B>) b.getClass();
		
			
		mapperFactory.getMapperFacade(TypeFactory.valueOf(classA), TypeFactory.valueOf(classB)).map(a, b);
		
	}
	
	public <E,Dto> Dto mapObject(Dto dto, E entity, Function<E, E>  function) {
		
		 map(dto,entity);
		
		 E result = function.apply(entity);
		 
		 return (Dto) map(result, dto.getClass());
	}
	
	public <E,Dto> Dto mapEntity(Dto dto, Class<E> entityClass, Function<E, E>  function) {
		
		 E entity=  map(dto,entityClass);
		
		 E result = function.apply(entity);
		 
		 return (Dto) map(result, dto.getClass());
	}
	
	public <E,Dto> Dto mapEntity(Dto dto, Supplier<E> supplier, Function<E, E>  function) {
		
		 E entity= supplier.get();
		 
		 map(dto, entity);
		
		 E result = function.apply(entity);
		 
		 return (Dto) map(result, dto.getClass());
	}

	public <E,Dto,R> Dto mapEntityWithResult(Dto dto, Class<E> entityClass, Function<E, R>  function) {
		
		 E entity=  map(dto,entityClass);
		
		 R result = function.apply(entity);
		 
		 return (Dto) map(result, dto.getClass());
	}

	
}
