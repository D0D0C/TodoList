package org.example.service;

import org.example.Util.TaskDAOImpl;
import org.example.entity.Task;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class ToDoListAppConsole {

    private static EntityManagerFactory entityManagerFactory;
    private static TaskDAOImpl taskDAO;

    public static void main() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Tasks"); //Si on instancie ici, il faut le fermer à la fin "quitter" au cas 5
        taskDAO = new TaskDAOImpl(entityManagerFactory);

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("#### To Do List ####");
            System.out.println("1. Ajouter une tâche à la liste");
            System.out.println("2. Afficher toutes les tâches de la liste");
            System.out.println("3. Marquer une tâche comme terminée");
            System.out.println("4. Supprimer une tâche de la liste");
            System.out.println("5. Quitter l'application");
            System.out.println("Choix : ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            switch (choice){
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    displayTasks();
                    break;
                case 3:
                    markTaskAsCompleted(scanner);
                    break;
                case 4:
                    deleteTask(scanner);
                    break;
                case 5:
                    System.out.println("Bye");
                    entityManagerFactory.close(); // Fermeture de l'entity manager
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");

            }

        }while (choice != 5);
    }

    private static void addTask(Scanner scanner){
        System.out.println("Entrer le titre de la tâche : ");
        String title = scanner.nextLine();

        Task task = new Task();
        task.setTitle(title); //utile pour "seter" le titre
        task.setCompleted(false);// True une fois terminée

        if(taskDAO.addTask(task)){ // ça prend la task pas encore géré par l'entity et la BDD et ça va devenir uen entité gérée
            System.out.println("Tâche ajoutée avec succès !"); // Si on renvoie un true c'est ok
        }else {
            System.out.println("Erreur");
        }
    }

    private static void displayTasks() {
        List<Task> tasks = taskDAO.getAllTasks();

        if (tasks.isEmpty()) { // test pour vérifier s'il y a une valeur à l'interieur
            System.out.println("Aucune tâche trouvée.");
        } else {
            System.out.println("=== Liste des tâches ===");
            for (Task task : tasks) { // For each pour parcourir la liste
                System.out.println(task.getId() + ". " + task.getTitle() + " (" + (task.isCompleted() ? "Terminée" : "En cours") + ")"); // Tener sur booleen, si il est true on attérit sur le premier "terminée" si false "en cours"
                // cette solution car pas de ToString dans Task

            }
        }
    }

    private static void deleteTask(Scanner scanner){
        System.out.println("Entrez l'ID de la tâche à supprimer : ");
        Long taskId  = scanner.nextLong();
        scanner.nextLine(); //Vider le cache

        if (taskDAO.deleteTask(taskId)){ // methode deleteTask vas suprrimer taskDAO
            System.out.println("Suppression OK");
        }else {
            System.out.println("Erreur");
        }
    }

    private static void markTaskAsCompleted(Scanner scanner){
        System.out.println("Entrez l'ID de la tâche à supprimer : ");
        Long taskId  = scanner.nextLong();
        scanner.nextLine();

        if (taskDAO.markTaskAsCompleted(taskId)){ // Méthode markTaskCompleted en fonction de l'id
            System.out.println("Modification OK");
        }else {
            System.out.println("Erreur");
        }
    }

    private static void addDescriptionTask (Scanner scanner){
        System.out.println("Entrer la description de la tache: ");
        String description = scanner.nextLine();

        Task task = new Task();
        task.setTitle(description);
        task.setCompleted(false);

        if(taskDAO.addDescriptionTask(task)){ //
            System.out.println("Description ajoutée avec succès !");
        }else {
            System.out.println("Erreur");
        }
    }

    private static void addDate (Scanner scanner){
        System.out.println("Entrer la date de la tache: ");
        String description = scanner.nextLine();

        Task task = new Task();
        task.setTitle(addDate); //utile pour "seter" le titre
        task.setCompleted(false);// True une fois terminée

        if(taskDAO.addDescriptionTask(task)){ //
            System.out.println("Description ajoutée avec succès !");
        }else {
            System.out.println("Erreur");
        }
    }
}