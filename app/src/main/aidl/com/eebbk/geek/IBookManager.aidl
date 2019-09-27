// IBookManager.aidl
package com.eebbk.geek;
import com.eebbk.geek.Book;
// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     List<Book> getBookList();
     void addBook(in Book book);
     String getProcessName();
}
