package com.hexaware.hlmbackend.app.serviceimpl;

import org.hibernate.metamodel.model.domain.spi.MapPersistentAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public class HomeLoanMapper {
	
	HomeLoanMapper Instance=Mappers.getMapper(HomeLoanMapper.class);
	 
	

}
