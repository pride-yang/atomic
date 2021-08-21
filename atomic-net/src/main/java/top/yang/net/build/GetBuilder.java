package top.yang.net.build;

import java.io.IOException;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import top.yang.net.build.base.OkHttpRequestBuilderHasParam;

public class GetBuilder extends OkHttpRequestBuilderHasParam<GetBuilder> {

  public GetBuilder(OkHttpClient okHttpClient) {
    super(okHttpClient);
  }

  @Override
  public okhttp3.Response execute() {

    if (url == null || url.length() == 0) {
      throw new IllegalArgumentException("url can not be null !");
    }

    if (params != null && params.size() > 0) {
      url = appendParams(url, params);
    }

    Request.Builder builder = new Request.Builder().url(url).get();
    appendHeaders(builder, headers);

    if (tag != null) {
      builder.tag(tag);
    }

    Request request = builder.build();

    try {
      return okHttpClient.
          newCall(request).execute();
    } catch (IOException e) {

      e.printStackTrace();
    }
    return null;
  }

  //append params to url
  private String appendParams(String url, Map<String, String> params) {
    StringBuilder sb = new StringBuilder();
    sb.append(url + "?");
    if (params != null && !params.isEmpty()) {
      for (String key : params.keySet()) {
        sb.append(key).append("=").append(params.get(key)).append("&");
      }
    }

    sb = sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }
}
