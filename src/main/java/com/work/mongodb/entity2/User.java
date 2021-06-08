package com.work.mongodb.entity2;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class User {

    private String _id;
    private String userId;
    private String userAccount;         //userName
    private String password;
    private String power;               //role
    private String[] projectId;

}

