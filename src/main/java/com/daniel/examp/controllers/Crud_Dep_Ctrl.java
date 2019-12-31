package com.daniel.examp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.daniel.examp.entities.Departamento;
import com.daniel.examp.general.processes.interfaces.ICrud_Dep_Pss;

@Controller
public class Crud_Dep_Ctrl {
	
	private ICrud_Dep_Pss read;
	
	private Crud_Dep_Ctrl (ICrud_Dep_Pss read){
		this.read = read;
	}
	
	
	@RequestMapping(value = "/create")
	public ModelAndView create(ModelAndView view){
		return read.create();
	}
	
	@RequestMapping(value = "/read")
	public ModelAndView read(){
		return read.read();
	}
	
	@RequestMapping("/update/{id}")
	public ModelAndView update(@PathVariable(name = "id") Integer id) {
	    return read.update(id);
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable(name = "id") Integer id) {
		return read.delete(id);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("dep") Departamento dep) {
		return read.save(dep);
	}
	
}