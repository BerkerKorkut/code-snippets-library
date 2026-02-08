package codeSnippets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public enum SnippetMenu {

    ADD(1, "Add New Code Snippet"){
        @Override
        public void choose(){
            SnippetManager snippetManager = new SnippetManager(chosenLibrary);
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter Title of Code Snippet:");
            String title = scan.nextLine();
            System.out.println("Enter Description of Code Snippet:");
            String description = scan.nextLine();
            System.out.println("Enter the Code (Enter END when finished):");
            String code = "";
            while(true){
                String line = scan.nextLine();
                if(line.equals("END")){
                    break;
                }
                code += line + "\n";
            }
            snippetManager.addSnippet(new Snippet(title, description, code));
            System.out.println("Code Snippet added successfully");
            terminalMenu();
        }
    },
    REMOVE(2, "Remove a Code Snippet"){
        @Override
        public void choose() {
            SnippetManager snippetManager = new SnippetManager(chosenLibrary);
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter name of Code Snippet to delete:");
            String name = scan.nextLine();
            boolean removed = snippetManager.removeSnippet(name);
            if(removed){
                System.out.println("Code Snippet removed successfully");
            }
            else{
                System.out.println("No such Code Snippet");
            }
            terminalMenu();
        }
    },
    SEARCH(3 , "Search for a Code Snippet"){
        @Override
        public void choose() {
            SnippetManager snippetManager = new SnippetManager(chosenLibrary);
            Scanner scan = new Scanner(System.in);
            System.out.println("Do you want to search by (n)name or (k)keyword?");
            String answer = scan.nextLine();
            if(answer.equals("n")){
                System.out.println("Enter name of Code Snippet:");
                String name = scan.nextLine();
                for(Snippet snippet : chosenLibrary){
                    if(snippet.getTitle().equals(name)){
                        System.out.println("Title: " + snippet.getTitle());
                        System.out.println("Description: " + snippet.getDescription());
                        System.out.println("Code: \n" + snippet.getCode());
                    }
                }
            }
            else if(answer.equals("k")){
                System.out.println("Enter a keyword to search for:");
                String keyword = scan.nextLine();
                for(Snippet snippet : chosenLibrary){
                    if(snippet.getTitle().contains(keyword)){
                        System.out.println(snippet);
                    }
                    else if(snippet.getDescription().contains(keyword)){
                        System.out.println(snippet);
                    }
                }
            }
            else{
                System.out.println("Invalid answer");
                SEARCH.choose();
            }
            terminalMenu();
        }

    },
    LIST(4, "List All Code Snippets"){
        @Override
        public void choose() {
            SnippetManager snippetManager = new SnippetManager(chosenLibrary);
            snippetManager.listSnippets();
            terminalMenu();
        }

    },
    BACK(0, "Back to Main Menu"){
        @Override
        public void choose() {
            try {
                File file = new File("data/" + chosenLibrary.name + ".txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
                for (Snippet snippet : chosenLibrary) {
                    writer.write("TITLE: " + snippet.getTitle() + "\n");
                    writer.write("DESCRIPTION: " + snippet.getDescription());
                    writer.write("CODE:\n");
                    writer.write(snippet.getCode() + "\n");
                    writer.write("END\n");
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            MainMenu.terminalMenu();
        }
    };


    abstract public void choose();

    static void terminalMenu() {
        Scanner scan = new Scanner(System.in);
        boolean chosen = false;
        System.out.println("Code Snippets Menu");
        for(SnippetMenu option : SnippetMenu.values()) {
            System.out.println("(" + option.number + ") " + option.description);
        }
        int choice = scan.nextInt();
        scan.nextLine();
        for(SnippetMenu option : SnippetMenu.values()) {
            if(choice == option.number) {
                chosen = true;
                option.choose();
            }
        }
        if(!chosen) {
            System.out.println("Select a valid option from menu");
            terminalMenu();
        }
    }

    static SnippetLibrary chosenLibrary;

    int number;
    String description;
    SnippetMenu(int number, String description){
        this.number = number;
        this.description = description;
    }

}
