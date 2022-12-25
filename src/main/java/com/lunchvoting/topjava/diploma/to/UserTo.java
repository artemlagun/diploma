package com.lunchvoting.topjava.diploma.to;

import com.fasterxml.jackson.annotation.JsonView;
import com.lunchvoting.topjava.diploma.View;
import com.lunchvoting.topjava.diploma.model.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTo extends BaseTo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 128)
    @JsonView(View.JsonREST.class)
    private String name;

    @Email
    @NotBlank
    @Size(max = 128)
    @JsonView(View.JsonREST.class)
    private String email;

    @NotBlank
    @Size(min = 5, max = 128)
    private String password;

    @JsonView(View.JsonREST.class)
    private boolean enabled = true;

    @JsonView(View.JsonREST.class)
    private LocalDate registered = LocalDate.now(ZoneId.systemDefault());

    @JsonView(View.JsonREST.class)
    private Set<Role> roles;

    @ConstructorProperties({"id", "name", "email", "password"})
    public UserTo(Integer id, String name, String email, String password) {
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
        return enabled == userTo.enabled && name.equals(userTo.name)
                && email.equals(userTo.email)
                && password.equals(userTo.password)
                && Objects.equals(registered, userTo.registered)
                && Objects.equals(roles, userTo.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, enabled, registered, roles);
    }
}
