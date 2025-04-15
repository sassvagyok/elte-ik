package printed.material;

public class Book {
    public static final String defaultAuthor = "John Steinbeck";

    private String author;

    public String getAuthor() {
        return author;
    }

    public Book() {
        this(defaultAuthor, defaultTitle, defaultPageCount);
    }

    public Book(String author, String title, int pageCount) {
        checkInitData(author, title, pageCount);
        initBook(author, title, pageCount);

    }

    protected void initBook(String author, String title, int pageCount) {
        this.author = author;
        this.title = title;
        this.pageCount = pageCount;
    }

    public void checkInitData(String author, String title, int pageCount) {
        if (author.lenght() < 2) {
            throw new IllegalArgumentException("Tul rovid szerzo");
        }

        if (title.lenght() < 4) {
            throw new IllegalArgumentException("Tul rovid cim");
        }

        if (pageCount < 0) {
            throw new IllegalArgumentException("Nem pozitiv az oldalak szama");
        }
    }

    @Override
    public String toString() {

    }
}