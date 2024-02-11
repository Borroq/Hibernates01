package org.example.dao;

import java.util.List;

public class ProjectService {
    private AbstractDAOInterface<Project> projectDAO;

    public ProjectService(AbstractDAOInterface<Project> projectDAO) {
        this.projectDAO = projectDAO;
    }

    public void persist (Project project){
        projectDAO.persist(project);
    }
    public void update (Project project){
        projectDAO.update(project);
    }
    public Project findByID (Integer id){
        return projectDAO.findByID(id);
    }
    public void delete (Project project){
        projectDAO.delete(project);
    }
    public void deleteAll (){
        projectDAO.deleteAll();
    }
    public List<Project> findAll(){
        return projectDAO.findAll();
    }
}
