package ru.ifmo.springmvc.service;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import ru.ifmo.springmvc.dao.ContactDAO;
import ru.ifmo.springmvc.domain.Contact;
 
@Service
public class ContactServiceImpl implements ContactService {
 
    @Autowired
    private ContactDAO contactDAO;
 
    @Transactional
    @Override
    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }
 
    @Transactional
    @Override
    public List<Contact> listContact() {
         return contactDAO.listContact();
    }
 
    @Transactional
    @Override
    public void removeContact(Integer id) {
        contactDAO.removeContact(id);
    }
}