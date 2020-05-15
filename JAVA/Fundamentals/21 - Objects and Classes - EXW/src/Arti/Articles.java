package Arti;

public class Articles {
    private String title;
    private String content;
    private String author;

    public Articles(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //    •	edit (new content) – change the old content with the new one
    public void edit(String content) {
        this.content = content;
    }

    //•	changeAuthor (new author) – change the author
    public void changeAuthor(String author) {
        this.author = author;
    }

    //•	rename (new title) – change the title of the article
    public void rename(String title) {
        this.title = title;
    }


    //•	override toString – print the article in the following format:
    //            "{title} - {content}:{author}"
    @Override
    public String toString() {
        String result = String.format("%s - %s: %s", this.title, this.content, this.author);
        return result;
    }

}
