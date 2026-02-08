package codeSnippets;

import java.util.ArrayList;

public class SnippetLibrary extends ArrayList<Snippet> {
    String name;
    ArrayList<Snippet> library;
    SnippetLibrary(String name){
        super();
        this.name = name;
    }
}
