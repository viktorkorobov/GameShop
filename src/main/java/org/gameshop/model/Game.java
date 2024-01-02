package org.gameshop.model;

import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private Long id;
    private String name;
    private Date releaseDate;
    private double rating;
    private double cost;
    private String description;
}
