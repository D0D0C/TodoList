package org.example.Util;

import java.util.Scanner;
import org.example.entity.TodoList;


public class Ihm {


    public class IHM {

        private Scanner scanner;

        private String choix;

        private Boolean status;

        public IHM(){
            scanner = new Scanner(System.in);
            edittodolist = new EditTodoList();
        }

        public void start(){
            System.out.println("Exercice : todoList");
            do {
                menu();
                choix = scanner.nextLine();
                switch (choix){
                    case "1":
                        AjouterTodo();
                        break;
                    case "2":
                        afficherTodo();
                        break;
                    case "3":
                        statusTodo();
                        break;
                    case "4":
                        deleteTodo();
                        break;
                    case "0":
                        System.out.println("Aurevoir");
                        break;
                    default:
                        System.out.println("choix invalide");

                }
            }while (!choix.equals("0"));

        }

        private void menu(){
            System.out.println("=== Menu Todo ===");
            System.out.println("1 - Ajouter une Todo à la liste");
            System.out.println("2 - Afficher toutes les Todos");
            System.out.println("3 - Marquer Status de la Todo ");
            System.out.println("4 - Supprimer une Todo");
            System.out.println("0 - Quitter");
        }
    }

}
