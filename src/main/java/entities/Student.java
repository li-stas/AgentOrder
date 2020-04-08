package entities;

public class Student {
    private Integer id;
    private String name;
    private String email;
    private String group;
    private float scholarship;

    public float getScholarship() {
        return scholarship;
    }

    public void setScholarship(float scholarship) {
        this.scholarship = scholarship;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Student(Integer id, String name, String email, String group) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.group = group;
    }

    public Student(Integer id, String name, float scholarship) {
        this.id = id;
        this.name = name;
        this.scholarship = scholarship;
    }

    public Student(Integer id, String name, String email, String group, float scholarship) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.group = group;
        this.scholarship = scholarship;
    }
}
