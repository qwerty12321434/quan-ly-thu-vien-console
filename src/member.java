import java.util.ArrayList;
import java.util.List;

public class member {
    //Gồm memberId, name, và một danh sách List<Book> borrowedBooks (những cuốn sách người này đang giữ).
    private String memberId,name;
    private List<book> borrowedBooks= new ArrayList<>();

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<book> borroedBooks) {
        this.borrowedBooks = borroedBooks;
    }

    public void addBorrowedBooks(book book){
        borrowedBooks.add(book);
    }

    public void deleteBorrowedBooks(book book){
        borrowedBooks.remove(book);
    }

    public String display(){
        return this.memberId+" "+this.name+"\n";
    }
}
