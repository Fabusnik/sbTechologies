package main.ru.fab;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class UtilClassTest {
    @Test
    public void compareNumberWithZero() throws Exception {
        Integer intDigit = 0;
        Float floatDigit = 0.0f;
        Double doubleDigit = 0.0;
        BigDecimal bigDigit = new BigDecimal(BigInteger.ZERO);

        assertFalse(UtilClass.compareNumberWithZero((byte) 0));

        assertTrue(UtilClass.compareNumberWithZero(floatDigit));
        assertTrue(UtilClass.compareNumberWithZero(intDigit));

        assertTrue(UtilClass.compareNumberWithZero(doubleDigit));
        assertTrue(UtilClass.compareNumberWithZero(bigDigit));
    }

    @Test
    public void getDateAddPeriod() throws Exception {
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

        assertEquals("2019-01-10", UtilClass.FORMAT_ISO.format(
                UtilClass.getDateAddPeriod(date.getTime(), holidayList)));

        assertEquals("2019-01-27", UtilClass.FORMAT_ISO.format(
                UtilClass.getDateAddPeriod(null, holidayList)));
    }

}