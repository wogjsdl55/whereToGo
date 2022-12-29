package mainCONTROLLER;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import mainService.DataMainService;


@RestController // 컨트롤러라고 알려주는 어노테이션
@RequestMapping("/data")
public class DataMainController  {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "datamainservice")
	DataMainService datamainservice;
	
	@Autowired
	HttpServletRequest req;

	
	@GetMapping("/")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }
	
	@RequestMapping(value = "/where/to/go", method = RequestMethod.GET) // localhost:8080/api/getRequestApi 로 들어오면 해당 getMethod api를 사용할 수 있다.
	public class GetController {
	    public Map<String, Object> whereToGo(HttpServletResponse response, HttpServletRequest request){
	    	Map<String, Object> dataList = new HashMap<String, Object>();
	    	
	    	//데이터 리턴입니다.
	    	dataList = datamainservice.whereToGo(); 
	    	return dataList;
	    }
	}

}
