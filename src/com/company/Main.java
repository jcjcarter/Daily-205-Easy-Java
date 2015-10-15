package com.company;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PrintStream output = System.out;

        String firstDateStr = input.next();
        String secondDateStr = input.next();
        Date firstDate = processDate(firstDateStr);
        Date secondDate = processDate(secondDateStr);
        String result = getDateRange(firstDate, secondDate);
        output.println(result);
        input.close();
        output.close();

    }

    private static String getDateRange(Date first, Date second) {
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        String result = "";
        result += first.getMonth();
        result += " ";
        result += first.getDay();
        boolean displaySecondDate = !(first.year == second.year &&
                first.month == second.month && first.day == second.day);
        boolean displayFirstYear = (curYear != first.year && (first.year
                != second.year || !displaySecondDate)) ||
                (curYear == first.year && (second.year > first.year+1 ||
                        (second.year == first.year+1 && (second.month > first.month
                                || second.month == first.month && second.day >= first.day))));

        if (displayFirstYear) {
            result += ", "+ first.getYear();
        }

        if (!displaySecondDate) {
            return result;
        }
        result += " - ";

        boolean displaySecondMonth = !(first.year == second.year && first.month == second.month);
        if (displaySecondMonth) {
            result += second.getMonth()+ " ";
        }

        result += second.getDay();
        boolean displaySecondYear = (displayFirstYear || !displayFirstYear &&
                (curYear != second.year && curYear != first.year));
        if (displaySecondYear) {
            result+=", "+ second.getYear();
        }

        return result;

    }

    private static Date processDate(String dateStr){
        Date result = new Date();

        result.year = Integer.parseInt(dateStr.substring(0, 4));
        result.month = Integer.parseInt(dateStr.substring(5, 7));
        result.day = Integer.parseInt(dateStr.substring(8, 10));

        return result;
    }

    private static class Date {
        int year;
        int month;
        int day;
        private static String[] MONTHS = {"January", "February", "March", "April",
                "May", "June", "July", "August",
                "September", "October", "November", "December"};

        private static String[] DAYS= {"1st", "2nd", "3rd", "4th", "5th",
                "6th", "7th", "8th", "9th", "10th",
                "11th", "12th", "13th", "14th", "15th",
                "16th", "17th", "18th", "19th", "20th",
                "21st", "22nd", "23rd", "24th", "25th",
                "26th", "27th", "28th", "29th", "30th",
                "31st"};
        public String getYear() {
            return Integer.toString(year);
        }

        public String getMonth() {
            return MONTHS[month-1];
        }
        public String getDay() {
            return DAYS[day-1];
        }

    }
}
