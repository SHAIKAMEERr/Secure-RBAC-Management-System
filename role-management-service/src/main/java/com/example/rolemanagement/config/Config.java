package com.example.rolemanagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

@Configuration
public class Config {
	
	private ModelMapper modelMapper;
	private Gson gSon;
	
    Config(ModelMapper modelMapper, Gson gSon){
    	this.gSon = gSon;
    	this.modelMapper = modelMapper;
    }
}
