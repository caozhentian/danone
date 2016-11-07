package com.danone.comfit.db.converter;

import de.greenrobot.dao.converter.PropertyConverter;

public class ItenPropertyConverter implements PropertyConverter<String,Long>{

	@Override
	public Long convertToDatabaseValue(String arg0) {
		// TODO Auto-generated method stub
		return Long.valueOf(arg0);
	}

	@Override
	public String convertToEntityProperty(Long arg0) {
		// TODO Auto-generated method stub
		return arg0.toString();
	}

	 
	
}
