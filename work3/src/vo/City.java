package vo;

public class City {
    private String cityCode;
    private String cityName;
    private String provinceCode;

    public City(String cityCode, String cityName, String provinceCode) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.provinceCode = provinceCode;
    }

    public City() {
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
