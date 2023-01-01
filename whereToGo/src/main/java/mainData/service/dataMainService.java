package mainData.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import mainData.dao.commonMasterDao;

@Service
public class dataMainService {
	
	@Autowired
	//commonMasterDao dao;
	
	public Map<String, Object> whereToGo(Map<String, Object> param) throws UnsupportedEncodingException {
		Map<String, Object> returnData = new HashMap<String, Object>();
    	Map<String, Object> dataList = new HashMap<String, Object>();
    	
		//정부포탈 url
		String urlName = "http://openapi.seoul.go.kr:8088";
		//인증키
		String key = "/5a45564a63776f673130314259674d4b";
		//data포맷 xml : xml, xml파일 : xmlf, 엑셀파일 : xls, json파일 : json
		String dataFormat = "/xml";
		//서비스명
		String serviceName = "/citydata";
		//요청시작위치page
		String startIndex = "/1"; 
		//요청종료위치page
		String endIndex = "/5";
		//핫스팟장소]
		String cityName = param.get("cityName").toString();
		
		String apiUrl = urlName + key + dataFormat + serviceName + startIndex + endIndex + URLEncoder.encode(cityName, "UTF-8");
		System.out.println("apiUrl="+ apiUrl);
		
		HttpURLConnection conn = null;
		URL url;
		try {
			url = new URL(apiUrl);
			
			conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/xml");
			
			//conn.setDoOutput(true);

	        int responseCode = conn.getResponseCode();

	        if (responseCode == 400 || responseCode == 401 || responseCode == 500 ) {
	            System.out.println("responseCode="+ responseCode + " Error!");
	            returnData.put("returnCode", responseCode);
	        } else {
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line = "";
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	            JSONObject json = XML.toJSONObject(sb.toString());
	            
	            returnData.put("returnCode", responseCode);
	            returnData.put("dataList", json.toString());
	        }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

        
		return returnData;
	}
}

