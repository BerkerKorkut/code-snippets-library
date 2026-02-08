package codeSnippets;

import java.util.ArrayList;

public class SnippetManager {
    private SnippetLibrary snippets;

    public SnippetManager(String name){
        snippets = new SnippetLibrary(name);
    }
    public SnippetManager(SnippetLibrary library){
        snippets = library;
    }

    public void addSnippet(Snippet snippet){
        snippets.add(snippet);
    }
    public void removeSnippet(Snippet snippet){
        snippets.remove(snippet);
    }
    public boolean removeSnippet(String title){
        Snippet toRemove = findByTitle(title);
        if (toRemove != null) {
            snippets.remove(toRemove);
            return true;
        }
        return false;
    }
    public Snippet findByTitle(String title){
        for(Snippet snippet : snippets){
            if(snippet.getTitle().equals(title)){
                return snippet;
            }
        }
        return null;
    }
    public void listSnippets(){
        for(Snippet snippet : snippets){
            System.out.println(snippet);
            System.out.println("");
        }
    }


}
