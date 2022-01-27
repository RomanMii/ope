package com.ti38b.ourpeopleeverywhere.unitConvertor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WeightConvertor implements Convertor{
    public enum WeightUnits{
        GRAM,
        KILOGRAM,
        TONNE,
        OUNCE,
        POUND;

        public static WeightUnits fromString(String str){
            if(str != null){
                for(WeightUnits weightUnits: WeightUnits.values()){
                    if(str.equalsIgnoreCase(weightUnits.name())){
                        return weightUnits;
                    }
                }
            }
            throw new IllegalArgumentException("Cannot find a value for " + str);
        }
    }

    @Override
    public BigDecimal Convert(String amount, String from, String to){
        return new BigDecimal(amount).multiply(getRate(WeightUnits.fromString(from),WeightUnits.fromString(to)));
    }

    private BigDecimal getRate(WeightUnits from, WeightUnits to){
        BigDecimal result = new BigDecimal("0");

        switch (from){
            case GRAM:
                result = getRate(WeightUnits.KILOGRAM,to).divide(getRate(WeightUnits.KILOGRAM,WeightUnits.GRAM), 4, RoundingMode.CEILING);
                break;
            case KILOGRAM:
                if(to == WeightUnits.GRAM){
                    result = new BigDecimal("1000");
                }else if(to == WeightUnits.TONNE){
                    result = new BigDecimal("0.001");
                }else if(to == WeightUnits.OUNCE){
                    result = new BigDecimal("35.2739");
                }else if(to == WeightUnits.POUND)
                    result = new BigDecimal("2.2046");
                break;
            case TONNE:
                result = getRate(WeightUnits.KILOGRAM,to).divide(getRate(WeightUnits.KILOGRAM,WeightUnits.TONNE), 4, RoundingMode.CEILING);
                break;
            case OUNCE:
                result = getRate(WeightUnits.KILOGRAM,to).divide(getRate(WeightUnits.KILOGRAM,WeightUnits.OUNCE), 4, RoundingMode.CEILING);
                break;
            case POUND:
                result = getRate(WeightUnits.KILOGRAM,to).divide(getRate(WeightUnits.KILOGRAM,WeightUnits.POUND), 4, RoundingMode.CEILING);
                break;
        }
        return result;
    }
}
