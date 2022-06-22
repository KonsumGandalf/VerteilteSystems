package de.oth.vs.xml.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

    @Override
    public String marshal(Date datum) throws Exception {
        return dateFormat.format(datum);
    }

    @Override
    public Date unmarshal(String datumString) throws Exception {
        return dateFormat.parse(datumString);
    }

}