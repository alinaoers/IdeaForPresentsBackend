package com.example.fullstack.dao;

import com.example.fullstack.model.Person;
import com.example.fullstack.model.PresentCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Component
public class PresentCardJDBCDao implements DAO<PresentCard>{

    private static final Logger log = LoggerFactory.getLogger(PersonGDBCDao.class);
    private JdbcTemplate jdbcTemplate;

    RowMapper<PresentCard> rowMapper = (rs, rowNum) -> {
        PresentCard card = new PresentCard();
        card.setId(rs.getInt("id"));
        card.setClientId(rs.getInt("clientId"));
        card.setDescription(rs.getString("description"));
        card.setPrice(rs.getInt("price"));
        card.setLink(rs.getString("link"));
        return card;
    };

    public PresentCardJDBCDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PresentCard> list() {
        String sql = "SELECT id, clientId, description, price, link FROM PRESENT_CARD";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(PresentCard presentCard) {
        String sql = "INSERT INTO PRESENT_CARD(clientId, description, price, link) VAlUES(?,?,?,?)";
        jdbcTemplate.update(sql, presentCard.getClientId(), presentCard.getDescription(), presentCard.getPrice(), presentCard.getLink());
    }

    @Override
    public Optional<PresentCard> get(int id) {
        String sql = "SELECT * FROM PRESENT_CARD WHERE id = ?;";
        PresentCard card = null;
        try {
            card = jdbcTemplate.queryForObject(sql, new Object[]{id}, new int[]{Types.INTEGER}, rowMapper);
        } catch (DataAccessException ex) {
            log.info("Present card not found " + id);
        }

        return Optional.ofNullable(card);
    }

    @Override
    public void update(PresentCard presentCard, int id) {
        String sql = "UPDATE PRESENT_CARD SET clientId = ?, description = ?, price = ?, link = ? WHERE id = ?";
        jdbcTemplate.update(sql, presentCard.getClientId(), presentCard.getDescription(), presentCard.getPrice(), presentCard.getLink(), id);
    }

    @Override
    public void delete(int id)  {
        jdbcTemplate.update("DELETE FROM PRESENT_CARD WHERE id = ?", id);
    }
}
