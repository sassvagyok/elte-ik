package printed.material;

import printed.material.CoverType;

public class PrintedBook extends Book {
    protected CoverType coverType;

    public PrintedBook(String author, String title, int pageCount, CoverType coverType) {
        super(author, title, pageCount + 6);
        this.coverType = coverType;
    }

    public PrintedBook(Book book) {
        this(book.getAuthor, book.getTitle, book.getPageCount CoverType.HARDCOVER);
    }

    public static PrintedBook decode(String text) {
        String items = text.split("");
    
        PrintedBook pb = new PrintedBook(items[0].strip(), items[1].strip(), Integer.parseInt(items[2].strip()), CoverType.valueOf(items[3].srip()));

        return pb;
    }

    public int getPrice() {
        if (coverType == CoverType.HARDCOVER) {
            return pageCount * 3;
        } else {
            return pageCount * 2;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append(" (");
        sb.append(coverType);
        sb.append(")");

        return sb.toString();
    }
}