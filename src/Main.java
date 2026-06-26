void main() {
    HashMap<String, book> bookRetrieval = new HashMap<>();
    ArrayList<book> books = new ArrayList<>();
    ArrayList<member> members = new ArrayList<>();

    System.out.println("Đang nạp dữ liệu test...");

    book b1 = new book();
    b1.setBookId("B01"); b1.setTitle("Lap trinh Java Core"); b1.setAuthor("James"); b1.setBorrowed(false);

    book b2 = new book();
    b2.setBookId("B02"); b2.setTitle("He thong IoT Smart Home"); b2.setAuthor("Quang"); b2.setBorrowed(true);

    book b3 = new book();
    b3.setBookId("B03"); b3.setTitle("Gia su AI Van hoc"); b3.setAuthor("Quang"); b3.setBorrowed(false);

    books.add(b1); bookRetrieval.put(b1.getBookId(), b1);
    books.add(b2); bookRetrieval.put(b2.getBookId(), b2);
    books.add(b3); bookRetrieval.put(b3.getBookId(), b3);

    member m1 = new member();
    m1.setMemberId("M01"); m1.setName("Quang");
    m1.addBorrowedBooks(b2); // Thành viên này đang mượn sẵn quyển B02
    members.add(m1);

    member m2 = new member();
    m2.setMemberId("M02"); m2.setName("Tuan");
    members.add(m2);

    System.out.println("Nạp xong! Các ID sách: B01, B02, B03. Các ID mem: M01, M02.");

    Scanner sc = new Scanner(System.in);
    while(true){
        String choice;
        choice = sc.nextLine();
        if(choice.equals("stop")){
            break;
        } else if (choice.equals("a b")) {
            System.out.println("Them sach:");
            book Book= new book();
            System.out.println("nhập mã sach:");
            String bookId= sc.nextLine();
            // check tồn tại
            if (bookRetrieval.get(bookId)!=null){
                System.out.println("đã có id trùng đổi id khác");
            }else{
                System.out.println("nhập title sach:");
                String title = sc.nextLine();
                System.out.println("nhập tác giả sach:");
                String author= sc.nextLine();
                Book.setBookId(bookId);
                Book.setTitle(title);
                Book.setAuthor(author);
                Book.setBorrowed(false);
                books.add(Book);
                bookRetrieval.put(bookId,Book);
                for(book x:books){
                    System.out.println(x.display());
                }
            }
        } else if (choice.equals("a m")) {
            member Member= new member();
            System.out.println("nhập mã member:");
            String memberId = sc.nextLine();

            // check tồn tại member
            boolean tf=false;
            for(member x: members){
                if(x.getMemberId().equals(memberId)){
                    tf=true;
                    break;
                }
            }
            if(tf){
                System.out.println("đã tồn tại mem id này");
            }else{
                System.out.println("nhập tên member:");
                String name= sc.nextLine();
                Member.setMemberId(memberId);
                Member.setName(name);
                members.add(Member);
                for(member x: members){
                    System.out.println(x.display());
                }
            }

        } else if (choice.equals("d b")) {
            // xóa sách thì cần kiểm tra còn ai đang mượn không, không ai mượn => xóa, có người mượn báo ngược lại
            System.out.println("nhập id sách:");
            String bookId= sc.nextLine();
            if (bookRetrieval.get(bookId)!=null){
                if(!bookRetrieval.get(bookId).isBorrowed()){
                    books.removeIf(book -> book.getBookId().equals(bookId));
                    bookRetrieval.remove(bookId);
                    for(book x: books) System.out.println(x.display());
                }else{
                    System.out.println("Sách đang được mượn không thể xóa");
                }
            }else {
                System.out.println("không tồn tại sách này!");
            }

        } else if (choice.equals("d m")) {
            // xóa người thì set trạng thái mượn của sách là false trước rồi mới xóa
            System.out.println("nhập id mem:");
            String memberId= sc.nextLine();
            for(member x: members){
                if(x.getMemberId().equals(memberId)){
                    for(book y: x.getBorrowedBooks()){
                        y.setBorrowed(false);
                    }
                }
            }
            members.removeIf(member -> member.getMemberId().equals(memberId));
            for(member x: members) System.out.println(x.display());

        } else if (choice.equals("u b")) {
            System.out.println("nhập id sách");
            String bookId= sc.nextLine();
            // ktra tồn tại
            if (bookRetrieval.get(bookId) != null){
                System.out.println("nhập tên sách");
                String title = sc.nextLine();
                System.out.println("nhập tên tgia");
                String author= sc.nextLine();
                bookRetrieval.get(bookId).setTitle(title);
                bookRetrieval.get(bookId).setAuthor(author);
                System.out.println("update thành công");
            }else{
                System.out.println("Không tồn tại sách");
            }
            for(book x:books){
                System.out.println(x.display());
            }
        } else if (choice.equals("u m")) {
            System.out.println("nhập id mem:");
            String memberId= sc.nextLine();
            boolean tf=false;
            for (member x:members){
                if(x.getMemberId().equals(memberId)){
                    member mem= x;
                    System.out.println("nhập tên mới:");
                    String name= sc.nextLine();
                    x.setName(name);

                    // check xem book tồn tại không
                    System.out.println("nhập mã sách:");
                    String bookId=sc.nextLine();
                    if(!bookId.isEmpty()){// rỗng thì không cập nhật sách
                        if (bookRetrieval.get(bookId)!=null){
                            book Book= bookRetrieval.get(bookId);
                            if(Book.isBorrowed()){
                                System.out.println("đang cho người khác mượn không được mượn");
                            }else{
                                Book.setBorrowed(true);
                                x.addBorrowedBooks(Book);
                                System.out.println("cập nhật thành công");
                            }
                        }else{
                            System.out.println("Không tồn tại sách");
                        }
                    }
                    tf=true;
                    break;
                }
            }
            if(!tf){
                System.out.println("không tồn tại");
            }
        } else if (choice.equals("r b")) {
            System.out.println("nhập id mem:");
            String memberId= sc.nextLine();
            boolean tf=false;
            for(member x: members){
                if(x.getMemberId().equals(memberId)){
                    member Member=x;
                    System.out.println("nhập id sách muốn trả");
                    String bookId= sc.nextLine();
                    if(bookRetrieval.get(bookId)!=null){
                        book Book= bookRetrieval.get(bookId);
                        if (x.getBorrowedBooks().contains(Book)) {
                            x.deleteBorrowedBooks(Book);
                            Book.setBorrowed(false);
                            System.out.println("Trả sách thành công!");
                        } else {
                            System.out.println("Người này không mượn quyển sách này!");
                        }
                    }else{
                        System.out.println("Khong ton tai sách nay");
                    }
                    tf=true;
                    break;
                }
            }

            if(!tf){
                System.out.println("không tồn tại mem id này");
            }


        } else{
            System.out.println("Hãy thử lại! không có lựa chọn này");
        }
    }
}
