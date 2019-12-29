package com.daniel.examp.general.processes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.daniel.examp.entities.Departamento;
import com.daniel.examp.general.processes.interfaces.IRead_CrudRepository;
import com.daniel.examp.general.processes.interfaces.IRead_Pss;

@Repository
public class Read_Pss implements IRead_Pss{
	
	public Logger log;
	private IRead_CrudRepository repositorySpring;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Read_Pss(IRead_CrudRepository repositorySpring){
		this.log = LoggerFactory.getLogger(this.getClass());
		this.repositorySpring = repositorySpring;
	}
	
	public ModelAndView read() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("example");
		
		List<String> departamentosSql;
		
		try {
			
			String sql = "select nombre from departamentos order by dep_id desc";
			Query qSql = this.entityManager.createNativeQuery(sql);
			departamentosSql = qSql.getResultList();
			log.info("RESULT INDEX 0 FROM NATIVE QUERY IS: {}", departamentosSql.toArray());
			
			String jpql = "SELECT d FROM Departamento d order by depId desc";
			Query qJpql = this.entityManager.createQuery(jpql);
			List<Departamento> departamentosJpql = qJpql.getResultList();
			log.info("RESULT INDEX 0 FROM JPQL QUERY IS: {}", departamentosJpql.get(0).getNombre());
			
			Query qJpqlNamed = this.entityManager.createNamedQuery("Departamento.findAll");
			departamentosJpql = qJpqlNamed.getResultList();
			log.info("RESULT INDEX 0 FROM JPQL NAMED QUERY IS: {}", departamentosJpql.get(0).getNombre());
			
			Departamento searchDepartamentp = repositorySpring.findByNombre("CUSCATLAN");
			log.info("RESULT INDEX 0 FROM CRUDREPOSITORY OF SPRING IS: {}", searchDepartamentp.getNombre());
			
			model.addObject("listaDepartamentosJpql", departamentosJpql);
			model.addObject("listaDepartamentosSql", departamentosSql);
		
		} catch (Exception e) {
			log.error("THE ERROR MESSAGE IS {} , THE ERROR LINE IN METHOD IS {}", 
					e.getMessage(), e.getStackTrace().length > 0 ? e.getStackTrace()[0].getLineNumber() : "UNKNOWN");
		}
		
		return model;
	}

}
