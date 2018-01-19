package main.ru.fab;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public final class UtilClass {
    private static final SimpleDateFormat FORMAT =
            new SimpleDateFormat("dd-MM-yy");

    static final SimpleDateFormat FORMAT_ISO =
            new SimpleDateFormat("YYYY-MM-dd");

    /**
     * Константа увеличения рабочих дней.
     */
    private static final int WORKING_DAY_ADD = 5;
    /**
     * Константа увеличения календарных дней.
     */
    private static final int CALENDAR_DAY_ADD = 1;
    /**
     * Константа увеличения количества лет.
     */
    private static final int YEAR_ADD = 1;

    private Set<byte[]> listElements = new HashSet<>();


    public Set<byte[]> getListElements() {
        return this.listElements;
    }

    public void addElement(final byte[] element) {
        //  if (!listElements.contains(element))
        listElements.add(element);
    }

    public static <T extends Number> boolean compareNumberWithZero(final T obj) {
        String simpleNameObjectClass = obj.getClass().getSimpleName().toString();
        switch (simpleNameObjectClass) {
            case "Integer":
                return obj.equals(0);
            case "Double":
                return obj.equals(0.0);
            case "Float":
                return new Float(0.0).equals(obj);
            case "BigDecimal":
                return BigDecimal.ZERO.equals(obj);
            default:
                return false;
        }
    }

    /**
     * Метод логирует данные (предполагается использование из внешних модулей).
     * <p>
     * Формат: [текущая дата в виде dd-MM-yy]: inputString; dataElements как строка (через сепаратор)
     *
     * @param inputString  строка из инпута
     * @param dataElements коллеция с данными для логирования
     * @param separator    символ, который используется для разделения элементов из коллеции dataElements для создания строкового представления.
     */

    public static void logData(final String inputString, final Collection dataElements, final char separator) {

        String result = "[" + FORMAT.format(new Date()) + "]: " + inputString.trim() + "; ";

        for (Object obj : dataElements) {
            result += obj + Character.toString(separator);
        }
        System.out.println(result.substring(0, result.length() - 1));
    }

    /**
     * Метод возвращает дату, увеличенную нв период,
     * заданный константами (WORKING_DAY_ADD, CALENDAR_DAY_ADD, YEAR_ADD).
     * <p>
     * @param startDate или текущая дата, если startDate = null, или меньше текущей даты.
     * @param classifier - справочник содержащий даты выходных и праздничных дней.
     */
    public static Date getDateAddPeriod(final Date startDate, final List<InsuranceHoliday> classifier) {

        Calendar calendar = Calendar.getInstance();

        if (startDate != null && startDate.compareTo(calendar.getTime()) < 0) {
            calendar.setTime(startDate);
        }

        int workingDays = 0;

        while (workingDays < WORKING_DAY_ADD) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            boolean weekend = false;
            for (InsuranceHoliday holiday : classifier) {
                if (FORMAT_ISO.format(holiday.getTheDate()).equals(FORMAT_ISO.format(calendar.getTime()))) {
                    weekend = true;
                    break;
                }
            }
            if (!weekend) {
                workingDays++;
            }
        }

        calendar.add(Calendar.DAY_OF_YEAR, CALENDAR_DAY_ADD);
        calendar.add(Calendar.YEAR, YEAR_ADD);
        return calendar.getTime();
    }
}

