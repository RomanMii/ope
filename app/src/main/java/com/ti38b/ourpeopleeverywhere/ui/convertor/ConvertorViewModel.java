package com.ti38b.ourpeopleeverywhere.ui.convertor;

import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ti38b.ourpeopleeverywhere.R;
import com.ti38b.ourpeopleeverywhere.unitConvertor.AreaConvertor;
import com.ti38b.ourpeopleeverywhere.unitConvertor.LengthConvertor;
import com.ti38b.ourpeopleeverywhere.unitConvertor.WeightConvertor;

import java.math.BigDecimal;

public class ConvertorViewModel extends ViewModel {

    public BigDecimal calculateLength(String from){
        BigDecimal fromString = new BigDecimal(from);
        return fromString.multiply(new BigDecimal("2.0"));
    }

    public BigDecimal calculateLength(String amount, String from, String to){
        LengthConvertor lengthConvertor = new LengthConvertor();
        return lengthConvertor.Convert(amount,from,to);
    }

    public BigDecimal calculateWeight(String amount, String from, String to){
        WeightConvertor weightConvertor = new WeightConvertor();
        return weightConvertor.Convert(amount, from, to);
    }

    public BigDecimal calculateArea(String amount, String from, String to){
        AreaConvertor areaConvertor = new AreaConvertor();
        return areaConvertor.Convert(amount, from, to);
    }
}