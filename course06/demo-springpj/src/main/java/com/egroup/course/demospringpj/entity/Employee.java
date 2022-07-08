package main.java.com.egroup.course.demospringpj.entity;

public class Employee {
    private int id;
    private String name;
    private int phone;
    private String chara; 

    public Employee(int id, String name, int phone, String chara) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.chara = chara;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhone() {
        return this.phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getChara() {
        return this.chara;
    }

    public void setChara(String chara) {
        this.chara = chara;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name) && phone == employee.phone && Objects.equals(chara, employee.chara);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, chara);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", chara='" + getChara() + "'" +
            "}";
    }
}
