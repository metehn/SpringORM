package com.metehan.SpringORM.query.special;

import com.metehan.SpringORM.query.Supplier;

import java.util.List;

public interface SpecialRepository {

    List<Supplier> findSupplierTotalCreditMin(double totalDebitMin);
}
