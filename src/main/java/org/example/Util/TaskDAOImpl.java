package org.example.Util;

import org.example.dao.TaskDAO;
import org.example.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    private EntityManagerFactory entityManagerFactory;

    public TaskDAOImpl(EntityManagerFactory entityManagerFactory) { //constructeur qui utilise un objet EMF en parametres

        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean addTask(Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // Création d'un entity manager avec entity manager factory
        EntityTransaction transaction = entityManager.getTransaction(); //Type EntityManager Objet transaction
        try {
            transaction.begin(); // Etablit une liaison avec la BDD "création d'un tunnel, d'une liaison qui se ferme au moment du commit"
            entityManager.persist(task); // Tente d'écrire et Rajoute une ligne ds la BDD (via une "image")
            transaction.commit(); // validation et commit
            return true; // fin de la méthode
        }catch (Exception e){ // Les exceptions héritent de Exception
            if(transaction.isActive()){ // Si on rentre dans l'exception c'est que quelque chose de mal s'est passé. If = retour booléen
                transaction.rollback(); // Si problème on roll back et revient via "l'image" au debut de la transaction a persist
            }
            e.printStackTrace(); // TTes les exception affiche l'erreur le "prtinstack"
            return false; // Retour false pour dire qu'il y a un problème
        }finally {
            entityManager.close();// Fermeture de la liaison avec la BDD
        }
    }

    @Override
    public List<Task> getAllTasks() {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // Pas de transaction car pas de modification
        List<Task> tasks = entityManager.createQuery("SELECT t FROM Task t",Task.class).getResultList(); // Query permet de récuperer la lsite des tache par l'entity mnager qui lui donne tout pour travailler en BDD
        entityManager.close();
        return tasks;
    }

    @Override
    public boolean deleteTask(Long taskId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin(); // On passe par transaction car modification
            Task task = entityManager.find(Task.class,taskId); // va me chercher un objet de type Task qui correspond à l'id en paramètres
            if(task != null){
                entityManager.remove(task); // remove de la task recherché précédement avec find
                transaction.commit(); // Validation  changement
                return true;
            } else {
                return false;
            }
        }catch (Exception e){ // Meme explication que ci-dessus
            if(transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public boolean markTaskAsCompleted(Long taskId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Task task = entityManager.find(Task.class,taskId); // si ont trouve la tache en BDD, on set completed à "true"
            if(task != null){
                task.setCompleted(true); // Le booléen est changé ds le prgrm et non la BDD
                transaction.commit(); // Modifié en BDD
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            if(transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            entityManager.close();
        }
    }
    public boolean addDescriptionTask (Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(task);
            transaction.commit();
            return true;
        }catch (Exception e){
            if(transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            entityManager.close();
        }
    }

    public boolean addDate (Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(task);
            transaction.commit();
            return true;
        }catch (Exception e){
            if(transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            entityManager.close();
        }
    }

}