package tests;

import apis.cardetails.CarDetailResponse;
import apis.cardetails.GetCarDetailsV1EndPoint;
import io.restassured.response.Response;
import apis.cardetails.CarDetailsParamBuilder;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static apis.cardetails.GetCarDetailsV1EndPoint.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GetCarDetailsTest extends BaseTest{

    @Test
    public void userShouldBeAbleToSeeAllCarManufacturers(){

        String[] expectedCarManufacturer= {"Bentley","BMW","Buick","Brilliance","Cadillac","Caterham","Chevrolet","Chrysler","Citroen","Corvette",
                "Dacia","Daewoo","Daihatsu","Nissan","Dodge","Ferrari","Fiat","Ford","Hummer","Honda","Hyundai","Infiniti","Isuzu","Iveco","Jaguar",
                "Jeep","Kia","Lada","Lamborghini","Lancia","Lexus","Lotus","MAN","Maserati","Maybach","Mazda","Smart","Mercedes-Benz","MINI","Mitsubishi",
                "Opel","Peugeot","Pontiac","Porsche","Proton","Renault","Rolls Royce","Land Rover","Saab","Seat","Skoda","Ssangyong","Subaru","Suzuki",
                "Tata","Tesla","Toyota","Trabant","Piaggio (Vespa)","Volkswagen","Volvo","Wartburg","Westfield","Zastava","Abarth","Alfa Romeo","Alpina",
                "Alpine","Aston Martin","Audi","Barkas","MG Rover"};

        CarDetailsParamBuilder paramsBuilder= new CarDetailsParamBuilder();
        Map<String,String> params=paramsBuilder.withWaKey(wa_key).withLocale("en").build();

        GetCarDetailsV1EndPoint carDetails= new GetCarDetailsV1EndPoint();
        Response response=carDetails.getAllManufacturers(params);
        assertSuccessfulGetManufacturerResponse(response,expectedCarManufacturer,expectedCarManufacturer.length);


    }

    @Test
    public void userShouldBeAbleToSeeAllCarTypeForACarManufacturer(){
        String carManufacturer="Bentley";
        String[] expectedCarType= {"Arnage","Azure","Continental Flying Spur","Continental GT","Continental GTC","Continental SC"};

        CarDetailsParamBuilder paramsBuilder= new CarDetailsParamBuilder();
        Map<String,String> params=paramsBuilder.withWaKey(wa_key).withLocale("en").build();

        GetCarDetailsV1EndPoint carDetails= new GetCarDetailsV1EndPoint();
        Response response=carDetails.getAllManufacturers(params);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        CarDetailResponse carDetailResponse=response.as(CarDetailResponse.class);

        String manufacturerCode=carDetailResponse.getWkda().entrySet().stream()
                .filter(e->e.getValue().equalsIgnoreCase(carManufacturer))
                .map(Map.Entry::getKey).findFirst().orElse(null);

        Map<String,String> paramsCarType=paramsBuilder.withManufacturer(manufacturerCode).build();
        Response carListResponse=carDetails.getCarType(paramsCarType);
        assertSuccessfulGetCarTypeResponse(carListResponse,expectedCarType,expectedCarType.length);

    }

    @Test
    public void userShouldBeAbleToSeeBuiltDateForACarTypeAndCarManufacturer(){
        String selectedCarManufacturer="Bentley";
        String selectedCarType="Arnage";
        String[] expectedBuiltDate={"2001","2002","2003","2004","2005","2006","2007","2008","2009","2010"};

        CarDetailsParamBuilder paramsBuilder= new CarDetailsParamBuilder();
        Map<String,String> params=paramsBuilder.withWaKey(wa_key).withLocale("en").build();

        GetCarDetailsV1EndPoint carDetails= new GetCarDetailsV1EndPoint();
        Response response=carDetails.getAllManufacturers(params);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        CarDetailResponse carDetailResponse=response.as(CarDetailResponse.class);

        String manufacturerCode=carDetailResponse.getWkda().entrySet().stream()
                .filter(e->e.getValue().equalsIgnoreCase(selectedCarManufacturer))
                .map(Map.Entry::getKey).findFirst().orElse(null);

        Map<String,String> paramsCarType=paramsBuilder.withManufacturer(manufacturerCode).build();
        Response carListResponse=carDetails.getCarType(paramsCarType);
        assertThat(carListResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        CarDetailResponse carList=carListResponse.as(CarDetailResponse.class);

        String carType=carList.getWkda().entrySet().stream()
                .filter(e->e.getValue().equalsIgnoreCase(selectedCarType))
                .map(Map.Entry::getKey).findFirst().orElse(null);

        Map<String,String> paramsBuildDate=paramsBuilder.withCarType(carType).build();

        Response dateBuiltResponse=carDetails.getCarBuiltDate(paramsBuildDate);
        assertThat(dateBuiltResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        assertSuccessfulGetBuiltDatesResponse(dateBuiltResponse,expectedBuiltDate,expectedBuiltDate.length);

    }
}
