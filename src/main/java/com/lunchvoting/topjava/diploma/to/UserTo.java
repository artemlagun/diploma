package com.lunchvoting.topjava.diploma.to;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 128)
    private String name;

    @Email
    @NotBlank
    @Size(max = 128)
    private String email;

    @NotBlank
    @Size(min = 5, max = 128)
    private String password;

    @ConstructorProperties({"id", "name", "email", "password"})
    public UserTo(Integer id, String name,  String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTo userTo = (UserTo) o;
        return name.equals(userTo.name) && email.equals(userTo.email) && password.equals(userTo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password);
    }
}
