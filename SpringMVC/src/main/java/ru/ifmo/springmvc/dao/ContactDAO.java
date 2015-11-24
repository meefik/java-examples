package ru.ifmo.springmvc.dao;

import java.util.List;
import ru.ifmo.springmvc.domain.Contact;

public interface ContactDAO {

    public void addContact(Contact contact);

    public List<Contact> listContact();

    public void removeContact(Integer id);
}
