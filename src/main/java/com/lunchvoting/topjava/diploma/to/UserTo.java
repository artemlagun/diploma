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


@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserTo {

    private Integer id;

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

    private boolean enabled = true;

    @NotNull
    private LocalDate registered = LocalDate.now(ZoneId.systemDefault());

    @ConstructorProperties({"id", "name", "email", "password", "enabled", "registered"})
    public UserTo(Integer id, String name,  String email, String password, boolean enabled, LocalDate registered) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
    }
}
