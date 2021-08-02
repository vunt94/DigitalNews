/*
* Copyright(C) 2021,  vunthe141507.
* J3.L.P0004
* Java Web
*
* Record of change:
* DATE                Version           AUTHOR          DESCRIPTION
* 17/05/2021           1.0               VuNT       fix comment, fix bug
* 19/05/2021           2.0               VuNT       fix comment, fix bug
*/
package entity;

import java.util.Date;

/**
* This class contain entities of Digital
* Contain methods which use to get and set all of attributes
* 2 Constructor with parameter and no parameter
* <p>Bugs: None
* 
* @author Nguyen Tuan Vu
*/

public class Digital {

    private int id;
    private String title;
    private String description;
    private String image;
    private String author;
    private Date timePost;
    private String shortDes;

    /**
     * Used to initialize Digital with no parameter
     */
    public Digital() {
    }
    
    /**
     * Used to initialize Digital with its properties as parameters
     * 
     * @param id the id of Digital. It is an int number
     * @param title the title of Digital. 
     * It is a <code>java.lang.String</code> object
     * @param description the description of Digital. 
     * It is a <code>java.lang.String</code> object
     * @param image the image of Digital. 
     * It is a <code>java.lang.String</code> object
     * @param author the author of Digital. 
     * It is a <code>java.lang.String</code> object
     * @param timePost the time post of Digital. 
     * It is a <code>java.util.Date</code> object
     * @param shortDes the short description of Digital. 
     * It is a <code>java.lang.String</code> object
     */
    public Digital(int id, String title, String description, String image, 
            String author, Date timePost, String shortDes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.author = author;
        this.timePost = timePost;
        this.shortDes = shortDes;
    }   
    /**
     * Get value of attribute id 
     * 
     * @return id of <code>Digital</code> objects. It is an int number
     */
    public int getId() {
        return id;
    }
    
    /**
     * Set new value of attribute id
     * 
     * @param id the id of news. It is an int number
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Get value of attribute title
     * 
     * @return title of <code>Digital</code> objects.
     * It is a <code>java.lang.String</code> object
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Set new value of attribute title
     * 
     * @param title the title of news. 
     * It is a <code>java.lang.String</code> object
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Get value of attribute description
     * 
     * @return description of <code>Digital</code> objects.
     * It is a <code>java.lang.String</code> object
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Set new value of attribute description
     * 
     * @param description the description of news. 
     * It is a <code>java.lang.String</code> object
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Get value of attribute image
     * 
     * @return image of <code>Digital</code> objects. 
     * It is a <code>java.lang.String</code> object
     */
    public String getImage() {
        return image;
    }
    
    /**
     * Set new value of attribute image
     * 
     * @param image the image of news. 
     * It is a <code>java.lang.String</code> object
     */
    public void setImage(String image) {
        this.image = image;
    }
    
    /**
     * Get value of attribute author
     * 
     * @return author of <code>Digital</code> objects.
     * It is a <code>java.lang.String</code> object
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * Set new value of attribute author
     * 
     * @param author the author of news. 
     * It is a <code>java.lang.String</code> object
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * Get value of attribute timePost
     * 
     * @return timePost the time post of <code>Digital</code> objects. 
     * It is a <code>java.util.Date</code> object
     */
    public Date getTimePost() {
        return timePost;
    }
    
    /**
     * Set new value of attribute timePost
     * 
     * @param timePost the time post of news. 
     * It is a <code>java.util.Date</code> object
     */ 
    public void setTimePost(Date timePost) {
        this.timePost = timePost;
    }
    
    /**
     * Get value of attribute shortDes
     * 
     * @return shortDes the short description of <code>Digital</code> objects. 
     * It's a <code>java.lang.String</code> object
     */
    public String getShortDes() {
        return shortDes;
    }
    
    /**
     * Set new value of attribute shortDes
     * 
     * @param shortDes the short description of news. 
     * It is a <code>java.lang.String</code> object
     */
    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }
    
    
}
