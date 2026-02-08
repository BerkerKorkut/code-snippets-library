package codeSnippets;

public class Snippet {
    private String title;
    private String description;
    private String code;

    Snippet(){

    }
    Snippet(String title){
        this.title = title;
    }
    Snippet(String title, String explanation){
        this.title = title;
        this.description = explanation;
    }
    Snippet(String title, String explanation, String code){
        this.title = title;
        this.description = explanation;
        this.code = code;
    }

    public String getTitle(){
        return title;
    }
    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return title + "\n" + description;
    }
}
