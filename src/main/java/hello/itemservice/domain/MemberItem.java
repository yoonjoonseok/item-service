package hello.itemservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(MemberItemPK.class)
public class MemberItem implements Serializable {
    @Id
    private Long uid;
    @Id
    private Long pid;
    private boolean alarm;
}
