package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        Author[] copyAuthors = new Author[authors.length + 1];
        System.arraycopy(authors, 0, copyAuthors, 0, authors.length);
        copyAuthors[copyAuthors.length - 1] = author;
        authors = copyAuthors.clone();
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author elem : authors) {
            if (elem.getName().equals(name) && elem.getLastName().equals(lastname)) {
                return elem;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }
        Author[] copyAuthor = new Author[authors.length - 1];
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].equals(author)) {
                continue;
            }
            copyAuthor[i] = authors[i];
        }
        authors = copyAuthor.clone();
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
