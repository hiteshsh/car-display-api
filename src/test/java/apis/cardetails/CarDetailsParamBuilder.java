package apis.cardetails;

import java.util.HashMap;
import java.util.Map;

public class CarDetailsParamBuilder {

    private Map<String,String> carDetailsParam;

    public CarDetailsParamBuilder(){
        this.carDetailsParam= new HashMap<>();
    }

    public CarDetailsParamBuilder withWaKey(String waKey){
        carDetailsParam.put("wa_key",waKey);
        return this;
    }
    public CarDetailsParamBuilder withLocale(String locale){
        carDetailsParam.put("locale",locale);
        return this;
    }
    public CarDetailsParamBuilder withManufacturer(String manufacturerCode){
        carDetailsParam.put("manufacturer",manufacturerCode);
        return this;
    }
    public CarDetailsParamBuilder withCarType(String carType){
        carDetailsParam.put("main-type",carType);
        return this;
    }

    public Map<String,String> build(){
        return carDetailsParam;
    }

}
