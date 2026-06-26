public class book {
    //Gồm các thuộc tính bookId, title, author, và một cờ boolean isBorrowed (để biết sách đang rảnh hay đã bị mượn).
    private String bookId;
    private String title;
    private String author;
    private boolean isBorrowed;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String display(){
        return this.bookId+" "+this.title+" "+this.author+" "+this.isBorrowed+"\n";
    }
}
