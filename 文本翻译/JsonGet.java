package 文本翻译;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonGet
{
    public static String textTrans(String from, String to, String q)
    {
        // 请求url
        String url = "https://aip.baidubce.com/rpc/2.0/mt/texttrans/v1";
        try
        {
            Map<String, Object> map = new HashMap<>();
            map.put("from", from);
            map.put("to", to);
            map.put("q", q);

            String param = com.alibaba.fastjson2.JSON.toJSONString(map);

            String accessToken = "24.c51ba07105623ec6a5767e18e87c1bc4.2592000.1707725152.282335-43853160";


            String result = HttpUtil.post(url, accessToken, "application/json", param);

            //将HTTP响应的JSON字符串解析为一个JSONObject对象
            JSONObject jsonObject = com.alibaba.fastjson2.JSON.parseObject(result);
            //从解析后的JSON对象中获取名为 "result" 的子对象
            JSONObject resultObject = jsonObject.getJSONObject("result");
            //从"result"对象中获取名为"trans_result"的JSON数组
            JSONArray transResultArray = resultObject.getJSONArray("trans_result");
            //从transResultArray数组中获取第一个翻译结果
            JSONObject transResultObject = transResultArray.getJSONObject(0);
            String dstValue = transResultObject.getString("dst");
            //输出翻译结果
            System.out.println("翻译结果为: " + dstValue);
            return dstValue;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args)
//    {
//        textTrans("en", "zh", "Most scientists and experts sharply dispute Hardy's views. “They casually dismiss the career work of scholars and scientists as just another misguided opinion,” says Dan Quinn senior communications strategist at the Texas Freedom Network, a non-profit group that monitors public education. “What millions of Texas kids learn in their public schools is determined too often by the political ideology of partisan board members, rather than facts and sound scholarship.");
//    }
}
