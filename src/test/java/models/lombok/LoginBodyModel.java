package models.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBodyModel {
    //String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

    String email, password;
}
