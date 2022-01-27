package com.ti38b.ourpeopleeverywhere.unitConvertor;

import java.math.BigDecimal;

public interface Convertor {
    BigDecimal Convert(String amount, String from, String to);
}
