package com.framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class NumberFormat {
	// money精度
	private final static int PRECISION_MONEY = 2;
	// 重量精度
	private final static int PRECISION_WEIGHT = 3;
	// 单价精度
	private final static int PRECISION_UNITPRICE = 8;

	public NumberFormat() {
	}

	/**
	 * 格式化单价
	 * 
	 * 
	 * @param up
	 *            单价
	 * @return 格式化后的单价
	 */
	public static String formatUnitPrice(String up) {
		up = formatNumber(up, PRECISION_UNITPRICE);

		return up;
	}

	/**
	 * 格式化重量
	 * 
	 * 
	 * @param weight
	 *            重量
	 * @return 格式化后的重量
	 */
	public static String formatWeight(String weight) {
		weight = formatNumber(weight, PRECISION_WEIGHT);

		return weight;
	}

	public static String formatMoney(String money) {
		money = formatNumber(money, PRECISION_MONEY);

		return money;
	}

	/**
	 * 格式化数字
	 * 
	 * 
	 * @param num
	 *            数字
	 * @param len
	 *            精度
	 * @return 格式化的数字
	 */
	public static String formatNumber(String num, int len) {
		num = formatNum(num, len, true);
		// try
		// {
		// num = num.replaceAll(",", "");
		// if(len > 0)
		// {
		// num = formatDecimal(num, len);
		// }
		// num = formatPermillage(num);
		// }
		// catch(NumberFormatException ex)
		// {
		// }

		return num;
	}

	/**
	 * 格式化小数点后的位数
	 * 
	 * @param decimal
	 *            传入串
	 * 
	 * 
	 * @param len
	 *            精确的小数位数
	 * 
	 * 
	 * @return 格式化后的串
	 * @throws java.lang.NumberFormatException
	 */
	public static String formatDecimal(String srcStr, int nAfterDot)
			throws NumberFormatException {
		String re = "0";
		re = formatNum(srcStr, nAfterDot, false);

		// if(srcStr == null || srcStr.trim().length() == 0)
		// {
		// return "0";
		// }
		// String re = "0";
		// srcStr = srcStr.replaceAll(",", "");
		// try
		// {
		// String sf = "###0";
		//
		// if(nAfterDot > 0)
		// {
		// sf = sf + ".";
		// for(int i = 0; i < nAfterDot; i++)
		// {
		// sf = sf + "0";
		// }
		// }
		//
		// DecimalFormat df = new DecimalFormat(sf);
		// re = df.format(Double.parseDouble(srcStr));
		// }
		// catch(NumberFormatException ex)
		// {
		// }

		return re;
	}

	/**
	 * 
	 * @param num
	 * @param len
	 * @param pre
	 * @return
	 */
	public static String formatNum(double num, int len, boolean pre) {
		String str = String.valueOf(num);
		str = formatNum(str, len, pre);
		return str;
	}

	/**
	 * 格式化数字
	 * 
	 * 
	 * @param num
	 *            数字
	 * @param len
	 *            精度(保留小数点后位数)
	 * @param pre
	 *            精度(要不要千分符,)
	 * @return 格式化的数字
	 */
	public static String formatNum(String num, int len, boolean pre) {
		if (num == null || num.trim().length() == 0) {
			return "0";
		}
		num = num.trim().replaceAll(",", "");
		String re = "0";
		try {
			String sf = "###0";

			if (pre) {
				sf = "#,##0";
			}
			if (len > 0) {
				sf = sf + ".";
				for (int i = 0; i < len; i++) {
					sf = sf + "0";
				}
			}
			DecimalFormat df = new DecimalFormat(sf);
			re = df.format(Double.parseDouble(num));
			if (Double.parseDouble(num.replaceAll(",", "").trim()) == 0) {
				re = re.replaceAll("-", "");
			}
		} catch (NumberFormatException ex) {
		}
		// egao,george change it at 2006.11.20
		if (len == 3) {
			re = setFormat(re, pre);
		}

		return re;
	}

	/**
	 * 格式化数字,向上取整
	 * 
	 * @param num
	 *            数字
	 * @param pre
	 *            精度(要不要千分符,)
	 * @param jz
	 *            净重
	 * @return 格式化的数字
	 */
	public static String formatNumCeil(String num, boolean pre) {
		if (num == null || num.trim().length() == 0) {
			return "0";
		}
		num = num.trim().replaceAll(",", "");
		String re = "0";
		try {
			String sf = "###0";

			if (pre) {
				sf = "#,##0";
			}
			DecimalFormat df = new DecimalFormat(sf);
			re = df.format(Math.ceil((Double.parseDouble(num))));
			if (Double.parseDouble(num.replaceAll(",", "").trim()) == 0) {
				re = re.replaceAll("-", "");
			}

		} catch (NumberFormatException ex) {
		}
		return re;
	}

	/**
	 * 格式化数字（进行四舍五入）
	 * 
	 * @param num
	 *            数字
	 * @param len
	 *            精度(保留小数点后位数)
	 * @param pre
	 *            是否需要千分符
	 * @return 格式化后的数字
	 */
	public static String formatNumByRoundHalfUp(String num, int len, boolean pre) {
		if (num == null || num.trim().length() == 0) {
			return "0";
		}
		num = num.trim().replaceAll(",", "");
		String re = "0";
		try {
			String sf = "###0";

			if (pre) {
				sf = "#,##0";
			}
			if (len > 0) {
				sf = sf + ".";
				for (int i = 0; i < len; i++) {
					sf = sf + "0";
				}
			}
			DecimalFormat df = new DecimalFormat(sf);
			BigDecimal bd = new BigDecimal(num).setScale(len,
					BigDecimal.ROUND_HALF_UP);
			re = df.format(bd.doubleValue());
			if (Double.parseDouble(num.replaceAll(",", "").trim()) == 0) {
				re = re.replaceAll("-", "");
			}
		} catch (NumberFormatException ex) {
		}
		// egao,george change it at 2006.11.20
		// if(len == 3)
		// {
		// re = setFormat(re, pre);
		// }

		return re;
	}

	/**
	 * 格式化千分位
	 * 
	 * @param p
	 *            输入
	 * @return 千分位格式化的数字
	 */
	@SuppressWarnings("unused")
	private static String formatPermillage(String p) {
		String str = p;
		int i = str.indexOf(".");
		int len = 0;
		if (i > -1) {
			str = str.substring(i + 1);
			len = str.length();
		}
		p = formatNum(p, len, true);
		// if(p == null || p.trim().length() == 0)
		// {
		// return "0";
		// }
		// if("".equals(p) || "0".equals(p) || "0.00".equals(p) ||
		// "0.0".equals(p) || ".0".equals(p))
		// {
		// return "0";
		// }
		// else
		// {
		// p = p.replaceAll(",", "");
		// }
		//
		// p = p.trim();
		// int idot = p.trim().indexOf(".");
		// if(idot > 3)
		// {
		// String prefix = p.substring(0, idot);
		// String suffix = p.substring(idot);
		// prefix = reverseOrder(prefix);
		// String fprefix = "";
		//
		// for(int i = 0; i < prefix.length(); i++)
		// {
		// if(i % 3 == 0 && i != 0)
		// {
		// fprefix = fprefix + ",";
		// }
		// fprefix = fprefix + prefix.charAt(i);
		// }
		//
		// prefix = reverseOrder(fprefix);
		//
		// p = prefix + suffix;
		// }
		// else if(idot < 0 && p.length() > 3)
		// {
		// p = reverseOrder(p);
		// String pp = "";
		// for(int i = 0; i < p.length(); i++)
		// {
		// if(i % 3 == 0 && i != 0)
		// {
		// pp = pp + ",";
		// }
		// pp = pp + p.charAt(i);
		// }
		//
		// p = reverseOrder(pp);
		// }

		return p;
	}

	/**
	 * 取一个串的倒序
	 * 
	 * @param p
	 *            字符串
	 * 
	 * 
	 * @return 倒序串
	 */
	@SuppressWarnings("unused")
	private static String reverseOrder(String p) {
		String reverse = "";

		for (int i = p.length() - 1; i >= 0; i--) {
			reverse = reverse + p.charAt(i);
		}

		return reverse;
	}

	/**
	 * 
	 * @param str
	 * @param b
	 * @return
	 */
	private static String setFormat(String str, boolean b) {
		String str0 = str.replaceAll(",", "");
		double d0 = Double.parseDouble(str0);
		int d1 = (int) d0;
		if (d0 == d1) {
			str0 = formatNum(str0, 0, b);
		}
		return str0;
	}

	/**
	 * 
	 * @param num
	 * @return
	 */
	public static String arabia_to_Chinese(String num) {
		for (int i = num.length() - 1; i >= 0; i--) {
			num = num.replaceAll(",", ""); // 替换tomoney()中的“,”
			num = num.replaceAll(" ", ""); // 替换tomoney()中的空格
		}
		num = num.replaceAll("￥", ""); // 替换掉可能出现的￥字符
		try {
			Double.parseDouble(num);
		} catch (Exception e) { // 验证输入的字符是否为数字
			return "请检查小写金额是否正确";
		}

		// ---字符处理完毕，开始转换，转换采用前后两部分分别转换---//
		String[] part = SystemUtilities.getSep(num, ".");
		String newchar = "";
		// 小数点前进行转化
		for (int i = part[0].length() - 1; i >= 0; i--) {
			if (part[0].length() > 10) {
				return "位数过大，无法计算";
			} // 若数量超过拾亿单位，提示
			String tmpnewchar = "";
			char perchar = part[0].charAt(i);
			switch (perchar) {
			case '0':
				tmpnewchar = "零" + tmpnewchar;
				break;
			case '1':
				tmpnewchar = "壹" + tmpnewchar;
				break;
			case '2':
				tmpnewchar = "贰" + tmpnewchar;
				break;
			case '3':
				tmpnewchar = "叁" + tmpnewchar;
				break;
			case '4':
				tmpnewchar = "肆" + tmpnewchar;
				break;
			case '5':
				tmpnewchar = "伍" + tmpnewchar;
				break;
			case '6':
				tmpnewchar = "陆" + tmpnewchar;
				break;
			case '7':
				tmpnewchar = "柒" + tmpnewchar;
				break;
			case '8':
				tmpnewchar = "捌" + tmpnewchar;
				break;
			case '9':
				tmpnewchar = "玖" + tmpnewchar;
				break;
			}
			switch (part[0].length() - i - 1) {
			case 0:
				tmpnewchar = tmpnewchar + "元";
				break;
			case 1:
				if (perchar != 0)
					tmpnewchar = tmpnewchar + "拾";
				break;
			case 2:
				if (perchar != 0)
					tmpnewchar = tmpnewchar + "佰";
				break;
			case 3:
				if (perchar != 0)
					tmpnewchar = tmpnewchar + "仟";
				break;
			case 4:
				tmpnewchar = tmpnewchar + "万";
				break;
			case 5:
				if (perchar != 0)
					tmpnewchar = tmpnewchar + "拾";
				break;
			case 6:
				if (perchar != 0)
					tmpnewchar = tmpnewchar + "佰";
				break;
			case 7:
				if (perchar != 0)
					tmpnewchar = tmpnewchar + "仟";
				break;
			case 8:
				tmpnewchar = tmpnewchar + "亿";
				break;
			case 9:
				tmpnewchar = tmpnewchar + "拾";
				break;
			}
			newchar = tmpnewchar + newchar;
		}
		// 小数点之后进行转化
		if (num.indexOf(".") != -1) {
			if (part[1].length() > 2) {
				// alert("小数点之后只能保留两位,系统将自动截段");
				part[1] = part[1].substring(0, 2);
			}
			for (int i = 0; i < part[1].length(); i++) {
				String tmpnewchar = "";
				char perchar = part[1].charAt(i);
				switch (perchar) {
				case '0':
					tmpnewchar = "零" + tmpnewchar;
					break;
				case '1':
					tmpnewchar = "壹" + tmpnewchar;
					break;
				case '2':
					tmpnewchar = "贰" + tmpnewchar;
					break;
				case '3':
					tmpnewchar = "叁" + tmpnewchar;
					break;
				case '4':
					tmpnewchar = "肆" + tmpnewchar;
					break;
				case '5':
					tmpnewchar = "伍" + tmpnewchar;
					break;
				case '6':
					tmpnewchar = "陆" + tmpnewchar;
					break;
				case '7':
					tmpnewchar = "柒" + tmpnewchar;
					break;
				case '8':
					tmpnewchar = "捌" + tmpnewchar;
					break;
				case '9':
					tmpnewchar = "玖" + tmpnewchar;
					break;
				}
				if (i == 0) {
					tmpnewchar = tmpnewchar + "角";
				}
				if (i == 1) {
					tmpnewchar = tmpnewchar + "分";
				}
				newchar = newchar + tmpnewchar;
			}
		}
		// 替换所有无用汉字
		while (newchar.indexOf("零零") != -1)
			newchar = newchar.replaceAll("零零", "零");
		newchar = newchar.replaceAll("零亿", "亿");
		newchar = newchar.replaceAll("亿万", "亿");
		newchar = newchar.replaceAll("零万", "万");
		newchar = newchar.replaceAll("零元", "元");
		newchar = newchar.replaceAll("零角", "");
		newchar = newchar.replaceAll("零分", "");
		newchar = newchar.replaceAll("零拾", "");

		if (newchar.charAt(newchar.length() - 1) == '元'
				|| newchar.charAt(newchar.length() - 1) == '角') {
			newchar = newchar + "整";
		}
		return newchar;
	}

	/**
	 * 'yyyy-MM-dd','yyyy-MM-dd HH:mm:ss'
	 * 
	 * @param cal
	 * @param format
	 * @return
	 */
	public static String getStringDate(Calendar cal, String format) {

		Date date = cal.getTime(); // 取服务器时间
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String str = formatter.format(date);
		return str;
	}

	public static double sum(double a, double b) {
		return sum(String.valueOf(a), String.valueOf(b));
	}

	public static double sum(String a, double b) {
		return sum(String.valueOf(b), a);
	}

	public static double sum(double a, String b) {
		return sum(String.valueOf(a), b);
	}

	public static double sum(String a, String b) {
		double val = 0;
		if (a != null) {
			a = a.trim().replaceAll(",", "");
			try {
				val = val + Double.parseDouble(a);
			} catch (NumberFormatException ex) {
			}
		}
		if (b != null) {
			b = b.trim().replaceAll(",", "");
			try {
				val = val + Double.parseDouble(b);
			} catch (NumberFormatException ex) {
			}
		}

		return val;
	}

	/**
	 * 'yyyy-MM-dd'
	 * 
	 * @param cal
	 * @return
	 */
	public static String getStringDate(Calendar cal) {
		return getStringDate(cal, "yyyy-MM-dd");
	}

	/**
	 * 把double型转成整形，取大的值 2.1 转成3 2.0转成2
	 * 
	 * @param d
	 * @return
	 */
	public static int formatDoubleToMaxInt(double d) {
		return d % 1 > 0 ? new Double(d).intValue() + 1 : new Double(d)
				.intValue();
	}
}