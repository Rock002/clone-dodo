package clonedodo.Dodo.models.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.List;

@Entity
@Data
@Table(name = "user1")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "roles")
    private String roles;
    @Column(name = "foodlist")
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Food> listOfFood;

    public List<Food> getListOfFood() {
        return listOfFood;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public void setName(String username) {
        this.name = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setListOfFood(List<Food> listOfFood) {
        this.listOfFood = listOfFood;
    }
}
