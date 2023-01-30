package hello.itemservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberItemPK implements Serializable {
    private Long uid;
    private Long pid;
}
