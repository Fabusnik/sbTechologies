package main.ru.fab;

import java.util.Date;

public class InsuranceHoliday {
    private Date holiday;

    public InsuranceHoliday(Date holiday) {
        this.holiday = holiday;
    }

    public Date getTheDate(){
        return holiday;
    }
}
