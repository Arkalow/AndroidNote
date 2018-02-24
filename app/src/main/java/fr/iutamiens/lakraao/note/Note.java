package fr.iutamiens.lakraao.note;

/**
 * Created by omer on 23/02/18.
 */

public class Note {
    private String title;//titre de la note
    private String content;//contenu de la note
    private int id;//id de note

    /***
     * Constructeur d'une note
     * @param title titre de la note
     * @param content contenu de la note
     */
    public Note(String title, String content){
        this.title = title;
        this.content = content;
    }

    /***
     * Constructeur d'une note
     * @param id Identifiant de la note
     * @param title titre de la note
     * @param content contenu de la note
     */
    public Note(int id, String title, String content){
        this.title = title;
        this.content = content;
        this.id = id;
    }

    /***
     * Accessor title
     * @return
     */
    public String getTitle(){
        return title;
    }

    /***
     * Accessor content
     * @return
     */
    public String getContent(){
        return content;
    }

    public int setId(int id){ this.id = id;return id; }
    public int getId(){return id;}
}
