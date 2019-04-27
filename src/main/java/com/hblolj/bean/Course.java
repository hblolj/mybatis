package com.hblolj.bean;

public class Course {

    private Integer courseId;

    private String name;

    public Course(Integer courseId, String name) {
        this.courseId = courseId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                '}';
    }

    public Course() {
        super();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}