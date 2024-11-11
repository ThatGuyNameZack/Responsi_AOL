import datetime
import os


# Book class to manage book information and availability
class Books:
    def __init__(self, library_name):
        self.books_file = "book_list.txt"
        self.library_name = library_name
        self.books_dict = {}
        book_id = 101  # Starting book ID

        try:
            with open(self.books_file) as bk:
                content = bk.readlines()
            for line in content:
                self.books_dict.update({
                    str(book_id): {
                        "book_title": line.strip(),
                        "lender_name": "",
                        "issue_date": "",
                        "status": "Available"
                    }
                })
                book_id += 1
        except FileNotFoundError:
            print(f"File '{self.books_file}' not found.")

    def display_books(self):
        print("\n--- List of Books ---")
        print("ID", "\t", "Title", "\t\t", "Status")
        print("------------------------")
        for key, value in self.books_dict.items():
            print(key, "\t", value.get("book_title"), "- [", value.get("status"), "]")

    def is_available(self, book_id):
        return self.books_dict[book_id]["status"] == "Available"

    def lend_book(self, book_id, lender_name):
        if self.is_available(book_id):
            self.books_dict[book_id]["status"] = "Borrowed"
            self.books_dict[book_id]["lender_name"] = lender_name
            self.books_dict[book_id]["issue_date"] = datetime.date.today()
            return True
        return False

    def return_book(self, book_id):
        if not self.is_available(book_id):
            self.books_dict[book_id]["status"] = "Available"
            self.books_dict[book_id]["lender_name"] = ""
            self.books_dict[book_id]["issue_date"] = ""
            return True
        return False


# Member class to manage member information
class Member:
    def __init__(self, member_id, name, contact_info):
        self.member_id = member_id
        self.name = name
        self.contact_info = contact_info

    def __str__(self):
        return f"MemberID: {self.member_id}, Name: {self.name}, Contact: {self.contact_info}"


# Librarian class to manage book and member records
class Librarian:
    def __init__(self, librarian_id):
        self.librarian_id = librarian_id

    def add_book(self, library, book_title):
        new_id = str(len(library.books_dict) + 101)
        library.books_dict[new_id] = {
            "book_title": book_title,
            "lender_name": "",
            "issue_date": "",
            "status": "Available"
        }
        print(f"Added book: {book_title}")

    def remove_book(self, library, book_id):
        if book_id in library.books_dict:
            removed_book = library.books_dict.pop(book_id)
            print(f"Removed book: {removed_book['book_title']}")
        else:
            print("Book ID not found.")


# Transaction class to record borrow and return transactions
class Transaction:
    transaction_id_counter = 1

    def __init__(self, member_id, book_id, transaction_type):
        self.transaction_id = Transaction.transaction_id_counter
        Transaction.transaction_id_counter += 1
        self.member_id = member_id
        self.book_id = book_id
        self.transaction_type = transaction_type
        self.date = datetime.date.today()

    def __str__(self):
        return f"TransactionID: {self.transaction_id}, MemberID: {self.member_id}, BookID: {self.book_id}, Type: {self.transaction_type}, Date: {self.date}"


# Library system to coordinate all actions
class LibrarySystem:
    def __init__(self, library_name, librarian_id):
        self.books = Books(library_name)
        self.members = {}
        self.transactions = []
        self.librarian = Librarian(librarian_id)

    def add_member(self, member_id, name, contact_info):
        self.members[member_id] = Member(member_id, name, contact_info)
        print(f"Added member: {name}")

    def remove_member(self, member_id):
        if member_id in self.members:
            removed_member = self.members.pop(member_id)
            print(f"Removed member: {removed_member.name}")
        else:
            print("Member ID not found.")

    def borrow_book(self, member_id, book_id):
        if member_id in self.members and book_id in self.books.books_dict:
            if self.books.lend_book(book_id, self.members[member_id].name):
                transaction = Transaction(member_id, book_id, "Borrow")
                self.transactions.append(transaction)
                print(f"Book borrowed: {self.books.books_dict[book_id]['book_title']} by {self.members[member_id].name}")
            else:
                print("Book is already borrowed.")
        else:
            print("Invalid member ID or book ID.")

    def return_book(self, member_id, book_id):
        if member_id in self.members and book_id in self.books.books_dict:
            if self.books.return_book(book_id):
                transaction = Transaction(member_id, book_id, "Return")
                self.transactions.append(transaction)
                print(f"Book returned: {self.books.books_dict[book_id]['book_title']} by {self.members[member_id].name}")
            else:
                print("Book is already available.")
        else:
            print("Invalid member ID or book ID.")

    def display_transactions(self):
        print("\n--- Transaction Records ---")
        for transaction in self.transactions:
            print(transaction)


# Example usage
library_system = LibrarySystem("Library of Dojima", "L001")

# Add books to the library
library_system.librarian.add_book(library_system.books, "Python Programming")
library_system.librarian.add_book(library_system.books, "Data Science with Python")
library_system.librarian.remove_book(library_system.books, "Python Programming")

# Add and remove members!
library_system.add_member("M001", "John Doe", "123456789")
library_system.add_member("M002", "Jane Doe", "987654321")
library_system.remove_member("M001", "John Doe", "123456789")

# Display all books
library_system.books.display_books()

# Borrow and return books through ID
library_system.borrow_book("M001", "101")
library_system.return_book("M001", "101")

# Display transactions
library_system.display_transactions()
