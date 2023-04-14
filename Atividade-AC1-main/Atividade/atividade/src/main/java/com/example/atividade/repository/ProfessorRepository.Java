package com.example.atividade.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.atividade.model.Professor;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class ProfessorRepository {
    @Autowired
    EntityManager entityManager;

    @Transactional
    public Professor inserir(Professor professor) {
        return entityManager.merge(professor);
    }

    public List<Professor> obterTodos() {
        return entityManager
                .createQuery("from Professor", Professor.class)
                .getResultList();
    }
}
