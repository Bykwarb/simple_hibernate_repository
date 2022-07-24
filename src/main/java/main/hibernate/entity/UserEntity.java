package main.hibernate.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String profession;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_data_id", referencedColumnName = "id")
    private LoginDataEntity dataEntity;

    public UserEntity() {
        dataEntity = new LoginDataEntity();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public LoginDataEntity getDataEntity() {
        return dataEntity;
    }

    public void setDataEntity(LoginDataEntity dataEntity) {
        this.dataEntity = dataEntity;
    }

    public void setLoginEmail(String email){
        dataEntity.setEmail(email);
    }
    public void setLoginPassword(String password){
        dataEntity.setPassword(password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", profession='" + profession + '\'' +
                ", dataEntity=" + dataEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(age, user.age) && Objects.equals(profession, user.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, profession);
    }
}
