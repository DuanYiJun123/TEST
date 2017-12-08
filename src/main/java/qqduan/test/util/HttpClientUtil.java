package qqduan.test.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * http工具�?
 * 
 * @author xue.wen
 *
 */
public class HttpClientUtil {

	HttpClientUtil() {
	}

	public static String sendGetRequest(String reqURL, String decodeCharset) {
		String responseContent = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(reqURL);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
				EntityUtils.consume(entity);
			}
		} catch (ClientProtocolException e) {
		} catch (ParseException e) {
		} catch (IOException e) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return responseContent;
	}

	public static String sendPostRequest(String reqURL, String sendData, boolean isEncoder) {
		return sendPostRequest(reqURL, sendData, isEncoder, null, null);
	}

	public static String sendPostRequest(String reqURL, String sendData, boolean isEncoder, String encodeCharset,
			String decodeCharset) {
		String responseContent = null;
		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(reqURL);
		httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
		try {
			if (isEncoder) {
				List<NameValuePair> formParams = new ArrayList<NameValuePair>();
				for (String str : sendData.split("&")) {
					formParams.add(new BasicNameValuePair(str.substring(0, str.indexOf("=")),
							str.substring(str.indexOf("=") + 1)));
				}
				httpPost.setEntity(new StringEntity(
						URLEncodedUtils.format(formParams, encodeCharset == null ? "UTF-8" : encodeCharset)));
			} else {
				httpPost.setEntity(new StringEntity(sendData));
			}

			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return responseContent;
	}

	public static String sendPostRequest(String reqURL, Map<String, String> params, String encodeCharset,
			String decodeCharset, Map<String, String> headers) {
		String responseContent = null;
		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(reqURL);

		for (Map.Entry<String, String> entry : headers.entrySet()) {
			httpPost.setHeader(entry.getKey(), entry.getValue());
		}

		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset == null ? "UTF-8" : encodeCharset));

			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return responseContent;
	}

	public static String sendPostRequest(String reqURL, Map<String, String> params, String encodeCharset,
			String decodeCharset) {
		String responseContent = null;
		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(reqURL);
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset == null ? "UTF-8" : encodeCharset));

			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return responseContent;
	}

	public static String sendPostSSLRequest(String reqURL, Map<String, String> params) {
		return sendPostSSLRequest(reqURL, params, null, null);
	}

	public static String sendPostSSLRequest(String reqURL, Map<String, String> params, String encodeCharset,
			String decodeCharset) {
		String responseContent = "";
		HttpClient httpClient = new DefaultHttpClient();
		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[] { xtm }, null);
			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));

			HttpPost httpPost = new HttpPost(reqURL);
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset == null ? "UTF-8" : encodeCharset));

			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return responseContent;
	}

	public static String sendPostRequestByJava(String reqURL, Map<String, String> params) {
		StringBuilder sendData = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sendData.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		if (sendData.length() > 0) {
			sendData.setLength(sendData.length() - 1);
		}
		return sendPostRequestByJava(reqURL, sendData.toString());
	}

	public static String sendPostRequestByJava(String reqURL, String sendData) {
		HttpURLConnection httpURLConnection = null;
		OutputStream out = null;
		InputStream in = null;
		int httpStatusCode = 0;
		try {
			URL sendUrl = new URL(reqURL);
			httpURLConnection = (HttpURLConnection) sendUrl.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setConnectTimeout(150000);
			httpURLConnection.setReadTimeout(150000);
			httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			out = httpURLConnection.getOutputStream();
			out.write(sendData.getBytes());
			out.flush();
			httpStatusCode = httpURLConnection.getResponseCode();

			in = httpURLConnection.getInputStream();
			byte[] by = new byte[1024];
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len = -1;
			while ((len = in.read(by)) != -1) {
				bos.write(by, 0, len);
			}
			return bos.toString("utf-8");

		} catch (Exception e) {
			e.printStackTrace();
			return "Failed`" + httpStatusCode;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
				httpURLConnection = null;
			}
		}
	}

	public static String sendPost(String url, byte[] params) {
		HttpURLConnection httpConn = null;
		OutputStream out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			httpConn = (HttpURLConnection) realUrl.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Accept-Charset", "utf-8");
			httpConn.setRequestProperty("Connection", "keep-alive");
			httpConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset = UTF-8");
			httpConn.setRequestProperty("Accept", "application/xml");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.803.0 Safari/535.1");
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			out = httpConn.getOutputStream();
			out.write(params);
			out.flush();
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
