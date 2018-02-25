package fr.iutamiens.lakraao.note;

/***
 * Représente une note
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
     * @return retourne le titre de la note
     */
    public String getTitle(){
        return title;
    }

    /***
     * Accessor content
     * @return retourne le contenu de la note
     */
    public String getContent(){
        return content;
    }

    /***
     * Accessor id
     * @return retourne l'id de la note
     */
    public int getId(){
        return id;
    }

    /***
     * Convertie la note sous forme de chaine de craractère
     * @return retourne la note en string
     */
    @Override
    public String toString(){
        return "Note { id : " + id + ", title : " + title + ", content : " + content + " }";
    }
}
