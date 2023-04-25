package com.jbground.collector;

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by jsjeong on 2022. 8. 29.
 * <pre>
 *  어떤 데이터 타입이든 List로 parsing 후 리턴하도록 하는 Parser interface
 *  반복이 필요할 경우 hasNext(), next() 를 통해 dependantData와 page를 증가시켜서 데이터를 추출 할 수 있도록 한다.
 * </pre>
 */
public interface Parser {

    /**
     * 종료 여부
     * @return true 진행, false 종료
     */
    boolean hasNext();

    List<Map<String, String>> parse(URL url) throws Exception;

}
