package com.forwork.ballroomwork.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class CalendarController {

    @GetMapping("/calendar")
    public String getCalendar(Model model) {

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

        model.addAttribute("days", calendarDays);
        model.addAttribute("month", "January");
        model.addAttribute("year", "2025");


        return  "calendar";
    }
}


