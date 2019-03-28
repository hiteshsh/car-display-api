package tests;

import apis.cardetails.CarDetailResponse;
import apis.cardetails.ErrorResponse;
import apis.cardetails.GetCarDetailsV1EndPoint;
import io.restassured.response.Response;
import apis.cardetails.CarDetailsParamBuilder;
import org.apache.http.HttpStatus;
import org.junit.Test;
import java.util.Map;

import static apis.cardetails.GetCarDetailsV1EndPoint.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GetCarDetailsNegativeTest extends BaseTest {

    @Test
    public void userShouldNotGetManufacturerIfNotAuthorised(){
        GetCarDetailsV1EndPoint carDetails= new GetCarDetailsV1EndPoint();
        CarDetailsParamBuilder paramsBuilder= new CarDetailsParamBuilder();
        Map<String,String> params=paramsBuilder.withLocale("en").build();
        Response response=carDetails.getAllManufacturers(params);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_UNAUTHORIZED);

    }

    @Test
    public void userShouldNotGetCarTypeForAManufacturerIfNotAuthorised(){
        GetCarDetailsV1EndPoint carDetails= new GetCarDetailsV1EndPoint();
        CarDetailsParamBuilder paramsBuilder= new CarDetailsParamBuilder();
        Map<String,String> params=paramsBuilder.withLocale("en").withManufacturer("107").build();
        Response response=carDetails.getCarType(params);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void userShouldNotGetBuiltDateForManufacturerAndCarTyperIfNotAuthorised(){
        GetCarDetailsV1EndPoint carDetails= new GetCarDetailsV1EndPoint();
        CarDetailsParamBuilder paramsBuilder= new CarDetailsParamBuilder();
        Map<String,String> params=paramsBuilder.withLocale("en").withManufacturer("107").withCarType("Arnage").build();
        Response response=carDetails.getCarBuiltDate(params);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void userShouldNotGetCarTypeIfManufacturerIsNotSelected(){
        GetCarDetailsV1EndPoint carDetails= new GetCarDetailsV1EndPoint();
        CarDetailsParamBuilder paramsBuilder= new CarDetailsParamBuilder();
        Map<String,String> params=paramsBuilder.withLocale("en").withWaKey(wa_key).build();
        Response response=carDetails.getCarType(params);
        assertErroMessageForMissingParam("manufacturer",response);
    }

    @Test
    public void userShouldNotGetBuiltDateIfManufacturerIsNotSelected(){
        GetCarDetailsV1EndPoint carDetails= new GetCarDetailsV1EndPoint();
        CarDetailsParamBuilder paramsBuilder= new CarDetailsParamBuilder();
        Map<String,String> params=paramsBuilder.withLocale("en")
                                            .withWaKey(wa_key).withCarType("Arnage").build();
        Response response=carDetails.getCarBuiltDate(params);
        assertErroMessageForMissingParam("manufacturer",response);
    }

    @Test
    public void userShouldNotGetBuiltDateIfCarTypeIsNotSelected(){
        GetCarDetailsV1EndPoint carDetails= new GetCarDetailsV1EndPoint();
        CarDetailsParamBuilder paramsBuilder= new CarDetailsParamBuilder();
        Map<String,String> params=paramsBuilder.withLocale("en")
                                    .withWaKey(wa_key).withManufacturer("107").build();
        Response response=carDetails.getCarBuiltDate(params);
        assertErroMessageForMissingParam("main-type",response);
    }

    @Test
    public void userShouldNotGetAnyCarTypesIfManufacturerNotExist(){
        String[] expectedCarType={};
        GetCarDetailsV1EndPoint carDetails= new GetCarDetailsV1EndPoint();
        CarDetailsParamBuilder paramsBuilder= new CarDetailsParamBuilder();
        Map<String,String> params=paramsBuilder.withLocale("en")
                                .withWaKey(wa_key).withManufacturer("NOTEXIST").build();
        Response response=carDetails.getCarType(params);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        assertSuccessfulGetCarTypeResponse(response,expectedCarType,expectedCarType.length);
    }

    @Test
    public void userShouldNotGetAnyBuiltDateIfManufacturerAndCarTypeNotRelated(){

        String[] expectedBuiltDate={};

        GetCarDetailsV1EndPoint carDetails= new GetCarDetailsV1EndPoint();
        CarDetailsParamBuilder paramsBuilder= new CarDetailsParamBuilder();
        Map<String,String> params=paramsBuilder.withLocale("en")
                .withWaKey(wa_key).withManufacturer("107").withCarType("Rover Estate").build();
        Response response=carDetails.getCarBuiltDate(params);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        assertSuccessfulGetBuiltDatesResponse(response,expectedBuiltDate,expectedBuiltDate.length);

    }

}
