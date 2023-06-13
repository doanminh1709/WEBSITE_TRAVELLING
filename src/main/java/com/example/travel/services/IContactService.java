package com.example.travel.services;

import com.example.travel.daos.Contact;
import com.example.travel.dtos.ContactDTO;

import java.util.List;

public interface IContactService {
    List<Contact> getAllContact();
    Contact getContactById(Long id);
    Contact createContact(ContactDTO contactDTO);
    Contact editContactById(Long id, ContactDTO contactDTO);
    void deleteContactById(Long id);
}
