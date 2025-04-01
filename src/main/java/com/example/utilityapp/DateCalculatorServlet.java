package com.example.utilityapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/date-calculator") // URL mapping for the servlet
public class DateCalculatorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dobString = request.getParameter("dob"); // Get date of birth from the form

        response.setContentType("text/html"); // Set content type for the response
        PrintWriter out = response.getWriter();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dob = LocalDate.parse(dobString, formatter);
            long totalDays = java.time.temporal.ChronoUnit.DAYS.between(dob, LocalDate.now());
            Period period = Period.between(dob, LocalDate.now());

            int years = period.getYears();
            int months = period.getMonths();
            int days = period.getDays();

            out.println("<html><head><title>Date Calculator Result</title>Age Calculator</head><body>");
            out.println("<h1>You are " + totalDays + " days old.</h1>");
            out.println("<h1>You are " + years+" Years"+ months+" Months"+days+" Days old.</h1>");
            out.println("</body></html>");

        } catch (DateTimeParseException e) {
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h1>Invalid date format. Please use YYYY-MM-DD.</h1>");
            out.println("<a href=\"index.html\"> Back to Calculator </a>");
            out.println("</body></html>");
        }
    }
}
i
