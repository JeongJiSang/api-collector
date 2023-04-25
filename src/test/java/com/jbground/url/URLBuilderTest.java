package com.jbground.url;

import com.jbground.collector.APICollector;
import com.jbground.collector.APICollectorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.List;
import java.util.Map;

class URLBuilderTest {

    String[] sample = {"http://210.204.213.171:8080/openapi/service/rest/EncyEtfhService/getEtfhVillageList?pageNo=xx&numOfRows=xx"
            , "https://apis.data.go.kr/1550246/textView/researchTextList?serviceKey=xx&pageNo=xx&numOfRows=xx"
            , "https://portal.nrich.go.kr/kor/openapi.do?idx=8&firstindex=xx&recordcountperpage=xx"
            , "https://www.kobis.or.kr/kobisopenapi/webservice/rest/people/searchPeopleInfo.xml?key=xx&peopleCd=xx"
            , "http://www.cha.go.kr/cha/openapi/selectEventListOpenapi.do"
    };

    String[] answer = {"http://210.204.213.171:8080/openapi/service/rest/EncyEtfhService/getEtfhVillageList?pageNo=1&numOfRows=100"
            , "https://apis.data.go.kr/1550246/textView/researchTextList?serviceKey=BHH3ALKp%2BnBJ8Zm6LqWe0FTii1jK72SzsQfyLQCGLGxRTEqEJnnOALcLEmjF2buJ04nKB4ncHnDFH4rotiy4vw%3D%3D&pageNo=1&numOfRows=100"
            , "https://portal.nrich.go.kr/kor/openapi.do?idx=8&firstindex=1&recordcountperpage=100"
            , "https://www.kobis.or.kr/kobisopenapi/webservice/rest/people/searchPeopleInfo.xml?key=xx&peopleCd=xx"
            , "http://www.cha.go.kr/cha/openapi/selectEventListOpenapi.do"
    };

    @Test
    void createTest() throws Exception {

        URLBuilder urlBuilder = new URLBuilder(sample[0]);
        urlBuilder.setFormat(URLBuilder.XML);
        urlBuilder.setParameterData(ParameterType.PAGE, "1");
        urlBuilder.setParameterData(ParameterType.ROW, "100");

        URL url = urlBuilder.build();

        Assertions.assertEquals(url.toString(), answer[0]);

        urlBuilder = new URLBuilder(sample[1]);
        urlBuilder.addServiceKey("BHH3ALKp%2BnBJ8Zm6LqWe0FTii1jK72SzsQfyLQCGLGxRTEqEJnnOALcLEmjF2buJ04nKB4ncHnDFH4rotiy4vw%3D%3D");
        urlBuilder.setFormat(URLBuilder.XML);
        urlBuilder.setParameterData(ParameterType.PAGE, "1");
        urlBuilder.setParameterData(ParameterType.ROW, "100");

        url = urlBuilder.build();

        Assertions.assertEquals(url.toString(), answer[1]);

    }


    @Test
    void parseTest() throws Exception {
        URLBuilder urlBuilder = new URLBuilder();
        urlBuilder.setURL(sample[1]);
        urlBuilder.addServiceKey("BHH3ALKp%2BnBJ8Zm6LqWe0FTii1jK72SzsQfyLQCGLGxRTEqEJnnOALcLEmjF2buJ04nKB4ncHnDFH4rotiy4vw%3D%3D");
        urlBuilder.setFormat(URLBuilder.XML);

        APICollector collector = APICollectorFactory.createCollector(urlBuilder);
        List<Map<String, String>> collect = collector.collect(urlBuilder.build());


    }
}