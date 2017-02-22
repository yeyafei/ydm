package com.framework.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class SystemUtilities {

	/**
	 * 字符串为空返回""
	 * 
	 * @param source
	 * @return
	 */
	public static final String isNull(String str) {
		return ((str == null) ? "" : str);
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean isBlankStr(String str) {
		return (str == null || "".equals(str.trim()));
	}

	/**
	 * 集合是否为空
	 * 
	 * @param coll
	 * @return
	 */
	public static final boolean isBlankCollection(Collection<?> coll) {
		return (coll == null || coll.isEmpty());
	}

	/**
	 * 字符串是否不为空（包括一些常用的形容null的字符串）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNullString(String str) {
		boolean isNotNull = true;
		if (null == str || (null != str && "".equals(str.trim()))
				|| "undefined".equals(str) || "null".equals(str)
				|| "(null)".equals(str)) {
			isNotNull = false;
		}
		return isNotNull;
	}

	/**
	 * 
	 * @param numberString
	 * @return
	 */
	public static final boolean isValidNumber(String numberString) {
		try {
			@SuppressWarnings("unused")
			double l = Double.parseDouble(numberString);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * 
	 * @param numberString
	 * @return
	 */
	public static final boolean isValidInteger(String numberString) {
		try {
			@SuppressWarnings("unused")
			int l = Integer.parseInt(numberString);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * Check the decimalString is BigDecimal
	 * 
	 * @param decimalString
	 * @return
	 */
	public static final boolean isValidBigDecimal(String decimalString,
			int length, int scale) {
		try {
			BigDecimal big = new BigDecimal(decimalString);
			if ((decimalString.indexOf(".") > length - scale)
					|| (big.scale() > scale)) {
				return false;
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Check whether the provided String is a number with/without decimals(e.g.
	 * float, double, int, long).
	 * 
	 * @param numberString
	 *            The String of the number.
	 * @return The validation result.
	 */
	public static final boolean isValidFloatingPointNumber(String numberString) {
		try {
			@SuppressWarnings("unused")
			double d = Double.parseDouble(numberString);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * Convert a Gregorian Calendar object to a SQL Timestamp object.
	 * 
	 * @param date
	 *            The Gregorian Calendar object.
	 * @return The corresponding Timestamp object.
	 */
	public static final Timestamp toTimestamp(GregorianCalendar date) {
		return new Timestamp(date.getTime().getTime());
	}

	/**
	 * Convert a String object to a SQL Timestamp object by offer the formatter.
	 * 
	 * @param dateString
	 *            The string of date.
	 * @param formatter
	 *            The fomatter of date. Like as "yyyy-MM-dd-HH.mm.ss.SSSSSS"
	 * @return The corresponding Timestamp object.
	 */
	public static final Timestamp toTimestamp(String dateString,
			String formatter) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatter, Locale.US);
		GregorianCalendar gc = new GregorianCalendar();
		try {
			gc.setTime(sdf.parse(dateString));
		} catch (ParseException ex) {
			return null;
		}
		return new Timestamp(gc.getTime().getTime());
	}

	/**
	 * Convert a Timestamp object to a date String by offer the formatter
	 * 
	 * @param timestamp
	 *            The timestamp date object.
	 * @param formatter
	 *            The fomatter of the string. Like as
	 *            "yyyy-MM-dd-HH.mm.ss.SSSSSS"
	 * @return The String of the timestamp object.
	 */
	public static final String toDateString(Timestamp timestamp,
			String formatter) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatter, Locale.US);
			return sdf.format(timestamp);
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * Check whether the provided date is valid. The date string format must be
	 * in 'yyyy-MM-dd'.
	 * 
	 * @param dateString
	 *            A date string of 'yyyy-MM-dd'. e.g. 2000-01-29.
	 * @param formatter
	 *            The fomatter of the string. Like as "yyyy-MM-dd"
	 * @return Whether the inputted date is valid.
	 */
	public static final boolean isValidDate(String dateString, String formatter) {
		SimpleDateFormat myFormat = new SimpleDateFormat(formatter, Locale.US);
		String strDate = dateString;
		Date myDate = null;
		try {
			myDate = myFormat.parse(strDate);
		} catch (ParseException pe) {
			// Parse error, invalid date
			return false;
		}

		String newStrDate = myFormat.format(myDate);
		if (!strDate.equals(newStrDate)) {
			// formatted date invalid
			return false;
		}

		return true;
	}

	/**
	 * Check whether the string contains valid ASCII characters, i.e. ASCII
	 * value between 32 and 126.
	 * 
	 * @param input
	 *            The input string.
	 * @return Whether the string contains valid ASCII characters.
	 */
	public static final boolean isValidASCII(String input) {
		if (input == null) {
			return true;
		}
		for (int i = 0; i < input.length(); i++) {
			if ((input.charAt(i) > 126) || (input.charAt(i) < 32)) {
				if ((input.charAt(i) != 10) && (input.charAt(i) != 13)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check whether the ID, that must contains digitals, characters, underline
	 * if not, return false;
	 * 
	 * @param input
	 *            The input string.
	 * @return
	 */
	public static final boolean isValidID(String input) {
		if (input == null) {
			return true;
		} else {
			for (int i = 0; i < input.length(); i++) {
				if (!((input.charAt(i) > 47) && (input.charAt(i) < 58)
						|| (input.charAt(i) > 64) && (input.charAt(i) < 91)
						|| (input.charAt(i) > 96) && (input.charAt(i) < 123) || input
						.charAt(i) == 95)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check whether the ID, that must contains digitals, characters, underline
	 * and chinese if not, return false;
	 * 
	 * @param input
	 *            The input string.
	 * @return
	 */
	public static final boolean isValidName(String input) {
		if (input == null) {
			return true;
		} else {
			for (int i = 0; i < input.length(); i++) {
				if (!((input.charAt(i) > 47) && (input.charAt(i) < 58)
						|| (input.charAt(i) > 64) && (input.charAt(i) < 91)
						|| (input.charAt(i) > 96) && (input.charAt(i) < 123)
						|| (input.charAt(i) == 95) || (input.charAt(i) > 255))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check whether the password, that must contains digitals, characters, if
	 * not, return false;
	 * 
	 * @param input
	 *            The input string.
	 * @return Whether the string contains valid ASCII characters.
	 */
	public static final boolean isValidPassword(String input) {
		if (input == null) {
			return true;
		} else {
			for (int i = 0; i < input.length(); i++) {
				if (!((input.charAt(i) > 47) && (input.charAt(i) < 58)
						|| (input.charAt(i) > 64) && (input.charAt(i) < 91) || (input
						.charAt(i) > 96)
						&& (input.charAt(i) < 123))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check whether the input is all chinese if not, return false;
	 * 
	 * @param input
	 *            The input string.
	 * @return
	 */
	public static final boolean isAllChinese(String input) {
		if (input == null) {
			return true;
		} else {
			if (input.length() * 2 == input.getBytes().length) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check whether the input is all english if not, return false;
	 * 
	 * @param input
	 *            The input string.
	 * @return
	 */
	public static final boolean isAllEnglish(String input) {
		if (input == null) {
			return true;
		} else {
			for (int i = 0; i < input.length(); i++) {
				if (!((input.charAt(i) > 64) && (input.charAt(i) < 91)
						|| (input.charAt(i) > 96) && (input.charAt(i) < 123) || input
						.charAt(i) == 32)) {
					return false;
				}
			}
		}
		return true;

	}

	public static final boolean isValidEmail(String email) {
		String reg3 = "\\w+@\\w+.\\w+";
		String reg4 = "\\w+@\\w+.\\w+.\\w+";

		if (isBlankStr(email)) {
			return true;
		}
		boolean result3 = Pattern.matches(reg3, email);
		boolean result4 = Pattern.matches(reg4, email);

		if (result3 || result4) {
			return true;
		}
		return false;
	}

	public static final boolean isFirstLetterIsUpCase(String str) {
		String reg3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		if (isBlankStr(str)) {
			return false;
		}
		String up = str.substring(0, 1);

		int len = reg3.indexOf(up);

		if (len >= 0) {
			return true;
		}
		return false;
	}

	// 校验是否为数字或者字母
	public static final boolean isLetterAndNum(String str) {
		String reg3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		if (isBlankStr(str)) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			String up = str.substring(i, i + 1);
			int len = reg3.indexOf(up);
			if (len == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断一个String内的所有字符是否是一样的
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean isSameCharOfStr(String str) {
		if (isBlankStr(str)) {
			return false;
		}

		String firstChar = null;
		for (int i = 0; i < str.length(); i++) {
			if (i == 0) {
				firstChar = str.substring(i, i + 1);
			} else {
				if (!firstChar.equals(str.substring(i, i + 1))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 判断一个String是否是一组连续的数字
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean isConsecutiveDigitStr(String str) {
		if (isBlankStr(str)) {
			return false;
		}

		String scope = "0123456789";

		int len = str.length();
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			String s = str.substring(i, i + 1);
			int index = scope.indexOf(s);
			if (index == -1) {
				return false;
			} else {
				arr[i] = Integer.parseInt(s);
			}
		}

		// asc
		if (isConsecutiveDigit(arr, true)) {
			return true;
		}

		// desc
		if (isConsecutiveDigit(arr, false)) {
			return true;
		}

		return false;
	}

	/**
	 * 判断一个int数组的数字是否是连续的数字
	 * 
	 * @param arr
	 * @param isASC
	 * @return
	 */
	public static final boolean isConsecutiveDigit(int[] arr, boolean isASC) {
		int len = arr.length;
		if (isASC) {
			for (int i = 0; i < len; i++) {
				int value1 = arr[i];
				if (i < len - 1) {
					int value2 = arr[i + 1];
					if (value2 == value1 + 1) {
						continue;
					} else {
						return false;
					}
				}
			}

			return true;
		}

		// desc
		for (int i = 0; i < len; i++) {
			int value1 = arr[i];
			if (i < len - 1) {
				int value2 = arr[i + 1];
				if (value2 == value1 - 1) {
					continue;
				} else {
					return false;
				}
			}
		}

		return true;
	}

	public static final boolean isStringInteger(String numberString, int leng) {
		boolean b = true;
		try {
			@SuppressWarnings("unused")
			long l = Long.parseLong(numberString);
		} catch (NumberFormatException nfe) {
			return false;
		}
		if (b) {
			if (numberString.length() != leng) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}

	}

	/**
	 *得到某个时间点的前后N天.前 -,后+
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static String getBeforeNdate(String date, int n) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance(); // 当时的日期和时间
			c.setTime(df.parse(date));
			// Date date = bartDateFormat.parse(dateStringToParse);
			// System.out.println(df.format(c.getTime()));
			int d = c.get(Calendar.DAY_OF_MONTH); // 取出“日”数--d; // 将“日”减一，即得到前一天
			c.set(Calendar.DAY_OF_MONTH, d + n); // 将“日”数设置回去
			// c.add(Calendar.DAY_OF_MONTH,-1);
			return (df.format(c.getTime()));
		} catch (ParseException ex) {
			return date;
		}
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (isBlankStr(str)) {
			str = "";
		} else {
			str = str.trim();
		}
		return str;
	}

	/**
	 * 
	 * @param sdate
	 * @param edate
	 * @return
	 */
	public static String compareDate(String sdate, String edate) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar sc = Calendar.getInstance(); // sdate
			sc.setTime(df.parse(sdate));
			Calendar ec = Calendar.getInstance(); // edate
			ec.setTime(df.parse(edate));
			if (sc.before(ec)) {
				return "Less"; // sdate<edate
			} else if (sc.after(ec)) {
				return "Great"; // sdate>edate
			} else {
				return "Equal"; // =
			}
		} catch (ParseException ex) {
			return "Error";
		}
	}

	/**
	 *判断一个字符串是否有中文
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean isContainChinese(String str) {
		int yleng = str.length();
		int hleng = strlen(str);
		if (yleng != hleng) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	private static int strlen(String str) {
		if (str == null || str.length() <= 0) {
			return 0;
		}

		int len = 0;

		char c;
		for (int i = str.length() - 1; i >= 0; i--) {
			c = str.charAt(i);
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')
					|| (c >= 'A' && c <= 'Z')) {
				// 字母, 数字
				len++;
			} else {
				if (Character.isLetter(c)) { // 中文
					len += 2;
				} else { // 符号或控制字符
					len++;
				}
			}
		}

		return len;
	}

	/**
	 * 如果字符串中间有多个空格，只保留一个
	 * 
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceMoreSpace(String str) {
		int i = 0;
		while (true && str.length() > 1) {
			char ch_1 = str.charAt(i);
			char ch_2 = str.charAt(i + 1);
			if (ch_1 == ch_2 && ch_2 == ' ') {
				str = str.substring(0, i + 1) + str.substring(i + 2);
				i--;
			}
			i++;
			if (i == (str.length() - 1)) {
				break;
			}
		}
		return str.trim();
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @throws java.lang.Exception
	 */
	public static String isOkOrigin(String str) throws Exception {
		if (SystemUtilities.isBlankStr(str)) {
			throw new Exception("Origin is null.");
		}
		str = str.trim().toUpperCase();
		str = str.replaceAll("\\+", "");
		str = str.replaceAll("&", "");
		str = str.replaceAll(" ", "");
		return str;
	}

	/**
	 * 深度复制HashSet
	 * 
	 * @param hashSet
	 * @return oHashSet
	 */
	@SuppressWarnings("unchecked")
	public static HashSet deepCopyHashSet(HashSet hashSet) {
		if (hashSet.size() < 1) {
			return hashSet;
		}
		HashSet oHashSet = new HashSet();
		for (Iterator it = hashSet.iterator(); it.hasNext();) {
			oHashSet.add(it.next());
		}
		return oHashSet;
	}

	/**
	 * double 值的四舍五入
	 * 
	 * @param value
	 *            double型的值
	 * 
	 * @param scale
	 *            保留精度
	 * @return
	 * @throws Exception
	 */
	public static double DOUBLE_ROUND_HALF_UP(double value, int scale) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
		double d = bd.doubleValue();
		bd = null;
		return d;
	}

	/**
	 * 取服务器日期时间
	 * 
	 * @return
	 */
	public static String getSysDateTime() {
		Date date = new Date(); // 取服务器日期时间
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String DateTime = formatter.format(date);
		return DateTime;
	}

	/**
	 * 取服务器日期
	 * 
	 * @return
	 */
	public static String getSysDate() {
		Date date = new Date(); // 取服务器时间
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String DateTime = formatter.format(date);
		return DateTime;
	}

	/**
	 * String To Integer, If isBlankStr then output "0"
	 * 
	 * @param s
	 * @return
	 */
	public static Integer toIntegerWithBlankStr(String s) {
		double d = 0;
		if (!SystemUtilities.isBlankStr(s)) {
			d = Double.parseDouble(s.replaceAll(",", ""));
		}
		return Integer.valueOf((int) d);
	}

	/**
	 * String To Double, If isBlankStr Then output "0"
	 * 
	 * @param s
	 * @return
	 */
	public static Double toDoubleWithBlankStr(String s) {
		double d = 0;
		if (!SystemUtilities.isBlankStr(s)) {
			d = Double.parseDouble(s.replaceAll(",", ""));
		}
		return Double.valueOf(d);
	}

	@SuppressWarnings("unchecked")
	public static String[] getSep(String str, String sep) {
		// 1维数组;表,记录
		String strName[];

		// 截取串

		StringTokenizer stBetweenTable = new StringTokenizer(str, sep);

		ArrayList list = new ArrayList();

		while (stBetweenTable.hasMoreElements()) {
			String strT = (String) stBetweenTable.nextElement();
			list.add(strT);
		}

		strName = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			String strTableName = (String) list.get(i);
			strName[i] = strTableName;
		}

		return strName;
	}
}