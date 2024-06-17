package org.example.asterixapi1706.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("Character")
public record Character(
        String id,
        String name,
        int age,
        String profession
) {
}
