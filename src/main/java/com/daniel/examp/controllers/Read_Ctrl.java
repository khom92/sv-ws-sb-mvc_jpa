package com.daniel.examp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.daniel.examp.general.processes.interfaces.IRead_Pss;

@Controller
public class Read_Ctrl {
	
	private IRead_Pss read;
	
	private Read_Ctrl (IRead_Pss read){
		this.read = read;
	}
	
	/**
	 * To try this endpoint write in your browser the following:
	 * http://localhost:8085/example/read
	 * Don't forget restore backup Departamentos database to postgreSQL, Download from https://github.com/khom92/crud_CI_plPgSql
	 */
	
	@RequestMapping(value = "/read")
	public ModelAndView read(){
		return read.read();
	}

}