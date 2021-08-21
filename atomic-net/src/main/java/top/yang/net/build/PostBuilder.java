package top.yang.net.build;

import java.io.IOException;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import top.yang.net.build.base.OkHttpRequestBuilderHasParam;

public class PostBuilder extends OkHttpRequestBuilderHasParam<PostBuilder> {

  private String jsonParams = "";

  public PostBuilder(OkHttpClient okHttpClient) {
    super(okHttpClient);
  }

  /**
   * json格式参数
   *
   * @param json
   * @return
   */
  public PostBuilder jsonParams(String json) {
    this.jsonParams = json;
    return this;
  }

  @Override
  public okhttp3.Response execute() {
    if (url == null || url.length() == 0) {
      throw new IllegalArgumentException("url can not be null !");
    }

    Request.Builder builder = new Request.Builder().url(url);
    appendHeaders(builder, headers);

    if (tag != null) {
      builder.tag(tag);
    }

    if (jsonParams.length() > 0) {
      //上传json格式参数
      RequestBody body = RequestBody.create(jsonParams, MediaType.parse("application/json; charset=utf-8"));
      builder.post(body);
    } else {        //普通kv参数
      FormBody.Builder encodingBuilder = new FormBody.Builder();
      appendParams(encodingBuilder, params);
      builder.post(encodingBuilder.build());
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

  //append params to form builder
  private void appendParams(FormBody.Builder builder, Map<String, String> params) {

    if (params != null && !params.isEmpty()) {
      for (String key : params.keySet()) {
        builder.add(key, params.get(key));
      }
    }
  }
}
