package com.metehan.SpringORM.query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    @Query("select supplier from Supplier as supplier where supplier.supplierName = :supplierName")
    List<Supplier> findSuppliersByName(@Param("supplierName") String supplierName);
}
