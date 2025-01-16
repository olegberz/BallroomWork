package com.forwork.ballroomwork.Controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Controller
public class CalendarController {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final String CALENDAR = "https://www.googleapis.com/auth/calendar";
    private static final String CALENDAR_EVENTS = "https://www.googleapis.com/auth/calendar.events";

    private Calendar getService() throws GeneralSecurityException, IOException {
        GoogleCredential credentials = GoogleCredential.fromStream(
                        getClass().getResourceAsStream("/credentials.json"))
                .createScoped(List.of(CALENDAR, CALENDAR_EVENTS));

        return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credentials)
                .setApplicationName("BallroomWork")
                .build();
    }

    @GetMapping("/calendar")
    public String getCalendar(){
        return "/calendar";
    }

    @PostMapping("/calendar/add-event")
    public String addEvent(
            @RequestParam String summary,
            @RequestParam String description,
            @RequestParam String startDateTime,
            @RequestParam String endDateTime,
            Model model) {
        try {
            Calendar service = getService();

            Event event = new Event()
                    .setSummary(summary)
                    .setDescription(description);

            EventDateTime start = new EventDateTime()
                    .setDateTime(new com.google.api.client.util.DateTime(startDateTime))
                    .setTimeZone("Europe/Riga");
            event.setStart(start);

            EventDateTime end = new EventDateTime()
                    .setDateTime(new com.google.api.client.util.DateTime(endDateTime))
                    .setTimeZone("Europe/Riga");
            event.setEnd(end);

            service.events().insert("primary", event).execute();

            model.addAttribute("message", "Событие успешно добавлено!");
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка добавления события: " + e.getMessage());
        }

        return "calendar";
    }
}



