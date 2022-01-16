package com.example.fullstack.dao;

import com.example.fullstack.model.Person;
import com.example.fullstack.model.PresentCard;

import java.util.List;

public interface PersonJDBCDAO<T> extends DAO<T>{

    public List<PresentCard> suggestedPresents(int id);
}
