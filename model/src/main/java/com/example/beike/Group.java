package com.example.beike;

import java.util.Date;
import java.util.Timer;

public class Group {
    private int group_id;
    private String group_name;
    private String creator_uc_id;
    private int index_sort;
    private Date ctime;
    private Date mtime;

    public Group(String group_name, String creator_uc_id, Date ctime, Date mtime) {
        this.group_name = group_name;
        this.creator_uc_id = creator_uc_id;
        this.ctime = ctime;
        this.mtime = mtime;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getCreator_uc_id() {
        return creator_uc_id;
    }

    public void setCreator_uc_id(String creator_uc_id) {
        this.creator_uc_id = creator_uc_id;
    }

    public int getIndex_sort() {
        return index_sort;
    }

    public void setIndex_sort(int index_sort) {
        this.index_sort = index_sort;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

}
