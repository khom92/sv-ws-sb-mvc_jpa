package com.daniel.examp.general.processes.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.daniel.examp.entities.Departamento;

public interface ICrud_Dep_CrudRepository extends CrudRepository<Departamento, Integer> {
	public Departamento findByNombre(String nombre);
}
