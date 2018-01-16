package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University  {
    private String name;

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

    private int age;
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    private List<Student> students = new ArrayList<>();
    public University(String name, int age) {
       this.name = name;
       this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student : students){
            if (student.getAverageGrade() == averageGrade) return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        double max = students.get(0).getAverageGrade();
        Student studentWithMaxAverageGrade = students.get(0);
        for (Student student: students)
        {
            if (student.getAverageGrade() > max){
                max = student.getAverageGrade();
                studentWithMaxAverageGrade = student;
            }
        }

        return studentWithMaxAverageGrade;
    }

    public Student getStudentWithMinAverageGrade() {
        double min = students.get(0).getAverageGrade();
        Student studentWithMaxAverageGrade = students.get(0);
        for (Student student: students)
        {
            if (student.getAverageGrade() < min){
                min = student.getAverageGrade();
                studentWithMaxAverageGrade = student;
            }
        }

        return studentWithMaxAverageGrade;
    }

    public void expel (Student student){
        students.remove(student);
    }


}