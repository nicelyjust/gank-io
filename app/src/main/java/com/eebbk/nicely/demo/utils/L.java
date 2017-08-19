package com.eebbk.nicely.demo.utils;
import android.util.Log;

import com.eebbk.nicely.demo.constant.ServerAPI;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @description Log工具
 * @author fengjun
 * @version 1.0
 * @created 2015-3-1
 */
public class L {

	private static String DEFAULT_TAG = "gank-io";

	/**
	 * You can change its value to "false" on the release version
	 */
	private static final boolean LOGGER = ServerAPI.DEBUG;

	private L() {
	}

	public static void v( String tag, String msg ) {
		if ( LOGGER ) {
			Log.v( tag, printfclassLineStr( msg ) );
		}
	}

	public static void d( String tag, String msg ) {
		if ( LOGGER ) {
			Log.d( tag, printfclassLineStr( msg ) );
		}
	}

	public static void i( String tag, String msg ) {
		if ( LOGGER ) {
			Log.i( tag, printfclassLineStr( msg ) );
		}
	}

	public static void w( String tag, String msg ) {
		if ( LOGGER ) {
			Log.w( tag, printfclassLineStr( msg ) );
		}
	}

	public static void e( String tag, String msg ) {
		if ( LOGGER ) {
			Log.e( tag, printfclassLineStr( msg ) );
		}
	}

	public static void e( String tag, String msg, Throwable tr ) {
		if ( LOGGER ) {
			msg += "\n";
			msg += formatStackTrace( tr );
			Log.e( tag, printfclassLineStr( msg ) );
		}
	}

	public static void v( String msg ) {
		if ( LOGGER ) {
			Log.v( DEFAULT_TAG, printfclassLineStr( msg ) );
		}
	}

	public static void d( String msg ) {
		if ( LOGGER ) {
			Log.d( DEFAULT_TAG, printfclassLineStr( msg ) );
		}
	}

	public static void i( String msg ) {
		if ( LOGGER ) {
			Log.i( DEFAULT_TAG, printfclassLineStr( msg ) );
		}
	}

	public static void w( String msg ) {
		if ( LOGGER ) {
			Log.w( DEFAULT_TAG, printfclassLineStr( msg ) );
		}
	}

	public static void e( String msg ) {
		if ( LOGGER ) {
			Log.e( DEFAULT_TAG, printfclassLineStr( msg ) );
		}
	}

	public static void e( String msg, Throwable tr ) {
		if ( LOGGER ) {
			msg += "\n";
			msg += formatStackTrace( tr );
			Log.e( DEFAULT_TAG, printfclassLineStr( msg ) );
		}
	}

	public static void e( Throwable throwable ) {
		if ( LOGGER ) {
			String msg = formatStackTrace( throwable );
			Log.e( DEFAULT_TAG, printfclassLineStr( msg ) );
		}
	}

	/**
	 * 打印类名和行号字符串
	 *
	 * @param str
	 * @return String
	 */
	private static String printfclassLineStr( String str ) {
		StringBuffer strBuffer = new StringBuffer();
		StackTraceElement[] mStackTrace = new Throwable().getStackTrace();

		strBuffer.append( str );
		strBuffer.append("，File:(").append(mStackTrace[2].getFileName());
		strBuffer.append(":").append(mStackTrace[2].getLineNumber());
		strBuffer.append(")，Method:").append(mStackTrace[2].getMethodName());

		return strBuffer.toString();
	}

	public static String formatStackTrace( Throwable throwable ) {
		if ( throwable == null ) return "";
		String rtn = throwable.getStackTrace().toString();
		try {
			Writer      writer      = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer );
			throwable.printStackTrace( printWriter );
			printWriter.flush();
			writer.flush();
			rtn = writer.toString();
			printWriter.close();
			writer.close();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return rtn;
	}
}
