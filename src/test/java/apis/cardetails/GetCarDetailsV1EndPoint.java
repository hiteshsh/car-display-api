package apis.cardetails;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CarDisplayProperties;

import java.util.Arrays;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetCarDetailsV1EndPoint {

    private static final Logger logger= LogManager.getLogger(GetCarDetailsV1EndPoint.class);


    public Response getAllManufacturers(Map params){

        logger.info("\nAll Manufacturer URL --- GET {}", CarDisplayProperties.getAllManufacturerUrlV1);
          Response response=  given().contentType(ContentType.JSON)
                   .params(params)
                    .when()
                    .get(CarDisplayProperties.getAllManufacturerUrlV1);
        logger.info("\nAll Manufacturer Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public Response getCarType(Map params){

        logger.info("\nCar Type URL --- GET {}", CarDisplayProperties.getCarTypeUrlV1);
        Response response=  given().contentType(ContentType.JSON)
                .params(params)
                .when()
                .get(CarDisplayProperties.getCarTypeUrlV1);
        logger.info("\nCar Type Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public Response getCarBuiltDate(Map params){

        logger.info("\nBuilt Date URL --- GET {}", CarDisplayProperties.getBuiltDateUrlV1);
        Response response=  given().contentType(ContentType.JSON)
                .params(params)

                .when()
                .get(CarDisplayProperties.getBuiltDateUrlV1);
        logger.info("\nBuilt Date Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public static void assertErroMessageForMissingParam(String param,Response response){
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
        ErrorResponse errorResponse=response.as(ErrorResponse.class);
        assertThat(errorResponse.getStatus()).isEqualTo("400");
        assertThat(errorResponse.getError()).isEqualTo("Bad Request");
        assertThat(errorResponse.getMessage()).isEqualTo("Required String parameter '"+param+"' is not present");

    }

    public static void assertSuccessfulGetManufacturerResponse(Response response,String[] expectedCarManufacturer,int totalmanufacturer){
        assertResponseWkda(response,expectedCarManufacturer,totalmanufacturer);
    }

    public static void assertSuccessfulGetCarTypeResponse(Response response,String[] expectedCarTypes,int totalCarType){
        assertResponseWkda(response,expectedCarTypes,totalCarType);
    }

    public static void assertSuccessfulGetBuiltDatesResponse(Response response,String[] expectedBuiltDates,int totalDates){
        assertResponseWkda(response,expectedBuiltDates,totalDates);
    }

    private static void assertResponseWkda(Response response, String[] expectedResult,int totalRecords){
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        CarDetailResponse carDetailResponse=response.as(CarDetailResponse.class);
        assertPageDetails(carDetailResponse);
        assertThat(carDetailResponse.getWkda().size()).isEqualTo(totalRecords);
        Arrays.stream(expectedResult).forEach(e-> assertThat(carDetailResponse.getWkda().containsValue(e)).isTrue());
    }

    private static void  assertPageDetails(CarDetailResponse response){
        assertThat(response.getPage()).isNotNull();
        assertThat(response.getPageSize()).isNotNull().isPositive();
        assertThat(response.getTotalPageCount()).isNotNull();
    }
}
