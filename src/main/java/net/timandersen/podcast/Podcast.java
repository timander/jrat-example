package net.timandersen.podcast;

public class Podcast {

    private int id = -1;
    private String title;
    private String link;
    private String description;
    private String date;
    private String author;

    public Podcast() {
    }

    public Podcast(String title, String link, String description, String date, String author) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.date = date;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Podcast{" +
               "\nid=" + id +
               ", \ntitle='" + title + '\'' +
               ", \nlink='" + link + '\'' +
               ", \ndescription='" + description + '\'' +
               ", \ndate='" + date + '\'' +
               ", \nauthor='" + author + '\'' +
               '}' + "\n";
    }
}
