package org.example.dao;


import org.example.entity.Task;

import java.util.List;

public interface TaskDAO {

    public boolean addTask(Task task); //Renvoie si tout est ok

    public List<Task> getAllTasks(); // Renvoie une liste de tache effectuées

    public boolean deleteTask(Long taskId); // Booléen pas obligatoire mais necessaire pour s'avoir si tout est ok mais toujours par rapport à l'ID

    public boolean markTaskAsCompleted(Long taskId); // taskId permet de rechercher par id les taches complétées

    public boolean addDescriptionTask (Task task);

    public boolean addDate (Task task);

    public boolean priority (Task task);
}