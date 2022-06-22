package PostTracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PostalItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "postal_item_type_id")
    private PostalItemType type;
    @ManyToOne
    @JoinColumn(name = "post_office_id")
    private PostOffice recipientPostOffice;
    @Column(name = "address", nullable = false)
    private String recipientAddress;
    @Column(name = "name", nullable = false)
    private String recipientName;
}
