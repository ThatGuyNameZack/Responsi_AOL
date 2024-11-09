import datetime
import os

class Books:
    def __init__(self, books, available):
        self.books = "book_list.txt" #already have the author init
        self.library_name = library_name
        self.books_dict = {}
        
        Id = 101
        
        try:
            with open(self.books) as bk:
                content = bk.readlines()
            for line in content:
                self.books_dict.update({
                    str(Id): {
                        "book_title": line.strip(),  # Hapus karakter newline
                        "lender_name": "",
                        "issue_date": "",
                        "Status": "Available"
                    }
                })
                Id += 1
        except FileNotFoundError:
            print(f"file '{self.books} not found.")

    def display_books(self):
        print("-------------------------List of Books---------------------")
        print("Books ID", "\t", "Title")
        print("-----------------------------------------------------------")
        for key, value in self.books_dict.items():
            print(key, "\t\t", value.get("books_title"), "-[",value.get("Status"),"]")

        library = Books("book_list.txt", "Library Of Dojima")
        library.display_books()





class_member:
def __init__(self, memberID, name, contact_number):
    self.memberID = 
    self.name = 
    self.contact_number = 


#lots of errors but i will fix it in due time.