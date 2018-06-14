package com.bonc.staff.map.service;

import com.bonc.staff.map.pojo.SpiderRawDto;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xukj
 */
public class HttpService {
    public static final Logger logger = LoggerFactory.getLogger(HttpService.class);
    public static String cookie = "AdminStaff=qITWpHb/vJa5Bx9l1aQmaZIDzUlNpBjLfcXINni36LoL6hj6XAUejZsozS0gjZbJNIeQaKApAbWWodTLUsVQiyd4A5ExhzdBge2TZZKEeif/4cmAwPtx2d04gtxqpzQW/yBMtZqS0BmFXGLz3GdcOAqn60lh6MYjj88+v11/nUKdav6EG35ULChv85+qtd01dgyprJMnGfG5Mh7KQAjSulknnoruylcJlJcgg+D5F3naZJN+NvBTNE6URxq0NqElhBzdnrV3+dmrvpgd4GIibbK4RzDx65CF7VznZB+gBB25iO2aLgNSCTq6rssZbW4uEELx1rl+a6ipriHr/OZpfao/bkESKWGWc50O2O1wrF8uIpEpBsm4YHBMisuVIDQMF6/f5sim2Sh7mcPx+tE1aAHc8Vv31xGS0Ptm1ETJN4TlDXb/DWscnyErLsNNBh3av3xUZ097yzUeu9jbqIjAl8J4foPYmxWBp6F6D7UXCxPeLxudTkC+6BRxIWKv/VT+bZhTIZ1MUB6XEFjpxZDpjyYeKAvgz6ZZrzpK4OEKkc45Wh5N8C9KH/p7BuMqNYWCNBEzmSBkn8kuVHlseudwKIgrt/ST2V+tNowxjXk6+ET8W5Kb1fqagUlOzz7bgeOXU4QeDrsg3/x4ewi+C4RG3doiM30jbTGyz6mXFT6Ru2Ei0pvvSO2ZR57QAkU8qcuIqRCkJxDcMtp6T5YXfuJC5qTi0zczVEVl4lhFGXtiRCyXxTMS0ZNqGKCcJ0QUk+lAN3ZtOOekC+VdJCxqAbSYqjwCGwC4hnblZ4ecoRkeqTPdVKtWSkrmsNwY2TWFbuDdGgi77jzRmPAK8f/6dz6euMJxgb0gHJ7oQAlBp6KZ/h0jKIiZw1GKlDHm+w6tgNpTaGuwPLljTglaR5/ISJRO81kwvfhF+fNbP2F7yWY6IxgShgfw4hGlWbKGFz4JIsv3raWvR/C5DoFyMWFpo7+zPmj3FvDk8/U68AWGGst3cQCjHunyEsjOWdAQ1F/EaIBFMREcrq9qHc6zyVjg++doPHsHiUlRIWTlR/ufXdkIcHJEGsLDUtFzDwrWX8nqDfz/DGYYCIHBZ+bofcahpGxIP017Ehxd1TC2Z5bDSnFYIY7AqkjOdZhp9J/2Kp26NvDMrMnzkOiK3sz2I7mp3Rj+sJuyphbaa+1wysqDZwyMcX1ZhY7fJtFAI1BhrbYivQelh+/hHI52cdIO+0OTFRqb+7hSjiPEEi+vEZaDOl6qbqNHgKHfNoIKbFBgJkvczqLOS2s48iqCCqSgtKft/9zSRNt/owHMi50lzFH26+FfUnHj0nluVv6D6WD+TBL+yIKlpPajM8PXE0ZPiPpFAgZ02EPqd/tF/Jsgb+9Cb/gU9U5/uelJTVZmXZLtm59lQg5MlffTI7gyrtq8QfHOn9bRVtCZ6C7UCt0tmZaFD3Nz23sRVioWnyXp1ndq+3r4mnsvKp6u8JlKuFBCyO98M+r00TYumdEhSdjMgLk23zNWSWYv4cHQXRqIgi4DzYQVBVunzaJpVOTLtnnmrdR5q8GUfzzftnmYq9w+/fwl6CWq9bfhq4W3DEzaTLmkK1d9io73onCgzZ3BA/N7d83S1XkThiTdwgpJy2z/ts3bfKvekmjek2HlTTffrGnj8qVv/gohXfn7ITr3clo/O67gFA5JQ1uYl42pCL+/uSSb//ucxGZmDZNsnvjxIlEOafKfsPEP3idwl7nRBYLI/CzKhoHsNAFRmD2sRWsYdHqkcrTUy5afAHcQE+rLHLmxCQCgZSnOjH5kc/EpbPXCpYRJ5dxCrdo2e6a2qg4Y/kI7C4j9fPo7SKI1aE/iNHzm3vbZNPJGilddrypp/rb0BLKR0nIkJBc7meVgMvGt4nTuGkEj9XN+L9KNED+Q0lZNrFHZfB71rpDfqjcQ51ffdVhG6z2akiLBgjcQu40UVKTGv7nq0/QfN9INICaPYr9U1BA4CR/S9jyNL6vwJWQ=; domain=10010.com; expires=Saturday, 22-Apr-2017 12:00:22 GMT; path=/";

    public HttpService() {
    }

    public SpiderRawDto get(String url, String cookie) {
        SpiderRawDto spiderRawDto = new SpiderRawDto();
        spiderRawDto.setStartTime(new Date());
        HttpGet httpGet = new HttpGet(url);
        if (StringUtils.isNotBlank(cookie)) {
            httpGet.addHeader("Cookie", cookie);
        }

        try {
            HttpResponse httpResponse = (new DefaultHttpClient()).execute(httpGet);
            Map<String, String> headerParam = new HashMap();
            StringBuffer sb = new StringBuffer();
            Header[] var8 = httpResponse.getHeaders("Set-cookie");
            int var9 = var8.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                Header header = var8[var10];
                sb.append(header.getValue());
            }

            headerParam.put("cookie_2", sb.toString());
            spiderRawDto.setHeaders(headerParam);
            HttpEntity entity = httpResponse.getEntity();
            logger.info("get请求status:" + httpResponse.getStatusLine());
            spiderRawDto.setStatus(httpResponse.getStatusLine().getStatusCode());
            if (entity != null) {
                spiderRawDto.setRawContent(EntityUtils.toString(entity, "UTF-8"));
                if (spiderRawDto.getRawContent().contains("商城、商户登录")) {
                    logger.error("cookie失效，请重新登录");
                    spiderRawDto.setStatus(-98);
                }
            }
        } catch (IOException var12) {
            logger.error("request url:{}, cookie:{}, message:{}", new Object[]{url, cookie, var12.getMessage()});
            spiderRawDto.setStatus(-99);
            spiderRawDto.setRawContent("io 异常");
        }

        spiderRawDto.setEndTime(new Date());
        return spiderRawDto;
    }
}