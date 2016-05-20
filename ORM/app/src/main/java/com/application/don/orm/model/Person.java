package com.application.don.orm.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Don on 5/20/16.
 */
public class Person extends SugarRecord {
    private String name;
    private int age;
    private String job;

    public Person() {
    }

    public Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
