package m.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartDTO {
    private String imageProduct;
    private String productName;
    private String toppingName;
    private String sizeName;
    private float priceDt;
}
