package com.daniel.examp.general.processes.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.daniel.examp.entities.Departamento;

public interface IRead_CrudRepository extends CrudRepository<Departamento, String> {
	public Departamento findByNombre(String nombre);
}
