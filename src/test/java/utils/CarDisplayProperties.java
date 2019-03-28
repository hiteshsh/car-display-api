package utils;

public class CarDisplayProperties {
    private static final PropertiesReader reader= new PropertiesReader();

    public static final String baseUrl= reader.getCarDisplayBaseUrl();
    public static final String getAllManufacturerUrlV1= String.format("%s/v1/car-types/manufacturer",baseUrl);
    public static final String getCarTypeUrlV1= String.format("%s/v1/car-types/main-types",baseUrl);
    public static final String getBuiltDateUrlV1= String.format("%s/v1/car-types/built-dates",baseUrl);

}
