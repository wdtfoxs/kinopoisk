package ru.springproject.dz.valid;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserValid {

    @Size(min = 4, max = 20, message = "Имя пользователя должно состоять из 4 и более символов")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Имя пользователя должно состоять из букв и цифр латинского языка")
    private String username;

    @Pattern(regexp = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$", message = "Неверный формат email")
    private String email;

    @Size(min = 6, max = 20, message = "Пароль должен состоять из минимум 6 символов")
    @Pattern(regexp = "^[A-z0-9]{6,18}$", message = "Пароль может состоять только из цифр и букв латинского языка")
    private String password;

    @Size(min = 6, max = 20, message = "Пароль должен состоять из минимум 6 символов")
    @Pattern(regexp = "^[A-z0-9]{6,18}$", message = "Пароль может состоять только из цифр и букв латинского языка")
    private String repassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
