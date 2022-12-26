package com.example.github.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
@Data
@NoArgsConstructor
@Table(name = "github_repository")
public class Repo {
    private long id;
    private String node_id;
    private String name;
    private String full_name;
    private boolean is_private;
    private String description;
    private boolean is_fork;
    private Date created_at;
    private  Date updated_at;
    private Date pushed_at;
    private String home_page;
    private long size;
    private  int stargazers_count;
    private int watchers_count;
    private  int forks_count;
    private int open_issues_count;
    private String license;
    private  String default_branch;
    private String language;
   private Permissions permissions;
    private boolean is_archived;
    private boolean is_disabled;


}
