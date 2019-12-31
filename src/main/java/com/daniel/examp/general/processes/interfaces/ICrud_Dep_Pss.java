package com.daniel.examp.general.processes.interfaces;

import org.springframework.web.servlet.ModelAndView;

import com.daniel.examp.entities.Departamento;

public interface ICrud_Dep_Pss {
	public ModelAndView create();
	public ModelAndView read();
	public ModelAndView update(Integer id);
	public ModelAndView delete(Integer id);
	public ModelAndView save(Departamento dep);
}
