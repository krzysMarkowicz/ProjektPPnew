package com.example.projektpp.Repos;

import com.example.projektpp.models.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class Process_repo {
    @Autowired
    Medicine_repo medicineRepo;

    @Autowired
    Category_repo categoryRepo;
    @Autowired
    Order_repo orderRepo;
    @Autowired
    Role_repo roleRepo;
    @Autowired
    Supplier_repo supplierRepo;
    @Autowired
    User_repo userRepo;
    @Autowired
    Warehouse_repo warehouseRepo;

    @Autowired
    OrderStatus_repo orderStatusRepo;

    @Bean
    InitializingBean init(PasswordEncoder passwordEncoder) {



        OrderStatus orderStatus1 = new OrderStatus();
        OrderStatus orderStatus2 = new OrderStatus();
        OrderStatus orderStatus3 = new OrderStatus();

        orderStatus1.setStatus("Potwierdzone");
        orderStatus2.setStatus("Przyjęte do realizacji");
        orderStatus3.setStatus("Wysłane");

        orderStatusRepo.save(orderStatus1);
        orderStatusRepo.save(orderStatus2);
        orderStatusRepo.save(orderStatus3);


        Role role = new Role(Role.Types.ROLE_ADMIN);
        roleRepo.save(role);
        Role role1 = new Role(Role.Types.ROLE_USER);
        roleRepo.save(role1);
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("admin@admin.com");
        user.setActive(true);
        user.setRoles(new HashSet<>(Arrays.asList(role)));
        userRepo.save(user);
        User user1 = new User();
        user1.setUsername("krzys");
        user1.setPassword(passwordEncoder.encode("markowicz"));
        user1.setEmail("asd@asd.com");
        user1.setActive(true);
        user1.setRoles(new HashSet<>(Arrays.asList(role1)));
        userRepo.save(user1);

        Category category1 = new Category();
        category1.setName("Przeciwbólowe");
        Category category2 = new Category();
        category2.setName("Antydepresyjne");
        categoryRepo.save(category1);
        categoryRepo.save(category2);

        Supplier supplier1 = new Supplier();
        supplier1.setName("SUPPLIER1");
        supplierRepo.save(supplier1);
        Supplier supplier2 = new Supplier();
        supplier2.setName("SUPPLIER2");
        supplierRepo.save(supplier2);

        Medicine medicine1 = new Medicine();
        medicine1.setName("APAP");
        medicine1.setCategory(category1);
        medicine1.setPrice(10.99f);
        medicine1.setManufacturer("Firma 1");
        medicine1.setOnPrescription(false);
        medicine1.setSupplier(supplier1);
        medicineRepo.save(medicine1);
        Medicine medicine2 = new Medicine();
        medicine2.setName("CITABAX");
        medicine2.setCategory(category2);
        medicine2.setPrice(13.99f);
        medicine2.setManufacturer("Firma 2");
        medicine2.setOnPrescription(true);
        medicine2.setSupplier(supplier2);
        medicineRepo.save(medicine2);

        Order order1 = new Order();
        order1.setUserName("Krzysztof");
        order1.setUserSurname("Markowicz");
        order1.setStreet("Hollywood");
        order1.setBuildingNo(5);
        order1.setCity("Los Angles");
        order1.setMedicineId(0l);
        order1.setSummaryPrice(medicine1.getPrice() + medicine2.getPrice());
        order1.setOrderStatus(orderStatus1);
        orderRepo.save(order1);

        Order order2 = new Order();
        order2.setUserName("IMIEE");
        order2.setUserSurname("BDASIKB");
        order2.setStreet("NIOCDNU");
        order2.setBuildingNo(3);
        order2.setCity("MEDOCMSAW");
        order2.setMedicineId(1l);
        order2.setSummaryPrice(medicine1.getPrice());
        order2.setOrderStatus(orderStatus2);
        orderRepo.save(order2);


        Warehouse warehouse = new Warehouse();
        warehouse.setMedicine(medicine1);
        warehouse.setQuantity(20);
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setMedicine(medicine2);
        warehouse1.setQuantity(100);
        warehouseRepo.save(warehouse);
        warehouseRepo.save(warehouse1);


        return () -> {

        };
    }


}
