package PostTracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp date;
    @ManyToOne
    @JoinColumn(name = "postal_item_status_id")
    private PostalItemStatus status;
    @ManyToOne
    @JoinColumn(name = "postal_item_id")
    private PostalItem postalItem;
}
