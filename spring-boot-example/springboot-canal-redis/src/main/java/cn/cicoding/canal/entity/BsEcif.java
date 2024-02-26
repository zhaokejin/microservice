package cn.cicoding.canal.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author z
 * @Description
 * @date 2023/2/25
 */
@Data
@Table(name = "bs_ecif")
public class BsEcif implements Serializable {

    @Column(name="seq_no")
    private Long seqNo;

    @Column(name="customer")
    private String customer;

    @Column(name="id_type")
    private String idType;

    @Column(name="id_no")
    private String idNo;

    @Column(name="new_field")
    private String newField;

    @Override
    public String toString() {
        return "BsEcif{" +
                "seqNo=" + seqNo +
                ", customer='" + customer + '\'' +
                ", idType='" + idType + '\'' +
                ", idNo='" + idNo + '\'' +
                ", newField='" + newField + '\'' +
                '}';
    }
}
