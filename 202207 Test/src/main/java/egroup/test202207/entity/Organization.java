package egroup.test202207.entity;

import java.util.Objects;

public class Organization {
    private int id;
    private String createDate;
    private String name;
    private String description;
    private String phone;
    private String email;
    private String address;

    public Organization() {
    }

    public Organization(int id, String createDate, String name, String description, String phone, String email, String address) {
        this.id = id;
        this.createDate = createDate;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Organization id(int id) {
        setId(id);
        return this;
    }

    public Organization createDate(String createDate) {
        setCreateDate(createDate);
        return this;
    }

    public Organization name(String name) {
        setName(name);
        return this;
    }

    public Organization description(String description) {
        setDescription(description);
        return this;
    }

    public Organization phone(String phone) {
        setPhone(phone);
        return this;
    }

    public Organization email(String email) {
        setEmail(email);
        return this;
    }

    public Organization address(String address) {
        setAddress(address);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Organization)) {
            return false;
        }
        Organization organization = (Organization) o;
        return id == organization.id && Objects.equals(createDate, organization.createDate) && Objects.equals(name, organization.name) && Objects.equals(description, organization.description) && Objects.equals(phone, organization.phone) && Objects.equals(email, organization.email) && Objects.equals(address, organization.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, name, description, phone, email, address);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}