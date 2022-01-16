package com.example.fullstack.controller;

import com.example.fullstack.dao.DAO;
import com.example.fullstack.model.Person;
import com.example.fullstack.model.PresentCard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.fullstack.dao.PersonJDBCDAO;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class Controller {
    private final PersonJDBCDAO<Person> personDao;
    private final DAO<PresentCard> cardDao;

    public Controller(PersonJDBCDAO<Person> personDao, DAO<PresentCard> cardDao) {
        this.personDao = personDao;
        this.cardDao = cardDao;
    }

    @GetMapping("/cards")
    public List<PresentCard> getCards() {
        return cardDao.list();
    }

    @GetMapping("/user_all_cards/{id}")
    public List<PresentCard> getUserCards(@PathVariable int id) {
        return personDao.suggestedPresents(id);
    }

    @PostMapping("/post_new_card")
    public ResponseEntity<Void> addCard(@RequestBody PresentCard card) {
        cardDao.create(card);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete_card/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable int id) {
        cardDao.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users")
    public List<Person> getPersons() {
        return personDao.list();
    }
}
