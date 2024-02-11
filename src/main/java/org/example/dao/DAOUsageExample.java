package org.example.dao;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class DAOUsageExample {
    public static void main(String[] args) {
        ProjectService projectService = new ProjectService(new ProjectDAODB());

        int randId = new Random().nextInt(99) + 1;
        Project project = new Project("Project #" + randId, new Date());
        projectService.persist(project);

        List<Project> projects = projectService.findAll();

        projects.stream().forEach(
                p -> System.out.println(p)
        );

        Project projectFromDB = projectService.findByID(projects.get(1).getId());
        System.out.println("\nProject form DB: " + projectFromDB);

        /*projectService.delete(projectFromDB);
        */System.out.println("\nProject id: " + projectFromDB.getId() + " deleted");

        projects = projectService.findAll();

        projects.stream().forEach(
                p -> System.out.println(p)
        );

    }
}
