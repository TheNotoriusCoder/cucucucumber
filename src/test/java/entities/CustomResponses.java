package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;
import java.util.PrimitiveIterator;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponses {
    private int category_id;
    private String created;
    private List<CustomResponses> responses;
    private String email;
    private String seller_name;
    private String responseBody;

    @JsonPropertyOrder("company_name")
    private String companyName;

    private CustomResponses company_name;
    private int total_page;
    private String phone_number;
    private String address;

    private CustomResponses business_area;
    private int business_area_id;
    private String ruTitle;
    private String enTitle;
    private CustomResponses category;
    private Services service_type;
    private int seller_id;

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }
}
