package com.mn.web.emuncl;

import lombok.Data;


public enum PeopleEnum {

    PEOPLEENUM_MAN(0, "man"),
    PEOPLEENUM_WOMAN(0, "woman")


    ;








    private String peopleTypeName;

    private Integer code;

    PeopleEnum(Integer code, String peopleTypeName) {
        this.peopleTypeName = peopleTypeName;
        this.code = code;
    }

    public String getPeopleTypeName() {
        return peopleTypeName;
    }

    public void setPeopleTypeName(String peopleTypeName) {
        this.peopleTypeName = peopleTypeName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
