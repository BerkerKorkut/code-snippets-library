package codeSnippets;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public enum MainMenu {
    CHOOSE(1, "Choose an Existing Snippet Library"){
        @Override
        public void choose(){
            Scanner scan = new Scanner(System.in);
            File library = new File("data");
            File[] libraries = library.listFiles((dir, name) -> name.endsWith(".txt"));
            System.out.println("Which Snippet Library do you want to reach");
            for(File file : libraries){
                System.out.println(file.getName().replace(".txt", ""));
            }
            String name = scan.nextLine();
            File file = new File("data/"+ name  +".txt");
            if (file.exists()) {
                SnippetMenu.chosenLibrary = new SnippetLibrary(name);
                SnippetManager snippetManager = new SnippetManager(SnippetMenu.chosenLibrary);
                Snippet snippet = new Snippet();
                String title = "";
                String description = "";
                String code = "";
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    while(true){
                        String currentLine = bufferedReader.readLine();
                        if(currentLine != null){
                            if(currentLine.contains("TITLE:")){
                                title = currentLine.replace("TITLE: ", "");
                            }
                            else if(currentLine.contains("DESCRIPTION:")){
                                description = currentLine.replace("DESCRIPTION: ", "");
                            }
                            else if(currentLine.contains("CODE:")){
                                while(true){
                                    currentLine = bufferedReader.readLine();
                                    if(currentLine.equals("END")){
                                        break;
                                    }
                                    else{
                                        code += currentLine + "\n";
                                    }
                                }
                            }
                            snippet.setTitle(title);
                            snippet.setDescription(description);
                            snippet.setCode(code);
                            snippetManager.addSnippet(snippet);
                        }
                        else{
                            break;
                        }
                    }
                }
                catch (IOException ioe){
                    ioe.printStackTrace();
                }
                System.out.println("Library " + file.getName().replace(".txt", "") + " chosen successfully");
                SnippetMenu.terminalMenu();
            }
            else {
                System.out.println("Snippet Library does not exist");
                terminalMenu();
            }
        }
    },
    CREATE(2, "Create a new Snippet Library"){
        @Override
        public void choose(){
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter name of Snippet Library you are creating");
            String name = scan.nextLine();
            try {
                File file = new File("data/"+ name  +".txt");
                if (!file.exists()) {
                    file.createNewFile();
                    System.out.println("Library " + file.getName().replace(".txt", "") + " created successfully");
                }
                else {
                    System.out.println("Snippet Library already exists");
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            terminalMenu();
        }
    },
    DELETE(3, "Delete an Existing Snippet Library"){
        @Override
        public void choose() {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter name of Snippet Library you are deleting");
            String name = scan.nextLine();
            File file = new File("data/" + name + ".txt");
            if(file.exists()){
                file.delete();
                System.out.println("Library " + file.getName().replace(".txt", "") + " deleted successfully");
            }
            else{
                System.out.println("There are no such Snippet Library");
            }
            terminalMenu();
        }
    },
    EXIT(0, "EXIT"){
        @Override
        public void choose() {
            System.exit(0);
        }
    };

    abstract public void choose();

    static void terminalMenu() {
        Scanner scan = new Scanner(System.in);
        boolean chosen = false;
        System.out.println("Code Snippets Library System Menu");
        for(MainMenu option : MainMenu.values()) {
            System.out.println("(" + option.number + ") " + option.description);
        }
        int choice = scan.nextInt();
        scan.nextLine();
        for(MainMenu option : MainMenu.values()) {
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

    int number;
    String description;
    MainMenu(int number, String description){
        this.number = number;
        this.description = description;
    }
}
