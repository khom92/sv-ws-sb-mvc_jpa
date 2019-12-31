package com.daniel.examp.general.processes;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.daniel.examp.entities.Departamento;
import com.daniel.examp.general.processes.interfaces.ICrud_Dep_CrudRepository;
import com.daniel.examp.general.processes.interfaces.ICrud_Dep_Pss;

@Repository
public class Crud_Dep_Pss implements ICrud_Dep_Pss{
	
	public Logger log;
	private ICrud_Dep_CrudRepository repositorySpring;
	private static final String ERR_MESSAGE = "THE ERROR MESSAGE IS {} , THE ERROR LINE IN METHOD IS {}";
	private static final String ERR_UNKNOWN = "UNKNOWN";
	private static final String ERR_PAGE = "errorGeneralPage";

	@PersistenceContext
	private EntityManager entityManager;
	
	public Crud_Dep_Pss(ICrud_Dep_CrudRepository repositorySpring){
		this.log = LoggerFactory.getLogger(this.getClass());
		this.repositorySpring = repositorySpring;
	}
	
	
	public ModelAndView create() 
	{
		try {
			ModelAndView createView = new ModelAndView("create_dep");
		    createView.addObject("dep", new Departamento());
		    return createView;
		} catch (Exception e) {
			log.error(ERR_MESSAGE, e.getMessage(), e.getStackTrace().length > 0 ? e.getStackTrace()[0].getLineNumber() : ERR_UNKNOWN);
			return new ModelAndView(ERR_PAGE);
		}
	}
	
	public ModelAndView update(Integer id) 
	{
	    
	    try {
		    ModelAndView updateView = new ModelAndView("create_dep");
		    Optional<Departamento> dep  = repositorySpring.findById(id);
		    
		    if(dep.isPresent()) {
		    	updateView.addObject("dep", dep.get());
		    } else {
		    	updateView.addObject("dep", new Departamento());
		    }
		    
		    return updateView;
		} catch (Exception e) {
			log.error(ERR_MESSAGE, e.getMessage(), e.getStackTrace().length > 0 ? e.getStackTrace()[0].getLineNumber() : ERR_UNKNOWN);
			return new ModelAndView(ERR_PAGE);
		}
	    
	}
	
	public ModelAndView delete(Integer id) 
	{
		try {
			repositorySpring.deleteById(id);
		    return new ModelAndView("redirect:/read");
		} catch (Exception e) {
			log.error(ERR_MESSAGE, e.getMessage(), e.getStackTrace().length > 0 ? e.getStackTrace()[0].getLineNumber() : ERR_UNKNOWN);
			return new ModelAndView(ERR_PAGE);
		}
	}
	
	public ModelAndView save(Departamento dep) 
	{
		ModelAndView model = null;
		try {
			repositorySpring.save(dep);
			model = new ModelAndView("redirect:/read");
		} catch (Exception e) {
			log.error(ERR_MESSAGE, e.getMessage(), e.getStackTrace().length > 0 ? e.getStackTrace()[0].getLineNumber() : ERR_UNKNOWN);
		}
		return model;
	}
	
	
	/**
	 * In this method I test another jpa custom queries 
	 */
	public ModelAndView read() 
	{
		
		ModelAndView model = new ModelAndView("example");
		List<?> departamentosSql;
		
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
			log.error(ERR_MESSAGE, e.getMessage(), e.getStackTrace().length > 0 ? e.getStackTrace()[0].getLineNumber() : ERR_UNKNOWN);
		}
		
		return model;
	}

}
