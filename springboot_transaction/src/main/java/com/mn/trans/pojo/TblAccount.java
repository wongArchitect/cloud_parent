package com.mn.trans.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * tbl_account
 * @author 
 */
@Data
public class TblAccount implements Serializable {
    private Integer id;

    private String name;

    private Double balance;

    private static final long serialVersionUID = 1L;
}