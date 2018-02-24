package fr.iutamiens.lakraao.note;

/**
 * Created by omer on 23/02/18.
 */

public class Note {
    private String title;//titre de la note
    private String content;//contenu de la note

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
}
