package apis.cardetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;


@Getter @Setter
public class CarDetailResponse {
    private Integer page;
    private Integer pageSize;
    private Integer totalPageCount;
    private Map<String,String> wkda;

}
