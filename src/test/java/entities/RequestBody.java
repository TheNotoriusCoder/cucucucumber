package entities;

import lombok.Data;

@Data
public class RequestBody {
    private String email;
    private String password;
    private String category_title;
    private String category_description;
    private boolean flag;
    private String company_name;
    private String seller_name;
    private String phone_number;
    private String address;
    private String product_title;
    private int product_price;
    private int service_type_id;
    private int category_id;
    private String product_description;
    private String date_of_Payment;
    private int remind_before_day;
    private String do_remind_every_month;



}
