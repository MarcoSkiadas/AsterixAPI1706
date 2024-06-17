package org.example.asterixapi1706.Model;

import lombok.Data;
import lombok.Setter;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@With
@Document("Character")
public record Character(
        String id,
        String name,
        int age,
        String profession
) {}


