package hello.itemservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(ItemHistoryPK.class)
public class ItemHistory {
    @Id
    long pid;
    @Id
    Timestamp ts;
    Integer stock;
}
