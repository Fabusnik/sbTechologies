package main.ru.fab;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class TestData {

    public static void main(String[] args) {
        UtilClass utilClass = new UtilClass();
        utilClass.addElement("String".getBytes());

        Set<byte[]> bytes = utilClass.getListElements();
        for (byte[] iter : bytes){
            System.out.println("Set : " + new String(iter));
        }

        System.out.println("-----------------------------------------");

        Integer intDigit = 0;
        Float floatDigit = 0.0f;
        Double doubleDigit = 0.0;
        BigDecimal bigDigit = new BigDecimal(BigInteger.ZERO);

        System.out.println(UtilClass.compareNumberWithZero((byte) 0));

        System.out.println(UtilClass.compareNumberWithZero(floatDigit));
        System.out.println(UtilClass.compareNumberWithZero(intDigit));

        System.out.println(UtilClass.compareNumberWithZero(doubleDigit));
        System.out.println(UtilClass.compareNumberWithZero(bigDigit));

        System.out.println("-----------------------------------------");

        List<String> list = new ArrayList<>();
        list.add("one two three");
        list.add("four five six");

        UtilClass.logData("Input string   ", list, '/');

        System.out.println("-----------------------------------------");

        List<InsuranceHoliday> holidayList = new ArrayList<>();

        Calendar startDate = Calendar.getInstance();
        startDate.set(2018, 0, 1);

        Calendar endDate = Calendar.getInstance();
        endDate.set(2019, 11, 31);

        while (startDate.before(endDate)) {
            if (startDate.get(Calendar.DAY_OF_WEEK) == 7 || startDate.get(Calendar.DAY_OF_WEEK) == 1) {
                holidayList.add(new InsuranceHoliday(startDate.getTime()));
            }
            startDate.add(Calendar.DAY_OF_YEAR, 1);
        }


        Calendar date = Calendar.getInstance();
        date.set(2018,0,2);
        System.out.println(UtilClass.FORMAT_ISO.format(
                UtilClass.getDateAddPeriod(date.getTime(), holidayList)));

        System.out.println(UtilClass.FORMAT_ISO.format(
                UtilClass.getDateAddPeriod(null, holidayList)));
    }
}
