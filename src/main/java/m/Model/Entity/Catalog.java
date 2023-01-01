package m.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "catalog")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CatalogId")
    private int catalogId;
    @Column(name = "CatalogName")
    private String catalogName;
    @Column(name = "CatalogStatus")
    private boolean catalogStatus;
    @OneToMany(mappedBy = "catalog",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> productList=new ArrayList<>();

}