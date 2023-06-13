package com.example.travel.services.imp;

import com.example.travel.daos.Contact;
import com.example.travel.dtos.ContactDTO;
import com.example.travel.exceptions.NotFoundException;
import com.example.travel.repositories.ContactRepository;
import com.example.travel.services.IContactService;
import com.example.travel.utils.Convert;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImp implements IContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isEmpty()) {
            throw new NotFoundException("Cound not find a contact with id: " + id);
        }
        return contact.get();
    }

    @Override
    public Contact createContact(ContactDTO contactDTO) {
        Contact contact = modelMapper.map(contactDTO, Contact.class);
        return contactRepository.save(contact);
    }

    @Override
    public Contact editContactById(Long id, ContactDTO contactDTO) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isEmpty()) {
            throw new NotFoundException("Cound not find a contact with id: " + id);
        }
        Contact newContact = contact.get();
        newContact = Convert.fromContactDTOToContact(contactDTO);

        return contactRepository.save(newContact);
    }

    @Override
    public void deleteContactById(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isEmpty()) {
            throw new NotFoundException("Cound not find a contact with id: " + id);
        }
        contactRepository.deleteById(id);
    }
}
