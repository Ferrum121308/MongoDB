package com.work.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "KeepRecord")
public class KeepRecord {

    private String _id;
    private String recordId;
    private String projectId;
    private String recordName;
    private String money;
    private String remarks;
    private String recordDate;
}
