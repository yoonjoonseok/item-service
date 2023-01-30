package hello.itemservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemHistoryPK implements Serializable {
    private long pid;
    private Timestamp ts;
}
