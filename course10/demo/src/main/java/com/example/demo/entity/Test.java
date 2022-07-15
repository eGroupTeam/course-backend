package com.example.demo.entity;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class Test {
    
    private int id;
    private String str;
    private String str2;

    public Test() {
    }

    public Test(int id, String str, String str2) {
        this.id = id;
        this.str = str;
        this.str2 = str2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStr() {
        return this.str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getStr2() {
        return this.str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public Test id(int id) {
        setId(id);
        return this;
    }

    public Test str(String str) {
        setStr(str);
        return this;
    }

    public Test str2(String str2) {
        setStr2(str2);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Test)) {
            return false;
        }
        Test test = (Test) o;
        return id == test.id && Objects.equals(str, test.str) && Objects.equals(str2, test.str2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, str, str2);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", str='" + getStr() + "'" +
            ", str2='" + getStr2() + "'" +
            "}";
    }
    
}
