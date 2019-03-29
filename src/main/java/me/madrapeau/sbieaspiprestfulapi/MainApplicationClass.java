package me.madrapeau.sbieaspiprestfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MainApplicationClass implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MainApplicationClass.class);

    public static void main(String args[]) {
        SpringApplication.run(MainApplicationClass.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE SBI_E.PUBLIC.user IF EXISTS;");
        jdbcTemplate.execute("CREATE TABLE SBI_E.PUBLIC.user(" +
                "id INTEGER, user_name VARCHAR(255), first_name VARCHAR(255), last_name VARCHAR(255))");


        jdbcTemplate.execute("DROP TABLE SBI_E.PUBLIC.right IF EXISTS;");
        jdbcTemplate.execute("CREATE TABLE SBI_E.PUBLIC.right(" +
                "id INTEGER, user_id INTEGER, account_id INTEGER, permission_code VARCHAR(255))");

        // Create list of access record
        List<Object[]> usersList = new ArrayList<Object[]>();
        Object[] a = {1,"DRAM01", "Martin", "Drapeau"};
        usersList.add(a);
        Object[] b = {2,"BRIJ02", "Jacques", "Brisson"};
        usersList.add(b);
        // Use stream to print out each tuple of the list
        usersList.forEach(user -> log.info(String.format("Inserting user %s %s", user[2], user[3])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO SBI_E.PUBLIC.user (id, user_name, first_name, last_name) VALUES (?,?,?,?)", usersList);


        // Create list of access record
        List<Object[]> rightsList = new ArrayList<Object[]>();
        Object[] r1 = {1,1,12205, "ReadAccountsDetail"};
        rightsList.add(r1);
        Object[] r2 = {2,1,12205, "ReadBalances"};
        rightsList.add(r2);
        Object[] r3 = {3,1,12205, "ReadTransactionsDetail"};
        rightsList.add(r3);
        Object[] r4 = {4,1,24000, "ReadAccountsDetail"};
        rightsList.add(r4);
        Object[] r5 = {5,1,24000, "ReadBalances"};
        rightsList.add(r5);
        Object[] r6 = {6,1,24000, "ReadTransactionsDetail"};
        rightsList.add(r6);
        Object[] r7 = {7,1,34999, "ReadAccountsDetail"};
        rightsList.add(r7);
        Object[] r8 = {8,1,34999, "ReadBalances"};
        rightsList.add(r8);
        Object[] r9 = {9,1,34999, "ReadTransactionsDetail"};
        rightsList.add(r9);
        Object[] r10 = {10,2,12205, "ReadAccountsDetail"};
        rightsList.add(r10);
        Object[] r11 = {11,2,12205, "ReadBalances"};
        rightsList.add(r11);
        Object[] r12 = {12,2,15000, "ReadAccountsDetail"};
        rightsList.add(r12);
        Object[] r13 = {13,2,15000, "ReadBalances"};
        rightsList.add(r13);
        Object[] r14 = {14,2,25000, "ReadAccountsDetail"};
        rightsList.add(r14);
        Object[] r15 = {15,2,25000, "ReadBalances"};
        rightsList.add(r15);
        // Use stream to print out each tuple of the list
        rightsList.forEach(access -> log.info(String.format("Inserting right record for user with id %s to account %s with the permission %s", access[1], access[2], access[3])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO SBI_E.PUBLIC.right (id, user_id, account_id, permission_code) VALUES (?,?,?,?)", rightsList);

    }
}