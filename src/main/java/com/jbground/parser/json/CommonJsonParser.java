package com.jbground.parser.json;


import com.jbground.collector.Parser;

/**
 * Created by jsjeong on 2022. 9. 15.
 * <pre>
 *  Default Json Parser
 * </pre>
 */
public class CommonJsonParser extends AbstractJsonParser implements Parser {

    public CommonJsonParser(AbstractJsonHandler handler) {
        super(handler);
    }

    public CommonJsonParser(AbstractJsonHandler handler, int index) {
        super(handler, index);
    }

}
