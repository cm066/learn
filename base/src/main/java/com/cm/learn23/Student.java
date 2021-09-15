package com.cm.learn23;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Comparable<Student> {
    public Integer id;
    public Integer age;

    @Override
    public int compareTo(Student o) {
        return age.compareTo(o.age);
    }
}
