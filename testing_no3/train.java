import java.util.HashMap;
import java.util.Map;

class Books{
   private String book_title;
   private String author;
   private String Isbn;
   public boolean StatusAvailable;
 

    public Books(String book_title, String author, String Isbn)
    {
        this.book_title = book_title;
        this.author = author;
        this.Isbn = Isbn;
        this.StatusAvailable = true; //it will be avalaible by default
    }

    public boolean isAvailable(){
        return StatusAvailable;
    }

    public void setAvailable(boolean StatusAvailable){
        this.StatusAvailable = StatusAvailable; //this is a setting in default hat i wont change., bascially making status avalible
    }

    public String getIsbn(){
        return Isbn; //when you pick a book you get the ISBN
    }
    //we'll also add an override
    @Override
        public String toString(){
            return "Books{" + "book_title='" + book_title + '\'' + ", author='" + author + '\'' + ", Isbn='" + Isbn + '\'' + ", Available=" + StatusAvailable + '}';

        }
    }  


class Members{ //member will connect to librarian because its basically a member
    private int MemberID;
    private String name;
    private int contactInfo;
 

    public Members(int MemberID, String name, int contactInfo){
        this.MemberID = MemberID;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public int getMemberID(){
        return MemberID;
    }

    //we'll add an override if theres a problem
    @Override
        public String toString(){
            return "Member{" + "MemberID=" + MemberID + ", name='" + name + '\'' + ", contactInfo=" + contactInfo + '}';

        }

}
    class Librarian extends Member{
        private int librarianID;
        
        public Librarian(int librarianID, int MemberID, String name, int contactInfo){
            super(MemberID, name, contactInfo);
            this.librarianID = librarianID;
        }

        public void addNewBook(Map<String, Books>Books, Books book){ //we'll add a new book basically this uses hashmap we make a new string of book, in the class books
                Books.put(book.getIsbn(), book);                                    //in the public Lbook we will add a new book in the public class
                System.out.println("a new Book added"+book);
        }

        public void removeBook(Map<String, Books>Books, String Isbn){
            Books removedBook = Books.remove(Isbn);
            if(removedBook!= null){
                System.out.println("Book removed" +removedBook);
            }else{
                System.out.println("did not found"+ Isbn);
            }
        }

        public void addMember(Map<Integer, Members>Members, Member member){
            Members.put(member.getMemberID(), member);
            System.out.println("a new member has been added" +member);
        }

        public void removeMember(Map<Integer, Members>Members, int MemberID){
            Members removedMember = Members.remove(MemberID);
            if(removedMember != null){
                System.out.println("removed member" +removedMember);
            }else{
                System.out.println("the member "+ MemberID + "is not found");
            }
        }
    }





class Transaction{
    private int transactionID;
    private int transactionDate;
    private String Isbn;
    private int MemberID;

    public Transaction(int transactionID, int transactionDate, String Isbn, int MemberID){
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.Isbn = Isbn;
        this.MemberID = MemberID;

    }

    public void borrowBook(Map<String, Books> Books, String Isbn, Map<Integer, Members>Members, int MemberID){
        Books book = Books.get(Isbn);
        Members member = Members.get(MemberID);
        
        if(book != null && member != null && book.isAvailable()){
            book.setAvailable(false);
            System.out.println("Book"+ book + "borrowed by" + member.getMemberID());
        } else {
            System.out.println("The book is not here");
        }
    }
    public void returnBook(Map<String, Books> Books, String Isbn, Map<Integer, Members> Members, int MemberID){
        Books book = Books.get(Isbn);
        Members member = Members.get(MemberID);

        if(book != null && member != null && book.isAvailable()){
          book.setAvailable(true);
          System.out.println("Book" + book + "Returned by" + member.getMemberID());
        }else{
            System.out.println("The book is not found");
        }


    }
        


    public class Main {
        public static void main(String[] args) {
            // Create maps for Books and Members
            Map<String, Books> Books = new HashMap<>();
            Map<Integer, Members> Members = new HashMap<>();
    
            // Add some sample books and members
            Books.put("1234", new Books("Book Title", "Author Name", "1234"));
            Members.put(1, new Members(1, "Member Name", 123456789));
    
            // Create librarian
            Librarian librarian = new Librarian(101, 1, "Librarian Name", 987654321);
    
            // Test adding/removing books and members
            librarian.addNewBook(Books, new Books("New Book", "New Author", "5678"));
            librarian.removeBook(Books, "1234");
    
            librarian.addMember(Members, new Members(2, "Another Member", 112233445));
            librarian.removeMember(Members, 1);
    
            // Create a transaction and test borrowing/returning books
            Transaction transaction = new Transaction(1, 20241111, "5678", 1);
            transaction.borrowBook(Books, "5678", Members, 1);
            transaction.returnBook(Books, "5678", Members, 1);
        }
    }

    






}
