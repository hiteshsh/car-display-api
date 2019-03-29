package apis.cardetails;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter @Setter
public class CarDetailResponse {
    private Integer page;
    private Integer pageSize;
    private Integer totalPageCount;
    private Map<String,String> wkda;

}
