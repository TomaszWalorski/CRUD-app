package pl.polsl.tomasz.walorski.controller;

import java.util.Date;

/**
 *
 * @author Tomasz Walorski
 * Class include methods ensuring, that input has proper type.
 */
public class TypeController {
    /**
     * Method check, if inputed string is empty.
     * @param input String object entered to check.
     * @return Method return true, if inputed string is not empty.
     */
    public boolean isNotEmpty(String input){
        if(input == null) return false;
        if(input.isEmpty()) return false;
        return true;
    }
    /**
     * Method check, if inputed string is integer.
     * @param input String object entered to check.
     * @return Method return true, if inputed has proper type.
     */
    public boolean isInteger(String input){
        try{
            Integer.parseInt(input);
        } catch (NumberFormatException e){
            return false;
        } catch (NullPointerException e){
            return false;
        }
        return true;
    }
    /**
     * Method check, if inputed year is leap one.
     * @param year Year value entered to check.
     * @return Method return true, if inputed year is leap one.
     */
    public boolean isLeapYear(int year){
        if(year%4 != 0){
            return false;
        } else if(year%100 == 0){
            if(year%400 != 0) return false; 
        }
        return true;
    }
    /**
     * Method check, if inputed mounth's value fit in proper range.
     * @param input Mounth's value entered to check.
     * @return Method return true, if inputed mounth's value fit in proper range.
     */
    public boolean isMonth(String input){
        if(!isInteger(input)) return false;
        int month = Integer.parseInt(input);
        if(12 < month) return false;
        return true;
    }
    /**
     * Method check, if inputed day's value fit in proper range.
     * @param input Day's value entered to check.
     * @param month Mounth's value entered to obtain range of proper values for day.
     * @param isLeapYear Boolean value, informing if it is leap year, entered to obtain range of proper values for day.
     * @return Method return true, if inputed day's value fit in proper range.
     */
    public boolean isDay(String input, int month, boolean isLeapYear){
        if(!isInteger(input)) return false;
        int day = Integer.parseInt(input);
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            if(day > 31) return false;
        } else if(month == 4 || month == 6 || month == 9 || month == 11){
            if(day > 30) return false;
        } else if(month == 2) {
            if(isLeapYear) {
                if(day > 29) return false;
            } else if(day > 28) return false;
        }
        return true;
    }
    /**
     * Method check if input data is not from future.
     * @param date Date entered to check.
     * @return Method return true, if input data is not from future.
     */
    public boolean isDate(Date date){
        if((new Date()).getTime() < date.getTime()) return false;
        return true;
    }
}
