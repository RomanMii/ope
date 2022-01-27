package com.ti38b.ourpeopleeverywhere.unitConvertor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AreaConvertor implements Convertor{
    public enum AreaUnits{
        SQUARE_CENTIMETER,
        SQUARE_METER,
        SQUARE_KILOMETER,
        SQUARE_INCH,
        SQUARE_FOOT,
        SQUARE_YARD,
        ACRE,
        SQUARE_MILE;

        public static AreaUnits fromString(String str){
            if(str != null){
                str = str.replaceAll("\\s", "_");
                for(AreaUnits areaUnits: AreaUnits.values()){
                    if(str.equalsIgnoreCase(areaUnits.name())){
                        return areaUnits;
                    }
                }
            }
            throw new IllegalArgumentException("Cannot find a value for " + str);
        }
    }

    @Override
    public BigDecimal Convert(String amount, String from, String to){
        return new BigDecimal(amount)
                .multiply(getRate(AreaUnits.fromString(from),AreaUnits.fromString(to)));
    }

    private BigDecimal getRate(AreaUnits from, AreaUnits to){
        BigDecimal result = new BigDecimal("0");

        switch (from){
            case SQUARE_CENTIMETER:
                result = getRate(AreaUnits.SQUARE_METER,to)
                        .divide(getRate(AreaUnits.SQUARE_METER,AreaUnits.SQUARE_CENTIMETER), 8, RoundingMode.CEILING);
                break;
            case SQUARE_METER:
                if(to == AreaUnits.SQUARE_CENTIMETER){
                    result = new BigDecimal("10000");
                }else if(to == AreaUnits.SQUARE_KILOMETER){
                    result = new BigDecimal("0.0000010");
                }else if(to == AreaUnits.SQUARE_INCH){
                    result = new BigDecimal("1550.0031");
                }else if(to == AreaUnits.SQUARE_FOOT){
                    result = new BigDecimal("10.7639");
                }else if(to == AreaUnits.SQUARE_YARD){
                    result = new BigDecimal("1.1959");
                }else if(to == AreaUnits.ACRE){
                    result = new BigDecimal("0.0002471");
                }else if(to == AreaUnits.SQUARE_MILE){
                    result = new BigDecimal("0.0000003861");
                }
                break;
            case SQUARE_KILOMETER:
                result = getRate(AreaUnits.SQUARE_METER,to)
                        .divide(getRate(AreaUnits.SQUARE_METER,AreaUnits.SQUARE_KILOMETER), 8, RoundingMode.CEILING);
                break;
            case SQUARE_INCH:
                result = getRate(AreaUnits.SQUARE_METER,to)
                        .divide(getRate(AreaUnits.SQUARE_METER,AreaUnits.SQUARE_INCH), 8, RoundingMode.CEILING);
                break;
            case SQUARE_FOOT:
                result = getRate(AreaUnits.SQUARE_METER,to)
                        .divide(getRate(AreaUnits.SQUARE_METER,AreaUnits.SQUARE_FOOT), 8, RoundingMode.CEILING);
                break;
            case SQUARE_YARD:
                result = getRate(AreaUnits.SQUARE_METER,to)
                        .divide(getRate(AreaUnits.SQUARE_METER,AreaUnits.SQUARE_YARD), 8, RoundingMode.CEILING);
                break;
            case ACRE:
                result = getRate(AreaUnits.SQUARE_METER,to)
                        .divide(getRate(AreaUnits.SQUARE_METER,AreaUnits.ACRE), 8, RoundingMode.CEILING);
                break;
            case SQUARE_MILE:
                result = getRate(AreaUnits.SQUARE_METER,to)
                        .divide(getRate(AreaUnits.SQUARE_METER,AreaUnits.SQUARE_MILE), 8, RoundingMode.CEILING);
                break;
        }
        return result;
    }
}
