package com.coderby.myapp.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MyObjectMapper extends ObjectMapper{

	private static final long serialVersionUID = 1L;
	
	public MyObjectMapper() {
		disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}

}