package modelos;

public class Conversion {
    private String monedaInicio;
    private String monedaFin;
    private double conversion;
    private double total;

    public Conversion(String monedaInicio, String monedaFin, Double conversion, Double total){
        this.monedaInicio = monedaInicio;
        this.monedaFin = monedaFin;
        this.conversion = conversion;
        this.total = total;
    }

    public Conversion(ConversionApi conversionApi){
        this.monedaInicio = conversionApi.base_code();
        this.monedaFin = conversionApi.target_code();
        this.conversion = conversionApi.conversion_rate();
        this.total = conversionApi.conversion_result();
    }

    public String getMonedaInicio() {
        return monedaInicio;
    }

    public String getMonedaFin() {
        return monedaFin;
    }

    public double getConversion() {
        return conversion;
    }

    public double getTotal() {
        return total;
    }

    public void setMonedaInicio(String monedaInicio) {
        this.monedaInicio = monedaInicio;
    }

    public void setMonedaFin(String monedaFin) {
        this.monedaFin = monedaFin;
    }

    public void setConversion(double conversion) {
        this.conversion = conversion;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
