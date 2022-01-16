package com.example.fullstack;

import com.example.fullstack.dao.DAO;
import com.example.fullstack.dao.PersonJDBCDAO;
import com.example.fullstack.model.Person;
import com.example.fullstack.model.PresentCard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import java.util.List;
//import java.util.Optional;

@SpringBootApplication
public class FullStackApplication {

    private static PersonJDBCDAO<Person> personDao;
    private static DAO<PresentCard> cardDao;

    public FullStackApplication(PersonJDBCDAO<Person> personDao, DAO<PresentCard> cardDao) {
        this.personDao = personDao;
        this.cardDao = cardDao;
    }

    public static void main(String[] args) {

        SpringApplication.run(FullStackApplication.class, args);
//        Person person = new Person("alina", "alina oreshina", "alina@phystech.edu", "alina123");
//        personDao.create(person);
//        Optional<Person> first = personDao.get(1);
//        System.out.println(first.get());
//
//        person.setEmail("alina@mail.ru");
//        personDao.update(person, 4);
//
//        List<Person> persons = personDao.list();
//        persons.forEach(System.out::println);
//
//        PresentCard card = new PresentCard(4, "mobile phone", 20000, "https://www.googleadservices.com");
//        cardDao.create(card);
//        Optional<PresentCard> secondCard = cardDao.get(2);
//        System.out.println(secondCard.get());
//
//        card.setPrice(25000);
//        cardDao.update(card, 4);
//
//        List<PresentCard> allCards = cardDao.list();
//        allCards.forEach(System.out::println);
//
//        List<PresentCard> cardsList = personDao.suggestedPresents(2);
//        cardsList.forEach(System.out::println);
    }

}
