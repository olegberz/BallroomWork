package com.forwork.ballroomwork.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class CalendarController {

    @GetMapping("/calendar")
    public String getCalendar(@RequestParam(value = "year", required = false, defaultValue = "2025") int year,
                              @RequestParam(value = "month", required = false, defaultValue = "0") int month,
                              Model model) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2025);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);

        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        List<String> calendarDays = new ArrayList<>();

        for (int i = 1; i < firstDayOfWeek; i++) {
            calendarDays.add("");
        }

        for (int day = 1; day < daysInMonth; day++) {
            calendarDays.add(String.valueOf(day));
        }

        model.addAttribute("prevMonth", month == 0 ? 11 : month - 1);
        model.addAttribute("nextMonth", month == 11 ? 0 : month + 1);
        model.addAttribute("prevYear", month == 0 ? year - 1 : year);
        model.addAttribute("nextYear", month == 11 ? year + 1 : year);



        return  "calendar";
    }
}


