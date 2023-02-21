package com.metehan.SpringORM.query;

import com.metehan.SpringORM.query.special.SpecialRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SupplierController {

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    SpecialRepository specialRepository;
    // İki tane repository tanımlamaya gerek yok. SupplierRepository interfacesi  SpecialRepository'i extends ederse
    // 1 repository kullanmış oluruz.

    @GetMapping("/supplier/generate")
    @ResponseBody
    public String generateSuppliers(){

        for (int i=0; i<100;i++){

            Supplier supplier = new Supplier(0, "Supplier " + i , 100+25*i);
            supplierRepository.save(supplier);

        }
        return "Generated: " + 100;
    }

    @GetMapping("/supplier/generate2")
    @ResponseBody
    public String generate2Supplier(@RequestParam(name = "name") String supplierName,  @RequestParam(name ="credit") double totalCredit){

            Supplier supplier = new Supplier(0, supplierName , totalCredit);
            supplierRepository.save(supplier);


        return "Generated: " + supplierName;
    }


    @GetMapping("/supplier/findall")
    @ResponseBody
    public String findAllSuppliers(){

       Iterable<Supplier> suppliers = supplierRepository.findAll();

       int count = 0;
       double grandDebit = 0;

       for(Supplier supplier : suppliers){

           grandDebit += supplier.getTotalCredit();
           count++;

       }
        return "Suppliers: " + 100 + " Total credit: " + grandDebit;
    }

    @GetMapping("/supplier/findbyname/{name}")
    @ResponseBody
    public String findSuppliersByName(@PathVariable(name = "name") String supplierName){

        Iterable<Supplier> suppliers = supplierRepository.findSuppliersByName(supplierName);

        int count = 0;
        double grandDebit = 0;

        for(Supplier supplier : suppliers){

            grandDebit += supplier.getTotalCredit();
            count++;

        }
        return "Suppliers: " + count + " Total credit: " + grandDebit;
    }

    @GetMapping("/supplier/findbymincredit/{credit}")
    @ResponseBody
    public String findbySupplierTotalCreditMin(@PathVariable(name = "credit") double credit){

        Iterable<Supplier> suppliers = specialRepository.findSupplierTotalCreditMin(credit);

        int count = 0;
        double grandDebit = 0;

        for(Supplier supplier : suppliers){
            System.out.println(supplier.getSupplierName());
            grandDebit += supplier.getTotalCredit();
            count++;

        }
        return "Suppliers: " + count + " Total credit: " + grandDebit;
    }

}
