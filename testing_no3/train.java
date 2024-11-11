

class books{
   private String book_title;
   private String author;
   private String isbn;
   public boolean Available;


    public books(String book_title, String author, String isbn)
    {
        this.book_title = title;
        this.author = author;
        this.isbn = isbn;
        this.Available = true; //it will be avalaible by default
    }

    public boolean Avalaible(Available){
        return Available;
    }

    public void setAvailable(boolean avalaible){
        this.Available = Available; //this is a setting in default hat i wont change., bascially making status avalible
    }

    public String getIsbn(String isbn){
        return isbn; //when you pick a book you get the ISBN
    }
    //we'll also add an override
    @Override
        public String toString(){
           return "books{"+"book_title= '"+book_title+ '\''+ "author= " + author + '\'' + "isbn= " + isbn + '\'' + "Avalible" + Available + '\'''}';
        }
    }  


class member{ //member will connect to librarian because its basically a member
    private int MemberID;
    private String name;
    private int contactInfo;


    public member(int MemberID, String name, int contactInfo){
        this.MemberID = MemberID;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public getMemberID(int MemberID){
        return MemberID;
    }

    //we'll add an override if theres a problem
    @Override
        public String toString(){
            return "member{"+"MemberID= " + MemberID + '\'' + "name= "+ name + '\'' + "contactInfo= " + contactInfo + '\'''}'
        }

}
    class librarian extends member{
        private int librarianID;
    
    }





class Transaction{
    private int transactionID;
    private int transactionDate;
}
