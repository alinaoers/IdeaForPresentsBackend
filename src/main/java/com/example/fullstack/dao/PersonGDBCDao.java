package com.example.fullstack.dao;

import com.example.fullstack.model.Person;
import com.example.fullstack.model.PresentCard;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Component
public class PersonGDBCDao implements PersonJDBCDAO<Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonGDBCDao.class);
    private JdbcTemplate jdbcTemplate;

    RowMapper<Person> rowMapper = (rs, rowNum) -> {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setUsername(rs.getString("username"));
        person.setFullname(rs.getString("fullname"));
        person.setEmail(rs.getString("email"));
        person.setPassword(rs.getString("password"));
        return person;
    };

    public PersonGDBCDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> list() {
        String sql = "SELECT id, username, fullname, email, password FROM Person";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Person person) {
        String sql = "INSERT INTO Person(username, fullname, email, password) VAlUES(?,?,?,?)";
        jdbcTemplate.update(sql, person.getUsername(), person.getFullname(), person.getEmail(), person.getPassword());
    }

    @Override
    public Optional<Person> get(int id) {
        String sql = "SELECT * FROM PERSON WHERE id = ?;";
        Person person = null;
        try {
            person = jdbcTemplate.queryForObject(sql, new Object[]{id}, new int[]{Types.INTEGER}, rowMapper);
        } catch (DataAccessException ex) {
            log.info("person not found " + id);
        }

        return Optional.ofNullable(person);
    }

    @Override
    public void update(Person person, int id) {
        String sql = "UPDATE Person SET username = ?, fullname = ?, email = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql, person.getUsername(), person.getFullname(), person.getEmail(), person.getPassword(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
    }

    private RowMapper<PresentCard> cardRowMapper = (rs, rowNum) -> {
        PresentCard card = new PresentCard();
        card.setId(rs.getInt("id"));
        card.setClientId(rs.getInt("clientId"));
        card.setDescription(rs.getString("description"));
        card.setPrice(rs.getInt("price"));
        card.setLink(rs.getString("link"));
        return card;
    };

    @Override
    public List<PresentCard> suggestedPresents(int id) {
        String sql = "select * from PRESENT_CARD where clientId = ?;";
        return jdbcTemplate.query(sql, new Object[]{id}, new int[]{Types.INTEGER}, cardRowMapper);
    }
}
