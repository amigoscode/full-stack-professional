package com.amigoscode.auth.common.log;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LogContextData {

    private List<String> contexts;
    private List<String> params;

    public LogContextData(String context, String param) {
        this.contexts = new ArrayList<>();
        this.params = new ArrayList<>();

        if(!StringUtils.isEmpty(context)) {
            this.contexts.add(context);
        }
        if(!StringUtils.isEmpty(param)) {
            this.params.add(param);
        }
    }
    public LogContextData(String context) {
        this(context, null);
    }

    public LogContextData() {
        this(null);
    }

    public String getContextsAsString() {
        return String.join(" ", contexts);
    }

    public String getParamsAsString() {
        return String.join(",", params);
    }

    public String getFullContextString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(!contexts.isEmpty()) {
            stringBuilder.append(getContextsAsString());
            stringBuilder.append(": ");
        }
        if(!params.isEmpty()) {
            stringBuilder.append(getParamsAsString());
        }

        return stringBuilder.toString();
    }
}
