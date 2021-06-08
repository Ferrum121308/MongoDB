package com.work.mongodb.entity2;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Document(collection = "KeepProject")
public class KeepProject {

    private String _id;
    private String projectId;
    private String projectName;
    private String[] recordId;


}