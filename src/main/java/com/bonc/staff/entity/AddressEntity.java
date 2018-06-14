package com.bonc.staff.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 数据库中的地址实体类
 * @author xukj
 */
@Entity
@Data
@Table(name = "staff_address")
@NoArgsConstructor
public class AddressEntity implements Serializable {

    public AddressEntity(String name, String code, String pacode) {
        this.name = name;
        this.code = code;
        this.pacode = pacode;
    }

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;
    private String name;
    private String code;
    private String pacode;

}
