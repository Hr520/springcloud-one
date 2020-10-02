package com.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	
	private final static SimpleDateFormat sdfMoth = new SimpleDateFormat("yyyy-MM");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private final static SimpleDateFormat sdfTimeNum = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 获取当前年(YYYY格式)
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}
	
	public static String sdfMoth() {
		String str=sdfMoth.format(new Date());
		str=str.substring(5,7);
		return str;
	}

	/**
	 * 获取当前年月日(YYYY-MM-DD格式)
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取当前年月日(YYYYMMDD格式)
	 * 
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}
	
	/**
	 * 获取当前月份(M格式)
	 * 
	 * @return
	 */
	public static String getMonth() {
        Calendar cale = Calendar.getInstance();  
		return cale.get(Calendar.MONTH) + 1 + "";
	}

	/**
	 * 获取当前时间(YYYY-MM-DD HH:mm:ss格式)
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}
	/**
	 * 获取当前时间(YYYYMMDDHHmmss格式)
	 * 
	 * @return
	 */
	public static String getTimeNum() {
		return sdfTimeNum.format(new Date());
	}

	/**
	 * 日期比较，如果s>=e 返回true 否则返回false
	 * @param s 时间1
	 * @param e 时间2
	 * @return
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 获取2个日期之间相差年数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取2个时间之间相差天数
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        
        try {
        	beginDate = format.parse(beginDateStr);
			endDate= format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        
        return day;
    }

    /**
     * 得到当前时间daysInt天之后的日期
     * @param daysInt
     * @return
     */
    public static String getAfterDayDate(int daysInt) {

        Calendar canlendar = Calendar.getInstance(); 
        canlendar.add(Calendar.DATE, daysInt); 
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 计算某日期 间隔多少天的日期
     * @param strDate 字符串日期(yyyy-MM-dd)
     * @param daysInt 正数表示该日期后n天, 负数表示该日期前n天
     * @return
     */
    public static String getDiffDayDate(String strDate, int daysInt) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar canlendar = Calendar.getInstance();
        try {
			canlendar.setTime(sdf.parse(strDate));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
        canlendar.add(Calendar.DATE, daysInt); 
        Date date = canlendar.getTime();
        String dateStr = sdf.format(date);
        return dateStr;
    }
    
    /**
     * 得到当前时间daysInt天之后是周几
     * @param daysInt
     * @return
     */
    public static String getAfterDayWeek(int daysInt) {

        Calendar canlendar = Calendar.getInstance(); 
        canlendar.add(Calendar.DATE, daysInt); 
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    
    /**
     * 获取某个时间后daynum天后的日期
     * @param date
     * @param daynum
     * @return
     */
    public static String gettonextDate(String date,int daynum){
        try {  
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");  
            Calendar cal = Calendar.getInstance();  
            cal.setTime(sf.parse(date));  
            cal.add(Calendar.DAY_OF_YEAR, +daynum);  
            String nextDate_1 = sf.format(cal.getTime());  
            return nextDate_1;  
        } catch (Exception e) {  
        	e.printStackTrace();
        	return null; 
        }  
    } 

 
    /**
     * 获取本月有多少天
     * @return
     */
    public static int getCurrentMonthLastDay()  
    {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
    
    /**
     * 获取本月第一天  (yyyy-MM-dd格式)
     * @return
     */
    public static String getMonthFirst(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");     	
    	Calendar cal_1=Calendar.getInstance();//获取当前日期 
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String firstDay = format.format(cal_1.getTime());
        return firstDay;
    }
    
    /**
     * 把时间戳转换为时间
     * @param str 微秒格式
     * @return
     */
    public static String stampToDate(String str){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(str);
        Date date = new Date(lt);
        String res = simpleDateFormat.format(date);
        return res;
    }
    
	/**
	 * Date 转  String
	 * @param	date 日期
	 * @param	format 转换格式
	 * @return
	 */
    public static String dateToString(Date date,String format) {
		try {
			return new SimpleDateFormat(format).format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
    /**
     * String 转  Date
     * @param	date 字符串日期
     * @param	format 转换格式
     * @return
     */
    public static Date stringToDate(String date,String format) {
    	try {
    		return new SimpleDateFormat(format).parse(date);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    /**
	 * 比较两个日期的大小
	 * @param sDate 字符串日期1
	 * @param eDate 字符串日期2
	 * @param format 转换格式
	 * @return 若sDate>eDate 返回true 否则返回false
	 */
	public static boolean compareDates(String sDate, String eDate, String format) {
		Date s = stringToDate(sDate, format);
		Date e = stringToDate(eDate, format);
		if (s.after(e)) {
			// 表示sDate > eDate
			return true;
		} else {
			// 表示sDate <= eDate
			return false;
		}
	}
	
	/**
     * 获取某个日期的本周一日期
     * @param
     * @return
	 * @throws ParseException 
     */
    public static String getThisWeekMonday(String dateString) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = sdf.parse(dateString);
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return sdf.format(cal.getTime());
    }
	
	public static void main(String[] args) throws ParseException {
		/*String b="2017-10-12";
		String a="2017-10-13";*/
		System.out.println(getThisWeekMonday("2018-02-28"));
	}
	
	
	/**
	 * 
	 * 将字符串时间转为时间戳输出
	 * 
	 * 
	 */
	public  static int changeTimeToUNIX(String time){
		int UNIX=0;
		try {
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 Date dateStart = format.parse(time);
			 UNIX = (int) (dateStart.getTime() / 1000);
		} catch (Exception e) {
		}
		 return UNIX;
	}

	public static long getNowUnixTime(){
		return System.currentTimeMillis()/1000;
	}
	
}
