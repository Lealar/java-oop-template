package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] copySchoolBook = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks, 0, copySchoolBook, 0, schoolBooks.length);
        copySchoolBook[copySchoolBook.length - 1] = book;
        schoolBooks = copySchoolBook.clone();
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int bookCounter = 0;
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                bookCounter += 1;
            }
        }
        SchoolBook[] arraySchoolBook = new SchoolBook[bookCounter];
        int addedBook = 0;
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                arraySchoolBook[addedBook] = book;
                addedBook += 1;
            }
        }
        return arraySchoolBook;
    }

    @Override
    public boolean removeByName(String name) {
        int counterDiffrentName = schoolBooks.length;
        boolean isNameInSchoolBooks = false;
        for (SchoolBook elem : schoolBooks) {
            counterDiffrentName--;
            isNameInSchoolBooks = true;
        }
        if (!isNameInSchoolBooks) {
            return false;
        }
        SchoolBook[] copySchoolBook = new SchoolBook[counterDiffrentName];
        for (int i = 0, j = 0; i < schoolBooks.length; i++) {
            if (!schoolBooks[i].getName().equals(name)) {
                copySchoolBook[j] = schoolBooks[i];
                j++;
            }
        }
        schoolBooks = copySchoolBook.clone();
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
