package mainData.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import mainData.service.dataMainService;


@RestController // 컨트롤러라고 알려주는 어노테이션
@RequestMapping("/api")
public class dataMainController  {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	dataMainService mainService;

	
	@RequestMapping(value= "/test", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public @ResponseBody Map<String, Object> helloBasic() {
		Map<String, Object> dataList = new HashMap<String, Object>();
        log.info("helloBasic");
       
        dataList.put("test", "1");
		return dataList;
    }
	

	@RequestMapping(value  = "/where/to/go", method = RequestMethod.POST, produces = "application/json; charset=utf8") // localhost:8080/api/getRequestApi 로 들어오면 해당 getMethod api를 사용할 수 있다.
	public @ResponseBody Map<String, Object> dataMainSelect (@RequestBody Map<String, Object> param)throws Exception {
			Map<String, Object> returnData = new HashMap<String, Object>();
	    	Map<String, Object> dataList = new HashMap<String, Object>();
	    	
	    	System.out.println("param="+ param.get("test"));
	    	//데이터 리턴입니다.
	    	dataList = mainService.whereToGo();
	    	
	    	returnData.put("returnCode", dataList.get("returnCode"));
	    	returnData.put("returnMessage", dataList.get("returnMessage"));
	    	returnData.put("dataList", dataList.get("dataList"));
	    	
	    	return returnData;
	}

}
