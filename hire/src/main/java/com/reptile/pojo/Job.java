package com.reptile.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Job {
    private int id;
    private String jobName;
    private String companyName;
    private String money;
    private String location;
}
