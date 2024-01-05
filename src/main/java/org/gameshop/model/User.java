package org.gameshop.model;

import lombok.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String nickname;
    private Date birthday;
    private String password;
    private double amount;
}
