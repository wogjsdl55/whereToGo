package mainService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.stereotype.Service;

import DAO.CommonMasterDao;
import jakarta.annotation.Resource;

@Service
public class DataMainService {
	
	@Resource(name="CommonMasterDao")
	CommonMasterDao commonMasterDao;
	  
	public Map<String, Object> whereToGo() {
		Map<String, Object> returnDataList = new HashMap<String, Object>();
		
		//정부포탈 url
		String urlName = "http://openapi.seoul.go.kr:8088";
		//인증키
		String key = "/5953414865776f673930544a775166";
		//data포맷 xml : xml, xml파일 : xmlf, 엑셀파일 : xls, json파일 : json
		String dataFormat = "/json";
		//서비스명
		String serviceName = "/citydata";
		//요청시작위치page
		String startIndex = "/1"; 
		//요청종료위치page
		String endIndex = "/5";
		//핫스팟장소]
		String areaName = "/";
		
		String apiUrl = urlName + key + dataFormat + serviceName + startIndex + endIndex + areaName;
		
		
		HttpURLConnection conn = null;
        JSONObject responseJson = null;
		URL url;
		try {
			url = new URL(apiUrl);
			
			conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			conn.setRequestMethod("GET");
			
			//conn.setDoOutput(true);
			
	        JSONObject commands = new JSONObject();

	        int responseCode = conn.getResponseCode();
	        if (responseCode == 400 || responseCode == 401 || responseCode == 500 ) {
	            System.out.println(responseCode + " Error!");
	        } else {
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line = "";
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	            responseJson = new JSONObject(sb.toString());
	            System.out.println(responseJson);
	        }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

        
		return returnDataList;
	}
}
