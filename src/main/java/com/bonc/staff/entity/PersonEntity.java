package com.bonc.staff.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xukj
 */
@Entity
@Data
@Table(name = "staff_person")
@NoArgsConstructor
public class PersonEntity implements Serializable{

    public PersonEntity(String name, String phone, String address, String amount) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.amount = amount;
    }

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;
    private String name;

    @Column(unique = true)
    private String phone;
    private String address;
    private String amount;
    @Column(name = "long_lat")
    private String longLat;
    @Column(name = "create_date")
    private Date createDate = new Date();
}
