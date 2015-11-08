package org.abqjug;


import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.fest.assertions.Assertions.assertThat;

public class HashingTest {


    @Test
    public void testMD5Hashing() {
        HashFunction hf = Hashing.md5();
        Employee employee = new Employee("Bob", "Barker");
        HashCode hc = hf.newHasher()
                .putLong(100L)
                .putString("Host of Price Is Right", Charsets.UTF_8)
                .putObject(employee, EmployeeFunnel.INSTANCE)
                .hash();
        System.out.println(hc.toString());
    }

    @Test
    public void testSHA512Hashing() {
        HashFunction hf = Hashing.sha512();
        Employee employee = new Employee("Bob", "Barker");
        HashCode hc = hf.newHasher()
                .putLong(100L)
                .putString("Host of Price Is Right", Charsets.UTF_8)
                .putObject(employee, EmployeeFunnel.INSTANCE)
                .hash();
        System.out.println(hc.toString());
    }

    @Test
    public void testBloomFilter() throws URISyntaxException, IOException {
        Stream<String> lines = Files.lines(Paths.get
                (this.getClass().getResource("/lotsofnames.txt").toURI()));

        //Size is important! If you are off, this will cause overflow, then you will get bad results
        BloomFilter<Employee> employees =
                BloomFilter.create(EmployeeFunnel.INSTANCE, 2);

        lines.map(x -> Arrays.asList(x.split(","))).forEach(strings -> {
            Employee employee;
            if (strings.size() == 1) employee = new Employee(strings.get(0).trim());
            else employee = new Employee(strings.get(1).trim(), strings.get(0).trim());
            employees.put(employee);
        });

        assertThat(employees.mightContain(new Employee("Jane", "Ace"))).isTrue();          //maybe true
        assertThat(employees.mightContain(new Employee("Kim", "Kardashian"))).isFalse();   //definitely false
        assertThat(employees.mightContain(new Employee("Paul", "Staley"))).isFalse();      //definitely false
        assertThat(employees.mightContain(new Employee("Eleanor", "Roosevelt"))).isTrue(); //maybe true
        assertThat(employees.mightContain(new Employee("Bono"))).isTrue();                 //maybe true
    }
}
