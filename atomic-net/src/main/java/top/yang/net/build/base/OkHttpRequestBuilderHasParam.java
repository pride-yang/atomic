package top.yang.net.build.base;

import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.OkHttpClient;

public abstract class OkHttpRequestBuilderHasParam<T extends OkHttpRequestBuilderHasParam> extends OkHttpRequestBuilder<T> {

  protected Map<String, String> params;

  public OkHttpRequestBuilderHasParam(OkHttpClient okHttpClient) {
    super(okHttpClient);
  }

  /**
   * set Map params
   *
   * @param params
   * @return
   */
  public T params(Map<String, String> params) {
    this.params = params;
    return (T) this;
  }

  /**
   * add param
   *
   * @param key param key
   * @param val param val
   * @return
   */
  public T addParam(String key, String val) {
    if (this.params == null) {
      params = new LinkedHashMap<>();
    }
    params.put(key, val);
    return (T) this;
  }
}
