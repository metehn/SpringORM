package com.metehan.SpringORM.query.special;

import com.metehan.SpringORM.query.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpecialRepositoryImpl implements SpecialRepository {

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Supplier> findSupplierTotalCreditMin(double totalCreditMin) {

        String jpql = "select s from Supplier s where s.totalCredit >= :totalCreditMin ";
        TypedQuery<Supplier> query = entityManager.createQuery(jpql, Supplier.class);
        query.setParameter("totalCreditMin", totalCreditMin);

        return query.getResultList();
    }
}
