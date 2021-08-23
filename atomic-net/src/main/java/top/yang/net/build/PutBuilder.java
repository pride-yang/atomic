package top.yang.net.build;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import top.yang.net.build.base.OkHttpRequestBuilder;

public class PutBuilder extends OkHttpRequestBuilder<PutBuilder> {

  public PutBuilder(OkHttpClient okHttpClient) {
    super(okHttpClient);
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

    builder.put(RequestBody.create("", MediaType.parse("text/plain;charset=utf-8")));

    Request request = builder.build();

    try {
      return okHttpClient.
          newCall(request).execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}