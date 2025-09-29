package clonedodo.DodoAlert.models.entity;


import java.util.ArrayList;
import java.util.List;


public class User {

    private Long id;
    private String username;
    private String password;
    private String roles;
    private List<Food> listOfFood;

    public List<Food> getListOfFood() {
        return listOfFood;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public void setName(String username) {
        this.username = username;
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
        this.listOfFood = listOfFood != null ? listOfFood : new ArrayList<>();
    }
}
