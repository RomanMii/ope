package com.ti38b.ourpeopleeverywhere.unitConvertor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LengthConvertor implements Convertor{
    public enum LengthUnits{
        MILLIMETER,
        CENTIMETER,
        METER,
        KILOMETER,
        INCH,
        FOOT,
        YARD,
        MILE;

        public static LengthUnits fromString(String str){
            if(str != null){
                for(LengthUnits lengthUnits: LengthUnits.values()){
                    if(str.equalsIgnoreCase(lengthUnits.name())){
                        return lengthUnits;
                    }
                }
            }
            throw new IllegalArgumentException("Cannot find a value for " + str);
        }
    }

    @Override
    public BigDecimal Convert(String amount, String from, String to){
        return new BigDecimal(amount).multiply(getRate(LengthUnits.fromString(from),LengthUnits.fromString(to)));
    }

    private BigDecimal getRate(LengthUnits from, LengthUnits to){
        BigDecimal result = new BigDecimal("0");
        switch (from){
            case MILLIMETER:
                result = getRate(LengthUnits.KILOMETER,to).divide(getRate(LengthUnits.KILOMETER,LengthUnits.MILLIMETER),4, RoundingMode.CEILING);
                break;
            case CENTIMETER:
                result = getRate(LengthUnits.KILOMETER,to).divide(getRate(LengthUnits.KILOMETER,LengthUnits.CENTIMETER),4, RoundingMode.CEILING);
                break;
            case METER:
                result = getRate(LengthUnits.KILOMETER,to).divide(getRate(LengthUnits.KILOMETER,LengthUnits.METER),4, RoundingMode.CEILING);
                break;
            case KILOMETER:
                if(to == LengthUnits.MILLIMETER){
                    result = new BigDecimal("1000000");
                }else if(to == LengthUnits.CENTIMETER){
                    result = new BigDecimal("100000");
                }else if(to == LengthUnits.METER){
                    result = new BigDecimal("1000");
                }else if(to == LengthUnits.INCH){
                    result = new BigDecimal("39370.079");
                }else if(to == LengthUnits.FOOT){
                    result = new BigDecimal("3280.84");
                }else if(to == LengthUnits.YARD){
                    result = new BigDecimal("1093.6133");
                }else if(to == LengthUnits.MILE){
                    result = new BigDecimal("0.6213712");
                }
                break;
            case INCH:
                result = getRate(LengthUnits.KILOMETER,to).divide(getRate(LengthUnits.KILOMETER,LengthUnits.INCH),4, RoundingMode.CEILING);
                break;
            case FOOT:
                result = getRate(LengthUnits.KILOMETER,to).divide(getRate(LengthUnits.KILOMETER,LengthUnits.FOOT),4, RoundingMode.CEILING);
                break;
            case YARD:
                result = getRate(LengthUnits.KILOMETER,to).divide(getRate(LengthUnits.KILOMETER,LengthUnits.YARD),4, RoundingMode.CEILING);
                break;
            case MILE:
                result = getRate(LengthUnits.KILOMETER,to).divide(getRate(LengthUnits.KILOMETER,LengthUnits.MILE),4, RoundingMode.CEILING);
                break;
        }
        return result;
    }
}
