package com.bonc.staff.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xukj
 */
@Entity
@Table(name = "staff_person_address")
@NoArgsConstructor
public class PerAddEntity {

    public PerAddEntity(String personid, String addressid) {
        this.personid = personid;
        this.addressid = addressid;
    }

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;
    private String personid;
    private String addressid;
}
